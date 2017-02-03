(ns clojure-learn.prisoner
  (use [clojure.string :only (split triml)])
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (:gen-class))

; Save the prisoner
;
; https://www.hackerrank.com/challenges/save-the-prisoner
;
; If we use starting index as 0, then the prisoner Id
; is given by  (s + m - 1 ) mod n. But both s and the prisoner
; id start with 1, so this is modified as
;
; s-dash = s - minus 1             ; covert the starting prisoner with index 0
; p-dash = ( s-dash + m - 1 ) % n   ; prisoner id in starting index 0
; p = p-dash +1


;t n m s

(defn poisoned-prisoner
  "compute the id of poisoned prisoner"
  [item]
  (let [
        [n m s] item
        s-dash  (dec s)
        p-dash  (rem (dec (+ s-dash m)) n)
        ]
    (inc p-dash)))

(defn foo
  [s]
  (map #(Integer/parseInt %) (split s #"\s+")))

(defn -main
  "Main loop"
  [& args]
  (let [
        t         (Integer/parseInt (read-line))
        str-data  (read-n t)  ; vector of strings
        data      (map #(foo %) str-data)     ; vector of seq of n m s
        out       (map #(poisoned-prisoner %) data)  ; compute the poisoned prisoner for each case
        ]
    (write-n out)))
