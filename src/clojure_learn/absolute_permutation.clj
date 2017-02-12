(ns clojure-learn.absolute-permutation
  (use [clojure-learn.simpleio :only (read-n write-n)])
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
; A particular position can be taken by a maximum of two numbers
; x = k + i
; x = i - k

(defn solve
  [data i k]
  (if (empty? data)
    (list #{})
    (let [
          x1  (+ k i)
          x2  (- i k)
          p1  (if (contains? data x1) (solve (clojure.set/difference data #{x1}) (inc i) k) '()) ; a list or nil
          p2  (if (contains? data x2) (solve (clojure.set/difference data #{x2}) (inc i) k) '()) ; a list or nil
          l1  (map #(cons x1 %) p1)
          l2  (map #(cons x2 %) p2)
          ]
      (concat l1 l2))))


(defn first-absolute-permutation
  "return the first absolute permutation"
  [data k]
  ; some hard-coded dummy value for now
  (list 1 2 3))

(defn process
  "Processes each line of input and outputs the result"
  [string]
  ;(println "processing : " string)
  (let [
        [n k] (map #(Integer/parseInt %) (split string #"\s+"))
        data  (rest (range (inc n)))
        perm  (first-absolute-permutation data k)
        ]
    (if (nil? perm)
      (print "-1")
      (doseq [item perm] (print item "")))
    (println)))

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        ]
    (doseq [item strvec] (process item))))
