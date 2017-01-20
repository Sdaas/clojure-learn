(ns clojure-learn.modfib
	(use [clojure.string :only (split triml lower-case)]))

; Modified Fibonacci
; 
; Solution for https://www.hackerrank.com/challenges/fibonacci-modified
;
; t(n+2) = t(n) + [ t(n+1)]^2
;

(defn modfib
	[f1 f2 n]

	(defn inner
		[n current v]
      	;(println "***")
      	;(println "c=" current)
      	;(println "v=" v)
		(let [
              f-1 (first v)
              f-2 (second v)
                f (+' f-2 (*' f-1 f-1))  ; *' => fall over to using BigInt when needed
            new_v (conj v f)
              ]
          	  (if (= n current)
          	  	f
          	  	(inner n (inc current) new_v))))         

    ; handle the special cases
  	(cond
    	(= n 1) f1
    	(= n 2) f2
    	:else (inner n 3 (list f2 f1))))

(defn process
	"The main loop"
	[]
	(let [
		[t1 t2 n ] (map #(Integer/parseInt %) (split (read-line) #"\s+"))
		answer  (modfib t1 t2 n)
		]
		(println (format "%d" (biginteger answer)))))