(ns clojure-learn.pdfviewer
  (use [clojure.string :only (split triml)])
  (:gen-class))

; Fancy PDF Viewer
; 
; Solution for https://www.hackerrank.com/challenges/designer-pdf-viewer
;

(defn max-height
    [str h]
    (let [
         indices  (map #(- (int %) 97) (seq str))   ; \a is ascii 97 -> map that to index 0
         heights  (map #(h %) indices) 
         ]
         (apply max heights)))
         
(defn area
    [str h]
    (* (count str ) (max-height str h)))

(defn -main
    "Main loop"
      [& args]
    (let [
          h (into [] (map #(Integer/parseInt %) (split (read-line) #"\s+")))
          word (read-line)     
        ]
        (println (area word h))))