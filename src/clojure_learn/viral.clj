(ns clojure-learn.viral
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Viral Advertising
; 
; https://www.hackerrank.com/challenges/strange-advertising
;

; day 0. Sent to 5 people. list is ()
; day 1. 5/2 = 2 like it. Sent to 2x3 = 6 people. list is (2)
; day 2. 6/2 = 3 like it. Send to 3x3 = 9 people. list is (3 2)
; day 3. 9/2 = 4 like it. Send to 4x3 = 12 people. list is (4 3 2)
; day 4. return 4+3+2 = 9
; 

(defn market
    "reach of the market on the nth day"
    [days]
    
    (defn inner
        [n sent-to accumulator]
        (if (= n days)
            (reduce + accumulator)
            (let [
                liked-by  (quot sent-to 2)
                new-sent-to (* liked-by 3)
                new-acc   (cons liked-by accumulator)
                ]
                (recur (inc n) new-sent-to new-acc)
                )
            )
    )

    (inner 0 5 '()))


(defn -main
  "Main program"
	[& args]
 	(let [
        days   (Integer/parseInt (read-line)) 
        ]
        (println days)))

