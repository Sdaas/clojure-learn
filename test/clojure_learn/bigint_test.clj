;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for bigint.clj
;	lein test clojure-learn.bigint-test
;
;To run only a specific test
;   lein test :only clojure-learn.bigint-test/string2bigint-test
;

(ns clojure-learn.bigint-test
  (:require [clojure.test :refer :all]
            [clojure-learn.bigint :refer :all]))

(deftest foo
  (testing "Foo"
    (testing "converting 0" 
    	(is (= {:negative false :number [1 0 2]} (string2bigint "201")) "Should return  + [0]"))))

(deftest string2bigint-zero
	(testing "converting 0" 
    	(is (= {:negative false :number [0]} (string2bigint "0")) "Should return  + [0]")))
  
(deftest string2bigint-positive
  (testing "postive integers"
    	(is (= {:negative false :number [1]} (string2bigint "1")) "Should return  + [1]")
    	(is (= {:negative false :number [0 1]} (string2bigint "10")) "Should return  + [0 1]")
    	(is (= {:negative false :number [0 0 4]} (string2bigint "400")) "Should return  + [0 0 4]")
    	(is (= {:negative false :number [2 6 0 1]} (string2bigint "1062")) "Should return  + [2 6 0 1]")
    	(is (= {:negative false :number [7 2 1 9 3 2 1 5 0 4 0 2]} (string2bigint "204051239127")) "Should return  + [7 2 .. 0 2]")))

(deftest string2bigint-negative
  (testing "negative integers"
    	(is (= {:negative true :number [1]} (string2bigint "-1")) "Should return  - [1]")
    	(is (= {:negative true :number [0 1]} (string2bigint "-10")) "Should return  - [0 1]")
    	(is (= {:negative true :number [0 0 4]} (string2bigint "-400")) "Should return  - [0 0 4]")
    	(is (= {:negative true :number [2 6 0 1]} (string2bigint "-1062")) "Should return  - [2 6 0 1]")
    	(is (= {:negative true :number [7 2 1 9 3 2 1 5 0 4 0 2]} (string2bigint "-204051239127")) "Should return  - [7 2 .. 0 2]")))

(deftest string2bigint-test
  (string2bigint-zero)
  (string2bigint-positive)
  (string2bigint-negative))

(deftest bigint2string-test
	(testing "zero"
		(is (= "0" (bigint2string {:negative false :number [0]})))
		(is (= "-0" (bigint2string {:negative true :number [0]}))))
	(testing "positive numbers"
		(is (= "1" (bigint2string {:negative false :number [1]})))
		(is (= "10" (bigint2string {:negative false :number [0 1]})))
		(is (= "400" (bigint2string {:negative false :number [0 0 4]})))
		(is (= "90205" (bigint2string {:negative false :number [5 0 2 0 9]}))))
	(testing "negative numbers"
		(is (= "-1" (bigint2string {:negative true :number [1]})))
		(is (= "-10" (bigint2string {:negative true :number [0 1]})))
		(is (= "-400" (bigint2string {:negative true :number [0 0 4]})))
		(is (= "-90205" (bigint2string {:negative true :number [5 0 2 0 9]})))))

(deftest zero-test
	(testing "positive zero"
		(is (= true (is-zero? {:negative false :number [0]}))))   ; positive zero
	(testing "negative zero"
		(is (= true (is-zero? {:negative true :number [0]})))) 
	(testing "non-zero"
		(is (= false (is-zero? {:negative false :number [1]})))
		(is (= false (is-zero? {:negative true  :number [0 0 1]})))))   

(deftest equals-test
	(testing "positive numbers"
		(is (= true (equal? {:negative false :number [1 2 3]} {:negative false :number [1 2 3]} )))
		(is (= false (equal? {:negative false :number [1 2 3]} {:negative false :number [1 2 3 4]} )))
		(is (= false (equal? {:negative false :number [1 2 3]} {:negative false :number [1 4 3]} ))))
	(testing "negative numbers"
		(is (= true (equal? {:negative true :number [1 2 3]} {:negative true :number [1 2 3]} )))
		(is (= false (equal? {:negative true :number [1 2 3]} {:negative true :number [1 2 3 4]} )))
		(is (= false (equal? {:negative true :number [1 2 3]} {:negative true :number [1 4 3]} ))))
	(testing "different sign on the same value"
		(is (= false (equal? {:negative false :number [1 2 3]} {:negative true :number [1 2 3]} )))
		(is (= false (equal? {:negative true :number [1 2 3]} {:negative false :number [1 2 3]} ))))
	(testing "zero"
		(is (= true (equal? {:negative false :number [0]} {:negative false :number [0]} )))
		(is (= true (equal? {:negative true :number [0]} {:negative true :number [0]} )))
		(is (= true (equal? {:negative true :number [0]} {:negative false :number [0]} )))))

(deftest add-zero-test
	(testing "add zero to zero"
		(is (= true (equal? {:negative false :number [0]}  (add {:negative false :number [0]} {:negative false :number [0]}) ))))
	(testing "add zero to non-zero"
		(is (= true (equal? {:negative false :number [1 2 3]}  (add {:negative false :number [0]} {:negative false :number [1 2 3]}) )))
		(is (= true (equal? {:negative false :number [4 5 6]}  (add {:negative false :number [4 5 6]} {:negative false :number [0]}) )))))
	

(deftest add-positive-test
	(testing "same size no carry"
		(is (= true (equal? {:negative false :number [3 5 7]}  (add {:negative false :number [1 2 3]} {:negative false :number [2 3 4]}) ))))
	(testing "different size no carry"
		(is (= true (equal? {:negative false :number [3 5 4 5]}  (add {:negative false :number [1 2]} {:negative false :number [2 3 4 5]}) ))))
	(testing "with carry"
		(is (= true (equal? {:negative false :number [2 7]}  (add {:negative false :number [3 2]} {:negative false :number [9 4]}) )))
		(is (= true (equal? {:negative false :number [2 1 1]}  (add {:negative false :number [7 4]} {:negative false :number [5 6]}) )))


		)
	)


