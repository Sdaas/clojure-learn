;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for triplet.clj
;	lein test clojure-learn.triplet-test
;
;To run only a specific test
;   lein test :only clojure-learn.triplet-test/fubar-test
;

(ns clojure-learn.triplet-test
  (:require [clojure.test :refer :all]
            [clojure-learn.triplet :refer :all]))

(deftest parse-test
  (testing "parsing the input"
  	(is (= (list 10 20 30) (parse "10 20 30")))
	(is (= (list 10 20 40) (parse "10    20 40")))))

(deftest triplet-test
	(testing "triplet"
		(is (= {:alice 0 :bob 0} (triplet (list 10 20 30) (list 10 20 30))))
		(is (= {:alice 3 :bob 0} (triplet (list 10 20 30) (list 9 19 29))))
		(is (= {:alice 0 :bob 3} (triplet (list 10 20 30) (list 11 21 31))))
		(is (= {:alice 1 :bob 1} (triplet (list 10 20 30) (list 9 20 31))))))