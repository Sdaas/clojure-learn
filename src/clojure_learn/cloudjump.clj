(ns clojure-learn.cloudjump
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Jumping on the cloud - revisited
;
; https://www.hackerrank.com/challenges/jumping-on-the-clouds-revisited
;


(defn visited
  "list of nodes that were visited"
  [n k]

  (defn next-cloud
    [i]
    (rem (+ i k) n)
    )

  (let [
        first-cloud (next-cloud 0)
        clouds (into [] (take-while #(not (= % 0)) (iterate next-cloud first-cloud)))
        ]
    (conj clouds 0)))

(defn energy
  "amount of energy spent in visiting these clouds"
  [visited clouds]
  (reduce + (map #(+ 1 (* 2 (clouds %1))) visited))
  )

(defn -main
  "Main program"
  [& args]
  (let [
        [n k]   (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
        clouds  (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
        v       (visited n k)                               ; list of visited nodes/clouds
        e       (energy  v clouds)                          ; amount of energy consumed
        remain  (- 100 e)                                   ; energy remaining
        ]
    (println remain)))


