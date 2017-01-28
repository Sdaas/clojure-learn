(ns clojure-learn.encryption
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Encrypt a string
; 
; https://www.hackerrank.com/challenges/encryption
;

(defn remove-spaces
    "remove all spaces from a string and return a sequence"
    [str]
    (filter #(not (= % \space )) (seq str)))

(defn grid
    "convert a sequence of charactes into a grid"
    ([s] 
        (let [
            len (int (Math/ceil (Math/sqrt (count s))))
        ]
        (grid [] s len)))
    ([acc s len]
        (cond
            (empty? s) acc
            (< (count s) 4) (conj acc s)
            :else (recur (conj acc (take len s)) (drop len s) len)
        )))

(defn scan
    "given a grid scan it vertically"
    [grid]

    (defn empty-grid? 
        "returns true if the grid is empty, false otherwise"
        [grid]
        (= 0 (reduce + (map #(count %) grid))))

    (defn inner
        [acc grid]
        (if (empty-grid? grid)
            acc
            (let [
                f   (filter #(some? %) (map #(first %1) grid))
                r   (map #(rest %1) grid)
                newacc (conj acc f)
                ]
                (inner newacc r))))

    (inner [] grid))

(defn tostring
    "convert the scanned grid to a single string"
    [s]
    (let [
        slist (map #(apply str %) s)
        ]
        (join " " slist)))

(defn -main
  "Main program"
	[& args]
 	(let [
        str  (read-line)   
        g    (grid (remove-spaces str))
        slist    (scan g)
        s    (tostring slist)
        ]
        (println s)))

