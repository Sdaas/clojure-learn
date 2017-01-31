(ns clojure-learn.biggergreater
  (use [clojure.string :only (split join triml)])
  (:gen-class))

; Bigger Is Greater
; 
; https://www.hackerrank.com/challenges/bigger-is-greater
;
; Given a word w, rearrange the letters of w to construct another 
; word s in such a way that s is lexicographically greater than w. In case of multiple possible answers, find the lexicographically smallest one among them


; Lets first try out the technique with numbers. Anythign that works
; on numbers will also work on a seq of characters.
;
; function next-largest()  2143
; [1] call next-largest on the last n-1 significant digits.
;       143
; if that does not work, then split the number into (first) and (rest).
;       first = 2
;       rest  = 1 4 3 
; find the smallest number in (rest) that larger than (first)
;       now first = 3
;       and rest  = 2 1 4
; Find the smallest number using rest (sort them and cat)
;       3 1 2 4
;
; if this also does not work, then there is no answer

(defn next 
    "the smallest number in data that is larger than n"
    ; returns nil if no match
    [n data]
    (let [
         less-than (filter #(> %1 n) data)
         ]
         (if (empty? less-than)
             nil
             (apply min less-than))))

(defn remove
    "remove x from data"
    [x data]
    (filter #(not (= %1 x)) data)
 )

(defn smallest
    "the smalesst number than can be made with the digits in data"
     [data]
     (sort data))

(defn next-largest
    "find the next largest number"
    [s]
    (if (= 0 (count s))
        nil
        (let [
               d1 (first s)    ; the first letter 
               r1 (rest s)     ; the remaining letters
               s1 (next-largest r1)  ; solve using the n-1 digits
               ]
               (if (not (nil? s1))
                   (cons d1 s1)
                   (let [
                        n1 (next d1 r1) ; the smallest number in r1 such that r1 > d1
                        ]
                        (if (nil? n1)
                              nil
                              (cons n1 (smallest (cons d1 (remove n1 r1))))))))))
                  

(defn -main
  "Main program"
	[& args]
 	(let [
        h (Integer/parseInt (read-line))
        m (Integer/parseInt (read-line))
        ]
        (println "hello")))

