(ns clojure-learn.circular
	(use [clojure.string :only (split triml lower-case)]))

; Solution for https://www.hackerrank.com/challenges/circular-array-rotation
;
; Given
; n = number of elements
; x = old position
; k = amount of right shift
; m = new position
;
; (x + k) mod n = m
;
; For example :
;
; Applying k = 3 
; data = 10 20 30 40 50 60 70 80, and n = 8
; we get 
; data = 60 70 80 10 20 30 40 50
; 
; The data at m=5 is 30. In the original data, this was at position x=2
; (2 + 3) mod 8 = 5 mod 8 = 5
; The data at m=0 is 60. In the original data, this was at position x=5
; (5 + 3) mod 8 = 8 mod 8 = 0 
;
; How to solve for x ? 
; 
; ( x + k) mod n = m
; => (x + k) = n*i + m   where i = 0 1 2 ...
;
; We need to find the smallest value of x that satisfies this equation

(defn solve
  	([n k m] (solve n k m 0))
  	([n k m i]
		(let [
			out (- (+ (* n i) m) k)
         ](if (>= out 0)
            out
            (recur n k m (inc i))))))

; n k q
; list of n data points
; q lines 
(defn process
	"The main loop"
	[]
	(let [
		[n k q] (map #(Integer/parseInt %) (split (read-line) #"\s+"))
		data (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
		qlist (for [temp (range q)]  (Integer/parseInt (read-line)))
		xlist (map #(solve n k %) qlist)
		answer (map #(nth data %) xlist)
		]
		(println n k q)
		(println data)
		(println qlist)
		(println xlist)
		(println answer)
		(map #(println %) answer))) ; <== last line not working
		


		
