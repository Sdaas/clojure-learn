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

(defn my-min
   "a minimum function that works on both chars and int"
   [a b]
   (let [
        r (compare a b)
         ]
         (cond 
              (= r 0)  a
              (< r 0) a   ; a < b
              :else b)))

(defn next-larger-in-list
    "the smallest number in data that is larger than n"
    ; returns nil if no match
    [n data]
    (let [
         less-than (filter #(> (compare %1 n) 0 ) data)
         ]
         (if (empty? less-than)
             nil
             (reduce my-min less-than))))  

(defn remove-from-list
   "removes the first occurence of x from data"
   [x data]
  
   (defn pred [item] (not (= 0 (compare x item))))
   (let [
        p1 (take-while pred data)
        p2 (rest (drop-while pred data))
        ]
        (concat p1 p2)))

(defn smallest
    "the smallest number than can be made with the digits in data"
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
                        n1 (next-larger-in-list d1 r1) ; the smallest number in r1 such that r1 > d1
                        ]
                        (if (nil? n1)
                              nil
                              (cons n1 (smallest (cons d1 (remove-from-list n1 r1))))))))))
                  
(defn read-n
    "read n elements from stdin into a vector"
    [n]
    (loop [
            x n 
            acc []
        ]
        (if (= x 0)
            acc
            (recur (dec x) (conj acc (read-line))))))

(defn process
    [data]
    (let [
        tmp (next-largest (seq data))
        ]
        (if (nil? tmp) "no answer" (apply str tmp))))


(defn -main
  "Main program"
	[& args]
 	(let [
        n  (Integer/parseInt (read-line))
        data (read-n n)
        out  (map #(process %) data)
        ]
        (doseq [item out] (println item))))

