(set-env!
  :source-paths #{"src" "test"}
  :dependencies '[[adzerk/boot-test "1.0.4"  :scope "test"]
                  [clj-http "2.0.0"]
                  [cheshire "5.5.0"]
                  [org.clojure/tools.logging "0.3.1"]
                  [camel-snake-kebab "0.3.2"]])

(require '[adzerk.boot-test :refer :all])

