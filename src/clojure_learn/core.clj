(ns clojure-learn.core
	(:require [clojure-learn.triplet :refer :all])
	(use [clojure.string :only (split triml)])
 	(:gen-class))


(defn -main
  [& args]
  
  (let [
  	a_string (read-line)
  	b_string (read-line)
  	a_score  (parse a_string)
  	b_score  (parse b_string)
  	score    (triplet a_score b_score)
  	]
  	(println score)
  	(println (score :alice) (score :bob))))
