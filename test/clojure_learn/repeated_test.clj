;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for repeated.clj
;	lein test clojure-learn.repeated-test
;
;To run only a specific test
;   lein test :only clojure-learn.repeated-test/foo
;

(ns clojure-learn.repeated-test
  (:require [clojure.test :refer :all]
            [clojure-learn.repeated :refer :all]))

(deftest occurences-test
	(testing "zero occurrences"
		(is (= 0 (occurences "hello" 0)))
		(is (= 0 (occurences "aaaaaa" 0)))
		(is (= 0 (occurences "hello" 3)))
		(is (= 0 (occurences "hello" 12)))
	))