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
; A particular position can be taken by a maximum of two numbers
; x = k + i
; x = i - k


(defn solve
  [data i k]

  (defn -inner
    [data x i k]
    ;(println "inner **" data x i k )
    (if (contains? data x)
      (solve (difference data #{x}) (inc i) k)
      '()))

  (if (empty? data)
    (list #{})
    (let [
          x1 (+ k i)
          x2 (- i k)
          l1  (map #(cons x1 %) (-inner data x1 i k))
          l2  (if (= x1 x2) '() (map #(cons x2 %) (-inner data x2 i k)))
          ]
      (concat l1 l2))))

(defn first-permutation
  [n k]
  (let [
        data (into #{} (rest (range (inc n))))
        out  (solve data 1 k)
        valid? (not (empty? out))
        ]
    (if valid?
      {:valid true :perm (first out)}
      {:valid false})))

(defn process
  "Processes each line of input and outputs the result"
  [string]
  ;(println "processing : " string)
  (let [
        [n k] (map #(Integer/parseInt %) (split string #"\s+"))
        {valid? :valid perm :perm} (first-permutation n k)
        ]
    (if valid?
      (doseq [item perm] (print item ""))
      (print "-1"))
    (println)))

(defn -main
  "Main loop"
  [& args]
  (let [
        t      (Integer/parseInt (read-line))   ; number of test cases
        strvec (read-n t) ; read all the lines into a vector of strings
        ]
    (doseq [item strvec] (process item))))
