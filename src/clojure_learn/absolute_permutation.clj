(ns clojure-learn.absolute-permutation
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (use [clojure.set :only (difference)])
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Absolute Permutation
;
; https://www.hackerrank.com/challenges/absolute-permutation
;

; Suppose N = 6 and K = 2
; numbers are {1 2 ... 6}
; i=1 => abs(x-1) = 2 => { 3 }
; i=2 => abs(x-2) = 2 => { 4 }
; i=3 => abs(x-3) = 2 => { 1, 5 }
; i=4 => abs(x-4) = 2 => { 2, 6 }
; i=5 => ans(x-5) = 2 => { 3 }
;
; A particular position can be taken by a maximum of two numbers
; x1 = k + i
; x2 = i - k
;
; So at any step, we will be "optimistic" and lead with the smaller of x1, x2. If we cannot
; get a solution with x1, then we will back track and try with x2.


; [...] data i k
; x1
; x2
; x = smaller of x1, x2 that is in the data set
;
; o try  [.... x] (data minus x ) (i+1) k
; if o !=nil
;    o this is what we are looking for
;    try [ ... x )data minus x ) i+1 k

(defn candidates
  "returns a vector of possible candidates at this position, in priority order"
  [data i k]
  (let [
        x1 (+ i k )
        x2 (- i k )]
    (cond
      (or (= x1 x2) (< x2 0)) [x1]  ; this is the only option possible at this position
      (< x1 x2) [x1 x2]     ; we shall lead with the smaller number
      :else     [x2 x1] ))) ; since x2 is smaller, we shall lead with that


(defn smallest
  "returns the smallest absolute permutation, or nil"
  [acc data i k]  ; acc must be a vector, data is a set

  (defn -helper
    [acc2 data2 cc ii kk]
    (smallest (conj acc2 cc) (clojure.set/difference data2 #{cc}) (inc ii) kk))

  ;(println "*** " acc data i k)
  (if (empty? data)
    acc ; we have a solution !
    (let [
          [c1 c2] (filter #(contains? data %) (candidates data i k))  ; generate the candidates.
          ]
      (cond
        (nil? c1) nil ; we can never have a non-nil c2 in this case
        (nil? c2) (-helper acc data c1 i k )
        :else  ; try c1 first, if that fails, then try c2
        (let [
              o1 (-helper acc data c1 i k )
              ]
          (if (nil? o1)
            nil
            (-helper acc data c2 i k )))))))



(defn process
  "Processes each line of input and outputs the result"
  [string]
  ;(println "processing : " string)
  (let [
        [n k] (map #(Integer/parseInt %) (split string #"\s+"))
        data  (into #{} (rest (range (inc n))))
        out (smallest [] data 1 k)
        ]
    (if (nil? out)
      (print "-1")
      (doseq [item out] (print item "")))
    (println)))

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        ]
    (doseq [item strvec] (process item))))
