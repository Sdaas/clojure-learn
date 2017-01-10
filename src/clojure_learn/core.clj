(ns clojure-learn.core
	(:require [clojure-learn.bigint :refer :all])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")

  (def a (string2bigint "12345"))
  (def b (string2bigint "456789"))
  (def c (add a b))
  (println (bigint2string c)))

