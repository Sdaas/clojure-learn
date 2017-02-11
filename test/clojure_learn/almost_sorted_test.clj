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
    (is (= {:start 3 :end 5} (descending [1 2 5 4 3 6])))
    (is (= {:start 5 :end 6} (descending [1 2 3 4 6 5])))   ; last two numbers are descending
    (is (= {:start 4 :end 6} (descending [1 2 3 6 5 4])))   ; last three numbers are descending
    (is (= {:start 1 :end 2} (descending [2 1 3 4 5 6])))   ; first two numbers are descending
    (is (= {:start 1 :end 4} (descending [4 3 2 1 5 6])))   ; first four numbers are descending
    (is (= {:start 1 :end 2} (descending [2 1])))
    (is (= {:start 1 :end 3} (descending [3 2 1])))
    (is (= {:start 1 :end 6} (descending [6 5 4 3 2 1])))   ; the entire sequence is desceding
    (is (= {:start 3 :end 5} (descending [12 17 25 23 20 29 32 40])))  ; .. 25 23 20 ... is the sequence
    ))

