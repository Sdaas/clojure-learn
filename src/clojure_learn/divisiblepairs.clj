(ns clojure-learn.divisiblepairs
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Divisible Sum Pairs
; 
; Solution for https://www.hackerrank.com/challenges/divisible-sum-pairs
;

(defn pair-one
  	"given a number n and a list, create pairs"
  	[n data]
  	(reduce #(conj %1 (list n %2)) (list ) data))


(defn pairs
    "return all the possible pairs from the list"
    [data]
  
    (defn inner
        [acc d]
     	(if (empty? d)
            acc
            (let [
              tmp (pair-one (first d) (rest d))
              newacc (concat acc tmp)
              ]
              (recur newacc (rest d)))))
  
    (inner '() data))


 (defn divisible-pairs
	"How many pairs in data are devisible by k"
	[k data]
	(let [
		sums (map #(+ (first %1 ) (second %1)) (pairs data))
		n    (filter #(= 0 (rem % k)) sums)
		]
		(count n)))
 

 (defn -main
 	"Main program"
	[& args]
 	(let [
          [n k ] (map #(Integer/parseInt %) (split (read-line) #"\s+") ) 
          data (map #(Integer/parseInt %) (split (read-line) #"\s+") ) 
        ]
        (println (divisible-pairs k data))))


