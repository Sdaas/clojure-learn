(ns clojure-learn.beautiful-triplet-test
  (:require [clojure.test :refer :all]
            [clojure-learn.beautiful-triplet :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for beautiful-triplet.clj
;	lein test clojure-learn.beautiful-triplet-test
;
;To run only a specific test
;   lein test :only clojure-learn.beautiful-triplet/foo
;

(deftest create-hashmap-test
  (testing "create hashmaps from data"
    (let [
          data [1 4 2]
          expected {1 [1 4] 2 [2]}
          ]
      (is (= expected (create-hashmap data 3))))
    ))

(deftest beautiful-test
  (testing "test to see if a triplet is beautiful"
    (is (= true (beautiful? 3 [1 4 7])))
    (is (= false (beautiful? 3 [1 4 10])))))

(deftest triplets-test
  (testing "generate all the triplets"
   (is (= [] (triplets [])))
   (is (= [] (triplets [10 20])))
   (is (= [[10 20 30]] (triplets [10 20 30 ])))
   (is (= [[1 4 7] [4 7 10] [7 10 16]] (triplets [1 4 7 10 16])))
    ))

