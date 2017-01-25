;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for pdfviewer.clj
;	lein test clojure-learn.pdfviewer-test
;
;To run only a specific test
;   lein test :only clojure-learn.pdfviewer-test/foo
;

(ns clojure-learn.pdfviewer-test
  (:require [clojure.test :refer :all]
            [clojure-learn.pdfviewer :refer :all]))

(deftest height-test
	(testing "find the max heigh for a string"
		(is (= 3 (max-height "abc" [1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5])))
		))

(deftest area-test
	(testing "find the area for the string"
		(is (= 9 (area "abc" [1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5])))
		(is (= 70 (area "qjhwkcexec" [4 2 1 2 3 4 3 7 4 1 5 6 1 3 2 6 6 3 7 3 1 1 5 1 1 4])))
		))
