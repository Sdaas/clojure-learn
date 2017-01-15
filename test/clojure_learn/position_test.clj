;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for position.clj
;	lein test clojure-learn.position-test
;
;To run only a specific test
;   lein test :only clojure-learn.position-test/string2bigint-test
;

(ns clojure-learn.position-test
  (:require [clojure.test :refer :all]
            [clojure-learn.position :refer :all]))

(deftest absent-test
 	(testing "looking for a number that is not present"
  		(is (= -1 (position 10 `()))) ; empty list
  		(is (= -1 (position 10 `(1 2 3))))))

(deftest present-test
 	(testing "looking for a number that is present"
  		(is (= 0 (position 10 `(10 20 30))))
		(is (= 6 (position 70 `(10 20 30 40 50 60 70))))))