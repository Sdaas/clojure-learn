(ns clojure-learn.bomberman
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (use [clojure.set :only (difference)])
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Bomber Man
;
; https://www.hackerrank.com/challenges/bomber-man
;

; This is what each value means
; 0 : There is no bomb here
; n : Number of ticks before the bomb goes off
;

(defn tick
  "A single clock tick - decrement the time on all the bombs"
  [data]
  (into [] (map #(if (> %1 0) (dec %1) 0) data)))

(defn place-bombs
  "Place a bomb on all the empty slots"
  [data]
  (into [] (map #(if (= %1 0 ) 3 %1) data)))

(defn first-cell-in-row?
  "return true if this is the first cell in the row, false otherwise"
  [index C] (= 0 (rem index C)))

(defn last-cell-in-row?
  "return true if this is the last cell in the row, false otherwise"
  [index C] (= (dec C) (rem index C)))

(defn cell-in-first-row?
  "return true if the cell is in the first row, false otherwise"
  [index C]
  (= 0 (quot index C)))

(defn cell-in-last-row?
  [index R C]
  (= (dec R) (quot index C)))

(defn next-cell-bomb?
  "returns true is the next cell is a bomb that is about to explode"
  [data k R C]
  ; if this cell is the last cell in a row, then obviously return false
  ; else return true if the next cell is a bomb tht is about to explode
  (if (last-cell-in-row? k C) false (= 1 (data (inc k)))))

(defn prev-cell-bomb?
  "returns true is the previous cell is a bomb that is about to explode"
  [data k R C]
  ; if this cell is the first cell in a row, then obviously return false
  ; else return true if the previous cell is a bomb tht is about to explode
  (if (first-cell-in-row? k C) false (= 1 (data (dec k)))))

(defn top-cell-bomb?
  "returns true if the top cell (cell in the previous row) is a bomb that is about to explode"
  [data k R C]
  ; if this cell is in the top row, then obviously return false
  (if (cell-in-first-row? k C) false (= 1 (data (- k C)))))

(defn bottom-cell-bomb?
  "returns true if the bottom cell (cell in the next row) is a bomb that is about to explode"
  [data k R C]
  ; if this cell is in the bottom row, then obviously return false
  (if (cell-in-last-row? k R C) false (= 1 (data (+ k C)))))

(defn current-cell-bomb?
  "returns true is the current cell is about to explode"
  [data k R C]
  (= 1 (data k)))

; next state of cell k is a function of the current state of the following cells
; k+1 (if k % C != C-1) (i.e., if k is not the last cell in the row ... )
; k-1 (if k % C != 0 ) (i.e., if k is not the first cell in the row ... )
; k-C (if k / C != 0 ) (i.e., if k is not in the first row )
; k+C (if k / C != R-1) (i.e., k is not in the last row )

(defn next-state
  "compute the next state of cell based on its own state and that of its neighbors"
  [data k R C]
  (let [
        flist [current-cell-bomb? prev-cell-bomb? next-cell-bomb? top-cell-bomb? bottom-cell-bomb?]
        explode? (some true? (map #(%1 data k R C) flist))
        ]
        (if explode? 0 (data k)))) ; if about to explode return 0, else the current state

(defn boom
  "blow up the bombs"
  [data rows cols]
  (println "boom" data rows cols)
  ; See https://clojuredocs.org/clojure.core/map-indexed %1 = index, %2 = item

  (defn foo
    [index _] ; item is not used
    (next-state data index rows cols))

  (into [] (map-indexed foo data)))


; 0  initial boom
; 1  tick boom
; 2  tick pace boom
; 3  tick boom
; 4  step 3 : tick place boom
; 5  step 4 ; tick boom
; 6  step 3 ; tick place boom
; 7  step 4 ; tick boom



(defn simulate
  "given an initial state, do a simultation for n seconds"
  [initial-state rows cols n]

  (loop [t 0
         state initial-state]
    (if (= t n)
      state
      (let [
            tmp1 (tick state)
            tmp2 (if (even? n) (place-bombs tmp1) tmp1)
            next-data (boom tmp2 rows cols)
            ]
        (println t next-data)
        (recur (inc t) next-data))))
  )

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        ]
    (doseq [item strvec] (println item))))