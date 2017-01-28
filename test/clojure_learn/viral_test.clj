;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for viral.clj
;	lein test clojure-learn.viral-test
;
;To run only a specific test
;   lein test :only clojure-learn.viral-test/foo
;

(ns clojure-learn.viral-test
  (:require [clojure.test :refer :all]
            [clojure-learn.viral :refer :all]))

(deftest market-test
	(testing "reach of the market on the nth day"
		(is (= 0 (market 0)))
		(is (= 2 (market 1)))
		(is (= 5 (market 2)))
		(is (= 9 (market 3)))
		))
; day 0. Sent to 5 people. list is ()
; day 1. 5/2 = 2 like it. Sent to 2x3 = 6 people. list is (2)
; day 2. 6/2 = 3 like it. Send to 3x3 = 9 people. list is (3 2)
; day 3. 9/2 = 4 like it. Send to 4x3 = 12 people. list is (4 3 2)
; day 4. return 4+3+2 = 9
; 