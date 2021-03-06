(ns clojure-learn.bomberman
  (use [clojure-learn.simpleio :only (read-n)])
  (use [clojure.string :only (split)])
  (:gen-class))

; Bomber Man
;
; https://www.hackerrank.com/challenges/bomber-man
;
; This is what each value means
; 0 : There is no bomb here
; n : Number of ticks before the bomb goes off

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
  ; See https://clojuredocs.org/clojure.core/map-indexed %1 = index, %2 = item

  (defn foo
    [index _] ; item is not used
    (next-state data index rows cols))

  (into [] (map-indexed foo data)))


(defn simulate
  "given an initial state, do a simultation for n seconds"
  [initial-state rows cols n]
  #(println "simulate" initial-state rows cols n)
  (loop [t 0
         state initial-state]
    #(println t state)
    (if (= t n)
      state
      (let [
            tmp1 (boom state rows cols)
            tmp2 (tick tmp1)
            next-data (if (odd? t) (place-bombs tmp2) tmp2)
            ]
        #(println "*" tmp1)
        #(println "*" tmp2)
        #(println "*" next-data)
        (recur (inc t) next-data))))
  )

(defn format-input
   "Format the input into a vector" ; [ "...O..." . . . ] with one entry per row
  [strvec]
  (let [
        str (reduce concat strvec) ; concat them into a big sequence of characters
        tmp (map #(if (= %1 \.) 0 3) str) ; convert . to 0 and O to 3 )
        ]
    (into [] tmp)) ; return a vector, not a list
  )

(defn output
  [data rows cols]
  (let [
        tmp1 (partition cols (map #(if (= 0 %1) \. \O) data))
        tmp2 (map #(apply str %1) tmp1)
        ]
    (doseq [x tmp2] (println x))))

; The insight is that the output after time n (n>4) is the same as the output at 4 + n mod 4.
; So the output at time 8 is the same as that at time 4 + 8 mod 4 = 4
; and the output at time 9 is the same as that at time 4 + 9 mod 4 = 5

(defn -main
  "Main loop"
  [& args]
  (let [
        [rows cols n]  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        strvec (read-n rows) ; read all the lines into a vector of strings
        data (format-input strvec)
        n-dash (if (> n 4) (+ 4 (rem n 4)) n)
        out  (simulate data rows cols n-dash)
        ]
    (output out rows cols)))
