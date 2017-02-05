(ns clojure-learn.strange-counter-test
  (:require [clojure.test :refer :all]
            [clojure-learn.strange-counter :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for strange-counter.clj
;	lein test clojure-learn.strange-counter-test
;
;To run only a specific test
;   lein test :only clojure-learn.strange-counter-test/foo
;

(deftest countdown-test
  (testing "tests for countdown sequence test"
    (is (= 1 (countdown-sequence 1)))
    (is (= 1 (countdown-sequence 3)))
    (is (= 2 (countdown-sequence 4)))
    (is (= 2 (countdown-sequence 9)))
    (is (= 3 (countdown-sequence 10)))
    (is (= 3 (countdown-sequence 15)))
    (is (= 3 (countdown-sequence 21)))
    (is (= 4 (countdown-sequence 22)))
    ))

(deftest start-test
  (testing "tests for value at the start of a sequence"
    (is (= 3 (value-at-start-of-sequence 1)))
    (is (= 6 (value-at-start-of-sequence 2)))
    (is (= 12 (value-at-start-of-sequence 3)))
    (is (= 24 (value-at-start-of-sequence 4)))
    ))

(deftest time-test
  (testing "tests for time at the start of a sequence"
    (is (= 1 (time-at-start-of-sequence 1)))
    (is (= 4 (time-at-start-of-sequence 2)))
    (is (= 10 (time-at-start-of-sequence 3)))
    (is (= 22 (time-at-start-of-sequence 4)))
    ))

(deftest display-test
  (testing "tests for time at the start of a sequence"
    (is (= 3 (display-value 1)))
    (is (= 1 (display-value 3)))
    (is (= 6 (display-value 4)))
    (is (= 1 (display-value 9)))
    (is (= 12 (display-value 10)))
    (is (= 7 (display-value 15)))
    (is (= 1 (display-value 21)))
    (is (= 24 (display-value 22)))
    ))


