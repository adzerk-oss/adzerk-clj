(set-env!
  :source-paths #{"src" "test"}
  :dependencies '[[adzerk/bootlaces          "0.1.12" :scope "test"]
                  [adzerk/boot-test          "1.1.0"  :scope "test"]
                  [adzerk/env                "0.3.0"]
                  [clj-http                  "2.0.0"]
                  [cheshire                  "5.5.0"]
                  [org.clojure/tools.logging "0.3.1"]
                  [camel-snake-kebab         "0.3.2"]])

(require '[adzerk.bootlaces :refer :all]
         '[adzerk.boot-test :refer :all])

(def +version+ "0.0.1")

(bootlaces! +version+)

(task-options!
  pom {:project     'adzerk-clj
       :version     +version+
       :description "A Clojure wrapper for the Adzerk APIs"
       :url         "TBD"
       :license     {"name" "Eclipse Public License"
                     "url" "http://www.eclipse.org/legal/epl-v10.html"}})
