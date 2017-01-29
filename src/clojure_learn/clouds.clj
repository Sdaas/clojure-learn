(ns clojure-learn.clouds
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Jumping on the Clouds
; 
; https://www.hackerrank.com/challenges/jumping-on-the-clouds
;

(defn paths
    "all the paths from the first cloud to the last one"
    [clouds thunder]
    (cond
        (first thunder)   []  ; if the first cloud is a thundercloud there is no path forward
        (= (count clouds) 1) [clouds]
        (= (count clouds) 2) [clouds]        
    :else 
        (let [
            n0     (first clouds)
            r1     (paths (rest clouds) (rest thunder))   ; all the possible paths from the n+1 node
            p1     (map #(cons n0 %) r1)
            r2     (paths (rest (rest clouds)) (rest (rest thunder))) ; all paths from the n+2 node
            p2     (map #(cons n0 %) r2)
            ]
            (println p1)
            (println r2)
            (concat p1 p2))))

(defn -main
  "Main program"
	[& args]
 	(let [
        days   (Integer/parseInt (read-line)) 
        ]
        (println days)))

