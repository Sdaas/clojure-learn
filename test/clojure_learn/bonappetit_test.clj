;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for bonappetit.clj
;	lein test clojure-learn.bonappetit-test
;
;To run only a specific test
;   lein test :only clojure-learn.bonappetit-test/foo
;

(ns clojure-learn.bonappetit-test
  (:require [clojure.test :refer :all]
            [clojure-learn.bonappetit :refer :all]))

(deftest anna-test
	(testing "what is anna's fair share"
		(is (= 7 (anna-share [3 10 2 9] 1)))))