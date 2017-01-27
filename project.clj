(defproject clojure-learn "0.1.0-SNAPSHOT"
  :description "Learning Clojure ..."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojure-learn.repeated
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.4.0"]]
  :profiles {:uberjar {:aot :all}})
