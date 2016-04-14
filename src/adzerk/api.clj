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
  [method url-fmt]
  (let [url-fmt (str ADZERK_API_HOST url-fmt)]
    (fn doreq
      ([api-key]
       (doreq api-key [] nil))
      ([api-key data]
       (doreq api-key [] data))
      ([api-key args data]
       (let [url (apply format url-fmt args)
             req {:method      method
                  :form-params data
                  :url         url
                  :headers     {"X-Adzerk-ApiKey" api-key}}]
         ;; an HTML response indicates an error
         (try (parse-string (:body (client/request (log-passthru req))) true)
              (catch Exception e
                  (throw (log/spy :error (ex-info "API request exception" req e))))))))))

(defmacro api
  [method url-fmt & forms]
  `(let [~'doapi (zerkreq ~method ~url-fmt)]
     (fn ~@forms)))

(defmacro defapi
  [name & forms]
  `(def ~name (api ~@forms)))

(defmacro deflist
  "List all items."
  [item]
  `(defapi ~(symbol (format "list-%ss!" item))
     :get ~(str "/v1/" item)
     [api-key#]
     (:items (~'doapi api-key#))))

(defmacro defget
  "Get an item."
  [item]
  `(defapi ~(symbol (format "%s!" item))
     :get ~(str "/v1/" item "/%s")
     [api-key# {:keys [~'id]}]
     (~'doapi api-key# [~'id] nil)))

