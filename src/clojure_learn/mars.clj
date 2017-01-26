(ns clojure-learn.mars
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Mars Exploration
; 
; Solution for https://www.hackerrank.com/challenges/mars-exploration

(defn create-sos
  "Create N blocks of SOS"
  [n]

  (defn inner
    [acc n]
      (if (= 0 n)
        acc
        (let [
            newacc  (concat acc (list \S \O \S))
            ]
            (recur newacc (dec n)))))

  (inner '() n))

 (defn count-errors
    "count the number of letters that have changed in string of n blocks"
    [str]
    (let [
      n       (quot (count str) 3)  ; number of blocks
      txed    (create-sos n)        ; what was transmitted
      rxed    (seq str)      ; convert string into a sequence
      changed (count (filter #(false? %) (map #(= %1 %2) txed rxed)))
      ]
      changed))

 (defn -main
 	"Main program"
	[& args]
 	(let [
        str (read-line) 
        ]
        (println (count-errors str))))
