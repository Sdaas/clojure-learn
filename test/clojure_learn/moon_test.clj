;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for moon.clj
;	lein test clojure-learn.moon-test
;
;To run only a specific test
;   lein test :only clojure-learn.moon-test/string2bigint-test
;

(ns clojure-learn.moon-test
  (:require [clojure.test :refer :all]
            [clojure-learn.moon :refer :all]))


(deftest merge-test
	(testing "merge-test"
		(is (= #{ #{1 2 3}} (merge-sets #{ #{1 2} #{2 3} } 2 3)))
		(is (= #{ #{1} #{2 3} #{4}} (merge-sets #{ #{1} #{2} #{3} #{4}} 2 3)))))

(deftest choose-test
 	(testing "ways to choose astronauts from countries"
  		(is (= 0 (ways-to-choose-pair `())))
  		(is (= 0 (ways-to-choose-pair `(5))))
  		(is (= 4 (ways-to-choose-pair `(2 2))))
  		(is (= 26 (ways-to-choose-pair `(4 3 2))))
		(is (= 23 (ways-to-choose-pair `(7 2 1))))))