(ns clojure-learn.absolute-permutation-test
  (:require [clojure.test :refer :all]
            [clojure-learn.absolute-permutation :refer :all]))

;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for absolute-permutation.clj
;	lein test clojure-learn.absolute-permutation-test
;
;To run only a specific test
;   lein test :only clojure-learn.absolute-permutation/foo
;

(deftest partition-test
  (testing "partition lists with just one element"
    (is (= {:part1 '(), :pivot 10, :part2 '()} (partition 0 '(10)))))
  (testing "partition lists with just two element"
    (is (= {:part1 '(), :pivot 10, :part2 '(20)} (partition 0 '(10 20))))
    (is (= {:part1 '(10), :pivot 20, :part2 '()} (partition 1 '(10 20)))))
  (testing "partition lists with more than two element"
    (is (= {:part1 '(), :pivot 10, :part2 '(20 30)} (partition 0 '(10 20 30))))
    (is (= {:part1 '(10), :pivot 20, :part2 '(30)} (partition 1 '(10 20 30))))
    (is (= {:part1 '(10 20), :pivot 30, :part2 '()} (partition 2 '(10 20 30))))))

(deftest permutations-test
  (testing "permutations with just one element"
    (let [
          data '(1)
          expected '((1))
          ]
      (is (= expected (permutations data)))
      ))
  (testing "permutations with two elements"
    (let [
          data '(1 2)
          expected '((1 2) (2 1))
          ]
      (is (= expected (permutations data)))
      ))
  (testing "permutations with more than two elements"
    (let [
          data '(1 2 3)
          expected '((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))
          ]
      (is (= expected (permutations data)))
      ))
  )