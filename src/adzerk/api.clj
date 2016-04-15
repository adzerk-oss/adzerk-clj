(ns adzerk.api
  (:require [adzerk.helpers        :refer (log-passthru)]
            [adzerk.env            :as    env]
            [clj-http.client       :as    client]
            [clojure.tools.logging :as    log]
            [cheshire.core         :refer (parse-string)]))

(env/def
  ADZERK_API_HOST "https://api.adzerk.net"
  ADZERK_API_KEY  nil)

(def ^:dynamic *api-key* ADZERK_API_KEY)

(defn zerkreq
  [method url-fmt {:keys [input-data output-data] :as opts}]
  (let [url-fmt (str ADZERK_API_HOST url-fmt)]
    (fn doreq
      ([]
       (doreq [] nil))
      ([data]
       (doreq [] data))
      ([args data]
       (let [url     (apply format url-fmt args)
             input  (case input-data
                      :form {:form-params data}
                      :json {:body data}
                      {})
             output (case output-data
                       :stream {:as :stream}
                       {})
             req    (merge
                      {:method  method
                       :url     url
                       :headers {"X-Adzerk-ApiKey" *api-key*}}
                      input
                      output)]
         ;; an HTML response indicates an error
         (try
           (let [{:keys [body headers] :as res} (client/request
                                                  (log-passthru req))
                 content-type (get headers "Content-Type")]
             (case content-type
               "application/gzip" body
               "application/json" (parse-string body true)
               (throw (Exception. "API response content type was not gzip or json."))))
           (catch Exception e
             (throw (log/spy :error (ex-info "API request exception" req e))))))))))

(defmacro api
  [method url-fmt opts & forms]
  `(let [~'doapi (zerkreq ~method ~url-fmt ~opts)]
     (fn ~@forms)))

(defmacro defapi
  [name & forms]
  `(def ~name (api ~@forms)))

(defmacro deflist
  "List all items."
  [item]
  `(defapi ~(symbol (format "list-%ss!" item))
     :get ~(str "/v1/" item)
     {:input-data :none}
     []
     (:items (~'doapi))))

(defmacro defget
  "Get an item."
  [item]
  `(defapi ~(symbol (format "%s" item))
     :get ~(str "/v1/" item "/%s")
     {:input-data :none}
     [id#]
     (~'doapi [id#] nil)))

