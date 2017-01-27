(ns clojure-learn.equalize
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Equalize all elements in an array
; 
; https://www.hackerrank.com/challenges/equality-in-a-array
;
; "Find and print the minimum number of deletion operations Karl must perform so that all the array's elements are equal."
;
; The idea is to create a map where the values indicate how many times
; a number is repeated. For example the array [ 1 1 1 1 2 2 3] would
; be { 1 : 4, 2 : 2, 3 : 1}.
;
; We need to delete all the elements except the ones with the maximum
; count. In this case, we would want to delete everything but the "1s"

(defn create-hashmap
    "given an array create a hashmap for each number and its frequency"
    ([data] 
        (create-hashmap {} data))
    ([m data]
        (if (empty? data)
            m
            (let [
                k   (first data)
                val (or (m k) 0)    ; set the value to 0 if key is not in the map
                new-map (into m {k (inc val)} )   
                ]
                (recur new-map (rest data))))))


(defn highest-frequency
    "given a hashmap of each number and its frequency, return the highest frequency"
    [h]
    (apply max (vals h)))

(defn items-to-delete
    "min items to delete so that the remainder of array has same value"
    [data]
    (let [
        m    (create-hashmap data)
        high (highest-frequency m)
        ]
        (- (count data) high)))

(defn -main
  "Main program"
	[& args]
 	(let [
        n    (read-line)   ; the length of the array. not used
        data (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
        (println (items-to-delete data))))

