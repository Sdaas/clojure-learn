(ns clojure-learn.strange-counter
  (use [clojure.string :only (split triml)])
  (use [clojure-learn.util :only (exp log2)])
  (:gen-class))


; Strange Counter
;
; https://www.hackerrank.com/challenges/strange-code
;
; The first step is to identify which countdown sequence t belongs to
; So we need to find  3 + 6 + 12 + …. such that is it just greater than t
;
; For example:
; With t = 15
; 3 + 6 + 12 is just larger than 15, so its part of the 3rd sequence
;
; This is a geometric sequence with a = 3 and r = 2
; Given g(n) = a ( 1 - r^n ) / ( 1 - r ) = 3*( 2^n - 1 )
; g(N) <= t < g(N+1)
;
; t > 3*(2^N - 1 ) => n = log 2 ( t/3 + 1 )



(defn countdown-sequence
  "Compute the countdown sequence that t is part of"
  [t]
  (let [
        n-exact (log2 (inc (/ t 3.00)))
        n1 (int (Math/ceil n-exact))
        ]
    n1))


(defn value-at-start-of-sequence
  "the value at start of the nth countdown sequence"
  ;3*2^(n-1)
  [n]
  (* 3 (exp 2 (dec n))))

(defn time-at-start-of-sequence
  "the time at str"
  [n]
  (inc (* 3 (dec (exp 2 (dec n))))))


(defn display-value
  "the value displayed at time t"
  [t]
  (let [
        n (countdown-sequence t)
        v0 (value-at-start-of-sequence n)
        t0 (time-at-start-of-sequence n)
        dt (- t t0)
        ]
    (- v0 dt)))

(defn -main
  "Main loop"
  [& args]
  (let [
        n (bigint (read-line))
        answer (display-value n)
        ]
    (println (format "%d" (biginteger answer)))))

