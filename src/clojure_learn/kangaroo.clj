(ns clojure-learn.kangaroo
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Kangaroo
;
; https://www.hackerrank.com/challenges/kangaroo
;

; x1 < x2
; After n iterations
; position of kangaroo #1 = x1 + n.v1
; position of kangaroo #2 = x2 + n.v2
;
; For these to be same  x1 + n.v1 = x2 + n.v2
; there must be an integer n such that
; n = (x2-x1)/(v1-v2)


(defn meet?
  [x1 v1 x2 v2]
  (let [
        dx (- x2 x1)
        dv (- v1 v2)]
    (if (= 0 dv)
        (= 0 dx)                                            ; if the speed is same, the only way they can meet is if x1=x2
        (let [
             n (quot dx dv)
             r (rem dx dv) ]
          (and (> n 0) (= r 0))))))

(defn -main
  "Main program"
  [& args]
  (let [
        [x1 v1 x2 v2]    (map #(Integer/parseInt %) (split (read-line) #"\s+"))
        output (if (meet? x1 v1 x2 v2) "YES" "NO")
        ]
    (println output)))

