(ns adzerk.api
  (:require [adzerk.helpers        :refer (log-passthru)]
            [clj-http.client       :as    client]
            [clojure.tools.logging :as    log]
            [cheshire.core         :refer (parse-string)]))

(def ^:dynamic *root-url* (System/getenv "ADZERK_API_HOST"))
(def ^:dynamic *api-key*  (System/getenv "ADZERK_API_KEY"))

(defn zerkreq
  [method url-fmt]
  (let [url-fmt (str *root-url* url-fmt)]
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
         (try (parse-string (:body (client/request (log-passthru req))))
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
     (~'doapi api-key#)))

(defmacro defget
  "Get an item."
  [item]
  `(defapi ~(symbol (format "%s!" item))
     :get ~(str "/v1/" item "/%s")
     [api-key# {:keys [~'id]}]
     (~'doapi api-key# [~'id] nil)))

