(ns clojure-learn.almost-sorted-test
  (:require [clojure.test :refer :all]
            [clojure-learn.almost-sorted :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for almost-sorted.clj
;	lein test clojure-learn.almost-sorted-test
;
;To run only a specific test
;   lein test :only clojure-learn.almost-sorted-test/foo
;

(deftest descending-test
  (testing "identify the descending sub sequence"
    (is (nil? (descending [])))                 ; no descending numbers
    (is (nil? (descending [1])))                 ; no descending numbers
    (is (nil? (descending [1 2])))                 ; no descending numbers
    (is (nil? (descending (range 10))))         ; no descending numbers
    (is (= {:start 2 :end 4} (descending [1 2 5 4 3 6])))
    (is (= {:start 4 :end 5} (descending [1 2 3 4 6 5])))   ; last two numbers are descending
    (is (= {:start 3 :end 5} (descending [1 2 3 6 5 4])))   ; last three numbers are descending
    (is (= {:start 0 :end 1} (descending [2 1 3 4 5 6])))   ; first two numbers are descending
    (is (= {:start 0 :end 3} (descending [4 3 2 1 5 6])))   ; first four numbers are descending
    (is (= {:start 0 :end 1} (descending [2 1])))
    (is (= {:start 0 :end 2} (descending [3 2 1])))
    (is (= {:start 0 :end 5} (descending [6 5 4 3 2 1])))   ; the entire sequence is desceding
    (is (= {:start 2 :end 4} (descending [12 17 25 23 20 29 32 40])))  ; .. 25 23 20 ... is the sequence
    ))

(deftest fix-test
  (testing "cannot be fixed - since it is already sorted"
    (is (= {:op "no" :start 0 :end 0} (fix [])))     ; empty list
    (is (= {:op "no" :start 0 :end 0} (fix [1 2]))) ; already sorted
    (is (= {:op "no" :start 0 :end 0} (fix (range 10)))) ; already sorted
    )
  (testing "cannot be fixed"
    (is (= {:op "no" :start 0 :end 0} (fix [ 30 10 20 ])))     ; descending sequence in start
    (is (= {:op "no" :start 0 :end 0} (fix [ 1 5 30 10 20 ]))) ; descending sequence in middle
    (is (= {:op "no" :start 0 :end 0} (fix [ 1 5 20 30 10 ]))) ; descending sequence in end
    )
  (testing "can be fixed by swapping"
    (is (= {:op "swap" :start 1 :end 2} (fix [20 10 30 40 50 ]))) ; swap the first two
    (is (= {:op "swap" :start 4 :end 5} (fix [10 20 30 50 40 ]))) ; swap the last two
    (is (= {:op "swap" :start 3 :end 4} (fix [10 20 40 30 50 ]))) ; swap the ones in middle
    )
  (testing "can be fixed by reversing"
    (is (= {:op "reverse" :start 1 :end 3} (fix [30 20 10 40 50 60]))) ; reverse a sequence at start
    (is (= {:op "reverse" :start 4 :end 6} (fix [10 20 30 60 50 40]))) ; reverse a sequence at end
    (is (= {:op "reverse" :start 3 :end 5} (fix [10 20 50 40 30 60]))) ; reverse a sequence in middle
    ))