(ns clojure-learn.triplet
	(use [clojure.string :only (split triml)]))

(defn parse
  	"parse an string of ints into a list"
  	[s]
  	(map #(Integer/parseInt %1) (split s #"\s+")))


(defn triplet
  [a_score b_score]
  (let [
  		alice		(reduce + (map #(if (> %1 %2) 1 0) a_score b_score))
  		bob			(reduce + (map #(if (> %2 %1) 1 0) a_score b_score))
  	]
  	{:alice alice :bob bob}))