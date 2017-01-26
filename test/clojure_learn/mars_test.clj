;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for mars.clj
;	lein test clojure-learn.mars-test
;
;To run only a specific test
;   lein test :only clojure-learn.mars-test/foo
;

(ns clojure-learn.mars-test
  (:require [clojure.test :refer :all]
            [clojure-learn.mars :refer :all]))

(deftest create-test
	(testing "create a seq of N  sos blocks"
        (is (= '(\S \O \S) (create-sos 1)))
        (is (= '(\S \O \S \S \O \S) (create-sos 2)))))

(deftest count-test
	(testing "count the number of errors in transmission"
		(is (= 0 (count-errors "SOSSOSSOS")))
		(is (= 2 (count-errors "OSS")))
		(is (= 2 (count-errors "SOSXXSSOS")))
		(is (= 4 (count-errors "SOPXXXSOS")))))