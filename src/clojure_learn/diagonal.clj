
(ns clojure-learn.diagonal
 	(:gen-class))

;Solution for https://www.hackerrank.com/challenges/diagonal-difference
;
;Given a square matrix of size  N, calculate the absolute difference between the sums of its diagonals.
;Obviously, this must work for both odd- and even- values of N.
;
;
; A NxN matrix is represented as a list of lists where each inner list represents a row,
; and the ith outer list represents the ith row. The kth element in the inner list represents
; the kth column in that row. For example, the matrix
;  | 1 2 3 |
;  | 4 5 6 |
;  | 7 8 9 |
;
; is represented as  ( (1 2 3)  (4 5 6)  (7 8 9))
;
; solution(n) = ( a[1][1] + a[2][2] ... + a[n-1][n-1] + a[n][n] ) - ( a[1][n] + a[2][n-1] ... + a[n-1][2] + a[n][1] )
; 			  = ( a[1][1] + a[n][1] ) - ( a[1][n] + a[n][1]) + ( a[2][2] ... + a[n-1][n-1]) - ( a[2][n-1] ... + a[n-1][2] )
;			  = ( a[1][1] + a[n][1] ) - ( a[1][n] + a[n][1])  + solution(n-2)
;
; The solution for the NxN matrix can be expressed in terms of the inner (N-2)x(N-2) sub-matrix
;

;(def m (list (list 11 12 13 14 15 ) (list 21 22 23 24 25) (list 31 32 33 34 35) (list 41 42 43 44 45) (list 51 52 53 54 55)))


(defn solve
	"Calculate the absolute difference between the sum of its diagonals, i.e."
	[m]  

	(defn inner_solve
		"Calculate the difference between the sum of its diagonals"
		[m]  
		;(println "solving for " m)
		(if (= 0 (count m))
			0
			(let [
				a11 (first (first m))
				a1n (last (first m))
				an1 (first (last m))
				ann (last (last m))
				diag1 (+ a11 ann)
				diag2 (+ a1n an1)
				m_temp (rest (drop-last m)) ; Drop the first and last rows of m
				m2 (map #(rest (drop-last %1)) m_temp ) ; Drop the first and last columns in each row
				m2_solve (inner_solve m2)
				]
				;(println diag1 diag2 m2_solve)
				(+ (- diag1 diag2) m2_solve))))

	(Math/abs (inner_solve m)))














