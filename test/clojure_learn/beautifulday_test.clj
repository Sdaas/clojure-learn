;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for beautifulday.clj
;	lein test clojure-learn.beautifulday-test
;
;To run only a specific test
;   lein test :only clojure-learn.beautifulday-test/foo
;

(ns clojure-learn.beautifulday-test
  (:require [clojure.test :refer :all]
            [clojure-learn.beautifulday :refer :all]))

(deftest reverse-test
	(testing "reverse an integer"
		(is (= 0 (reverse-integer 0)))
		(is (= 6 (reverse-integer 6)))
		(is (= 2 (reverse-integer 2000)))
		(is (= 2001 (reverse-integer 1002)))
		(is (= 654321 (reverse-integer 123456)))))

(deftest beautiful-test
	(testing "check if a particular day is a beautiful day"
		(is (beautifulday? 20 6))
		(is (not (beautifulday? 21 6)))
		(is (beautifulday? 22 6 ))
		(is (not (beautifulday? 23 6)))))

(deftest beautifuldays-test
	(testing "count the number of beautiful days in a range"
		(is (= 2 (beautifuldays 20 23 6)))))

