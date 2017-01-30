(ns clojure-learn.distance
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Minimum Distances
; 
; https://www.hackerrank.com/challenges/minimum-distances
;

; We first need to scan the list, and make a hashtable of all the indices.
; For the data [7 1 3 4 1 7 1], the hash table looks like
; {
;     7 : [0 5]    ; Since 7 occurs at indices 0 and 5
;     1 : [1 4 5]  ; Since 1 occurs at indices 1 and 4
;     3 : 2
;     4 : 3
;}
;
; Now for each list of indices, we need to find the pair that has
; the minimum distance between them. Brute force this is a O(N^2)
; problem. Faster approach is to sort this list and find the minimum
; distance between items n and n+1.

(defn delta
    "Given a sorted list, find the difference between item n+1 and item n"
    ; It is assumed that no item repeats in the list
    ([data] 
        (delta [] data))
    ([acc data]
        (if (< (count data) 2)
          acc
          (let [
               diff   (- (second data) (first data))
               newacc (conj acc diff)
               ]
               (recur newacc (rest data))))))

(defn minimum-delta
    "Given a (unsorted) list, find the minimum difference between two members of the list"
    ; it assumed that list does not contain duplicate entries
    [data]
    (apply min (delta (sort data))))



(defn create-map-old
    "Create a map of indices from the data"
    ([data] 
        (create-map-old {} data 0))
    ([m data index]
        (if (empty? data)
            m
            (let [
                key     (first data)
                indices (or (m key) #{})
                newmap  (assoc m key (conj indices index))
                ]
                (recur newmap (rest data) (inc index))))))

; Same function as create-map, but using reduce. 
; calling     (create-map [10 20 30 30 40 30 10])
; results in  {10 #{0 6}, 20 #{1}, 30 #{3 2 5}, 40 #{4}}
(defn create-map
    "Create a map of indices from the data"
    [data]

    (defn add-to-map
        [m item]
        (let [
            [idx data] item
            val  (or (m data) #{})
            ]
            (assoc m data (conj val idx))))

    (let [
        indices        (range (count data)) 
        data-index-map (map vector indices data)  ; [0 data0 1 data1 2 data2 etc ]
        ]
        (reduce add-to-map {} data-index-map)))

(defn minimum
    "Find the minimum distance between two items that are same"
    [data]
    (let [
        vals1   (vals (create-map data))  ; set of set of indices
        vals2   (filter #(> (count %) 1) vals1)
        minlist  (map #(minimum-delta (into [] %)) vals2)
        ]
        (if (empty? minlist)      ; will happen if all elements of data are unique
            -1
            (apply min minlist))))
        

(defn -main
  "Main program"
	[& args]
 	(let [
        dummy   (read-line)
        data    (map #(Integer/parseInt %) (split (read-line) #"\s+"))      
        ]
        (println (minimum data))))

