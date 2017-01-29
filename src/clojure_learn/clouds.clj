(ns clojure-learn.clouds
  (use [clojure.string :only (split join triml)])
  (use [clojure.set :only (union)])
  (:gen-class))

; Jumping on the Clouds
; 
; https://www.hackerrank.com/challenges/jumping-on-the-clouds
;

(defn paths
    "all the paths from the first cloud to the last one"
    [clouds thunder]

    (defn prepend-node-to-paths
        "prepend current node to all paths"
        [node paths]
        (into #{} (map #(cons node %) paths)))
    
    (cond
        (first thunder)   #{}  ; if the first cloud is a thundercloud there is no path forward
        (= (count clouds) 1) #{clouds}
        (= (count clouds) 2) 
            (let [
                n0    (first clouds)
                r1    (paths (rest clouds) (rest thunder))   ; all the possible paths from the n+1 node
                p1    (prepend-node-to-paths n0 r1)
                ]
                p1)       
        :else 
            (let [
                n0     (first clouds)
                r1     (paths (rest clouds) (rest thunder))   ; all the possible paths from the n+1 node
                p1     (prepend-node-to-paths n0 r1)
                r2     (paths (rest (rest clouds)) (rest (rest thunder))) ; all paths from the n+2 node
                p2     (prepend-node-to-paths n0 r2)
                ]
                (union p1 p2))))

(defn minimum-steps
    "Minimum steps from the first to last cloud (avoid thunder clouds)"
    [data]
    (let [
        thunder (map #(= 1 %) data)   ; Which ones are thunder clouds true/false
        clouds  (range (count data))  ; give each cloud a name
        p       (paths clouds thunder) ; set of paths
        lens    (map #(count %) p)         ; length of each math
        ]
        (dec (apply min lens))))

(defn -main
  "Main program"
	[& args]
 	(let [
        dummy  (read-line)
        data   (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        ]
        (println (minimum-steps data))))

