(ns clojure-learn.core
	(:require [clojure-learn.circular :refer :all])
	(use [clojure.string :only (split triml)])
 	(:gen-class))


(defn -main
  [& args]
  (clojure-learn.circular/process))
