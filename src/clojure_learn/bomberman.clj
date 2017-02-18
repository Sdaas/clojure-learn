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
  (map #(if (> %1 0) (dec %1) 0) data))

(defn place-bombs
  "Place a bomb on all the empty slots"
  [data]
  (map #(if (= %1 0 ) 3 %1) data))

(defn first-cell-in-row?
  "return true if this is the first cell in the row, false otherwise"
  [index C] (= 0 (rem index C)))

(defn last-cell-in-row?
  "return true if this is the last cell in the row, false otherwise"
  [index C] (= (dec C) (rem index C)))

(defn cell-in-first-row?
  "return true if the cell is in the first row, false otherwise"
  [index R]
  (= 0 (quot index R)))

(defn cell-in-last-row?
  [index R C]
  (= (dec R) (quot index C)))

; next state of cell k is a function of the current state of the following cells
; k+1 (if k % C != C-1) (i.e., if k is not the last cell in the row ... )
; k-1 (if k % C != 0 ) (i.e., if k is no the first cell in the row ... )
; k+r (if k / C != R-1) (i.e., k is not in the last row )
; k-r (if k / C != 0 ) (i.e., if k is not in the first row )

(defn boom
  "blow up the bombs"
  [data rows]
  (list 1 2 3 ))

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        ]
    (doseq [item strvec] (println item))))
