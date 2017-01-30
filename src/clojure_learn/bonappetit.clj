(ns clojure-learn.bonappetit
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Bon Appetit
; 
; https://www.hackerrank.com/challenges/bon-appetit
;

(defn anna-share
    "Anna's fair share"
    [cost k]
    (/ (- (reduce + cost) (cost k)) 2))

(defn -main
  "Main program"
	[& args]
 	(let [
        [n k]  (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        cost   (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
        charged   (Integer/parseInt (read-line))
        fair   (anna-share cost k)
        refund (- charged fair)
        ]
        (if (= 0 refund)
            (println "Bon Appetit")
            (println refund))))

