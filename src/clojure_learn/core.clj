(ns clojure-learn.core
	(:require [clojure-learn.modfib :refer :all])
	(use [clojure.string :only (split triml)])
 	(:gen-class))


(defn -main
  [& args]
  (clojure-learn.modfib/process))
