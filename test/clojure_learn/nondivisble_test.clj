(ns clojure-learn.nondivisble-test
  (:require [clojure.test :refer :all]
            [clojure-learn.util :refer :all]
            [clojure-learn.nondivisible :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for nondivisble.clj
;	lein test clojure-learn.nondivisble-test
;
;To run only a specific test
;   lein test :only clojure-learn.nondivisble-test/foo
;

; compute the maximal-subset using two methods
(defn solve
  "the maximal set size such that the sum of no two numbers in a set are divisible by 2k"
  [data k]

  (defn pred
    "return true if not divisible by 2k"
    [a b]
    (not (= 0 (rem (+ a b) (* 2 k)))))

  (defn foo
    "return true if none of the pairs are divisible by 2k"
    [s]
    (every? #(pred (first %1) (second %1)) (pairs s)))

  (let [
        s (subsets data)
        not-divisible-sets (filter #(foo %) (filter #(> (count %) 1) s))  ; the sets where sum of no two elements are divisble by 2k
        set-size (map #(count %) not-divisible-sets)
        ]
    (apply max set-size)
    ))

(deftest remainders-tests
  (testing "creater the remainders hashset"
    (is (= { 0 #{5 10} 1 #{11} 3 #{8 13} 4 #{9} } (remainders [5 8 9 10 11 13] 5)))
    ))

(deftest create-pairs-tests
  (testing "creating pairs"
    ; note that each pair is modelled as a list, so the order is important
    (is (= #{'(1 1)} (create-pairs 2)))  ; (1,1) is the only possible pair
    (is (= #{'(1 2)} (create-pairs 3)))  ; possible pairs are (1 2)
    (is (= #{ '(1 3) '(2 2)} (create-pairs 4)))  ; possible pairs are (1 3) (2 2)
    (is (= #{ '(1 4) '(2 3)} (create-pairs 5)))  ; possible pairs are (1 4) (2 3)
    (is (= #{ '(1 5) '(2 4) '(3 3)} (create-pairs 6)))  ; possible pairs are (1 5) (2 4) (3 3)
    ))

(deftest maximal-subset-tests
  (testing "maximal subsets for 0 0 remainder pair"
    (let [
          data [5 10 15 20]        ; The valid sets are {5 10} or {5 20} or {10 15} or {15 20}
          k    5
          s1   (solve data k )
          s2   (size-of-maximal-subset data k)
          ]
      (is (= s1 s2))
      (is (= s2 2)))
    )
  (testing "maximal subsets"
    (let [
          data [1 7 2 3]
          k    3
          s1   (solve data k )
          s2   (size-of-maximal-subset data k)
          ]
      (is (= s1 s2)))
    (let [
          data [1 7 2 3]
          k    4
          s1   (solve data k )
          s2   (size-of-maximal-subset data k)
          ]
      (is (= s1 s2)))
    ))



