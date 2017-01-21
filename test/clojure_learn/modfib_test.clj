;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for modfib.clj
;	lein test clojure-learn.modfib-test
;
;To run only a specific test
;   lein test :only clojure-learn.modfib-test/foo
;

(ns clojure-learn.modfib-test
  (:require [clojure.test :refer :all]
            [clojure-learn.modfib :refer :all]))

;nkm
(deftest solve-test1
	(testing "test1"
		(is (= 1 (modfib 1 2 1)))
		(is (= 2 (modfib 1 2 2)))
		(is (= 5 (modfib 1 2 3)))
		(is (= 27 (modfib 1 2 4)))))