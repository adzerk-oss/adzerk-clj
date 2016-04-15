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
  [method url-fmt & {:keys [data-type] :as opts}]
  (let [url-fmt (str ADZERK_API_HOST url-fmt)]
    (fn doreq
      ([]
       (doreq [] nil))
      ([data]
       (doreq [] data))
      ([args data]
       (let [url     (apply format url-fmt args)
             payload (case data-type
                       :form {:form-params data}
                       :json {:body data}
                       :none {})
             req     (merge
                       {:method      method
                        :form-params data
                        :url         url
                        :headers     {"X-Adzerk-ApiKey" *api-key*}}
                       payload)]
         ;; an HTML response indicates an error
         (try (parse-string (:body (client/request (log-passthru req))) true)
              (catch Exception e
                (throw (log/spy :error (ex-info "API request exception" req e))))))))))

(defmacro api
  [method url-fmt data-type & forms]
  `(let [~'doapi (zerkreq ~method ~url-fmt :data-type ~data-type)]
     (fn ~@forms)))

(defmacro defapi
  [name & forms]
  `(def ~name (api ~@forms)))

(defmacro deflist
  "List all items."
  [item]
  `(defapi ~(symbol (format "list-%ss!" item))
     :get ~(str "/v1/" item) :none
     []
     (:items (~'doapi))))

(defmacro defget
  "Get an item."
  [item]
  `(defapi ~(symbol (format "%s" item))
     :get ~(str "/v1/" item "/%s") :none
     [id#]
     (~'doapi [id#] nil)))

