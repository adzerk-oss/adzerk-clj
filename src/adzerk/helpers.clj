(ns adzerk.helpers
  (require '[clojure.tools.logging :as log]
           '[clj-http.client :as client]
           '[camel-snake-kebab.core :refer [->PascalCaseString ->kebab-case-keyword]]
           '[cheshire.core :refer [generate-string parse-string]]
           '[clojure.walk :refer [postwalk]]))

(defn transform-keys [t coll]
  "Recursively transforms all map keys in coll with t.
   Taken from: https://crossclj.info/ns/camel-snake-kebab/latest/camel-snake-kebab.extras.cljs.html"
  (letfn [(transform [[k v]] [(t k) v])]
    (postwalk (fn [x] (if (map? x) (into {} (map transform x)) x)) coll)))

(defn csharp->clj
  "Keywordize a result from the API. This helps when REPLing together data for
  testing."
  [api-entity]
  (->> [api-entity]
       (transform-keys ->kebab-case-keyword)
       (first)))

(defn log-passthru [x]
  (log/info x)
  x)

(defn clj->csharp
  [m & [overrides]]
  (generate-string m {:key-fn #(get overrides % (->PascalCaseString %))}))

