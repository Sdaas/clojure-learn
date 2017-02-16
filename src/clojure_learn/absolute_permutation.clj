(ns clojure-learn.absolute-permutation
  (use [clojure-learn.simpleio :only (read-n write-n)])
  (use [clojure.set :only (difference)])
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Absolute Permutation
;
; https://www.hackerrank.com/challenges/absolute-permutation
;

; For k = 0, need to do abs (data[i] - i ) = 0 => data[i] = i
;
; For other values of k, any particular position can be taken by a maximum of two numbers
; x1 = k + i
; x2 = i - k;
;
; Lets see how these play out various values of k when N = 6
;
; K = 1         K = 2         K = 3       K = 4
;
; Pos x1 x2     Pos x1 x2     Pos x1 x2   Pos x1 x2
; 1   2  -      1   3  -      1   4  -    1   5  -
; 2   3  1      2   4  -      2   5  -    2   6  -
; 3   4  2      3   5  1      3   6  -    3   -  -
; 4   5  3      4   6  2      4   -  1    4   -  -
; 5   6  4      5   -  3      5   -  2    5   -  1
; 6   -  5      6   -  4      6   -  3    6   -  2
;
; So the values that x1 and x2 can take can be seen as two "windows" that slide past each other. From the example
; above it can be seen that
; (a) For K > N/2 there are slots that cannot be covered by any number. So there is no solution for K > N/2
; (b) For K = N/2, it can be seen that there is _exactly_ one solution for each number. So a solution exists
; (c) For K < N/2, it can be been that there is _exactly_ one solution for the first k and last k numbers. But the
;     pattern is not clear
;
; Lets see how these play out various values of k when N = 12
;
; K = 1         K = 2         K = 3       K = 4       K=5         K=6
;
; Pos x1 x2     Pos x1 x2     Pos x1 x2   Pos x1 x2   Pos x1 x2   Pos x1  x2
; 1   2  -      1   3  -      1   4  -    1   5  -    1   6  -    1    7  -
; 2   3  1      2   4  -      2   5  -    2   6  -    2   7  -    2    8  -
; 3   4  2      3   5  1      3   6  -    3   7  -    3   8  -    3    9  -
; 4   5  3      4   6  2      4   7  1    4   8  -    4   9  -    4   10  -
; 5   6  4      5   7  3      5   8  2    5   9  1    5  10  -    5   11  -
; 6   7  5      6   8  4      6   9  3    6  10  2    6  11  1    6   12  -
; 7   8  6      7   9  5      7  10  4    7  11  3    7  12  2    7    -  1
; 8   9  7      8  10  6      8  11  5    8  12  4    8   -  3    8    -  2
; 9  10  8      9  11  7      9  12  6    9   -  5    9   -  4    9    -  3
;10  11  9     10  12  8     10   -  7   10   -  6   10   -  5   10    -  4
;11  12 10     11   -  9     11   -  8   11   -  7   11   -  6   11    -  5
;12   - 11     12   - 10     12   -  9   12   -  8   12   -  7   12    -  6
;
;
; For K=1 (2 1) (4 3) (6 5) (8 7) (10 9) (12 11)
; For K=2 (3 4 1 2) (7 8 5 6) (11 12 9 10)
; For K=3 (4 5 6 1 2 3) (10 11 12 7 8 9)
; For K=4 No Solution .....
; For K=5 No Solution .....
; For K=6 (7 8 9 10 11 12) (1 2 3 4 5 6)
;
; (d) Solution exists if N / K is an even number

(defn solution?
  [n k]
  (or (= k 0) (= 0 (rem n (* 2 k)))))
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


; Returns the smallest absolute permutation. Or nil, if no absolute permutation exists
(defn smallest
  ([n k]
   (cond
     (= k 0 ) (into [] (rest (range (inc n))))
     (not (solution? n k)) nil
     :else (smallest []  (into #{} (rest (range (inc n)))) 1 k)))

  ([acc data i k]  ; acc must be a vector, data is a set

    (defn -helper
      [acc2 data2 cc ii kk]
      (smallest (conj acc2 cc) (clojure.set/difference data2 #{cc}) (inc ii) kk))

    (if (empty? data)
      acc ; we have a solution !
      (let [
            [c1 c2] (filter #(contains? data %) (candidates data i k))  ; generate the candidates.
            ]
        ;(println c1 c2)
        (cond
          (nil? c1) nil ; we can never have a non-nil c2 in this case
          (nil? c2) (-helper acc data c1 i k )
          :else  ; try c1 first, if that fails, then try c2
          (let [
                o1 (-helper acc data c1 i k )
                ]
            ;(println "o1=" o1)
            (if (not (nil? o1))   ; if o1 worked, then return it, else return the result of c2
              o1
              (-helper acc data c2 i k ))))))))


(defn process
  "Processes each line of input and outputs the result"
  [string]
  ;(println "processing : " string)
  (let [
        [n k] (map #(Integer/parseInt %) (split string #"\s+"))
        out (smallest n k)
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
