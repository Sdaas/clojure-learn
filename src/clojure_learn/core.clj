(ns clojure-learn.core
	(:require [clojure-learn.moon :refer :all])
	(use [clojure.string :only (split triml)])
 	(:gen-class))


(defn -main
  [& args]
  (clojure-learn.moon/process))