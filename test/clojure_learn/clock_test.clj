;Note that the test must be in a XXX_test file (underscore)
;
;To run all tests in project
;  lein test
;
;To run all tests for clock.clj
;	lein test clojure-learn.clock-test
;
;To run only a specific test
;   lein test :only clojure-learn.clock-test/fubar-test
;

(ns clojure-learn.clock-test
  (:require [clojure.test :refer :all]
            [clojure-learn.clock :refer :all]))


(deftest whitespace-test
	(testing "handle whitespace"
  		(is (= "01:00:00" (military_time "01:00:00AM")))
  		(is (= "01:00:00" (military_time "01:00:00 AM")))))

(deftest seconds-test
	(testing "seconds are optional"
  		(is (= "01:00:00" (military_time "01:00 AM")))
		(is (= "01:00:00" (military_time "01:00:00 AM")))))

(deftest hours-test
	(testing "hours may have 1 or two digits"
  		(is (= "01:00:00" (military_time "1:00 AM")))
		(is (= "01:00:00" (military_time "01:00:00 AM")))))

(deftest midnight
	(testing "midnight"
  		(is (= "00:00:00" (military_time "12:00:00 AM")))
		(is (= "00:00:00" (military_time "12:00 AM")))))

(deftest am-test
	(testing "AM"
		(is (= "00:01:00" (military_time "12:01:00 AM")))
		(is (= "01:23:45" (military_time "1:23:45 AM")))
		(is (= "11:59:00" (military_time "11:59:00 AM")))))

(deftest pm-test
   (testing "PM"
	  	(is (= "12:00:00" (military_time "12:00:00 PM")))
  		(is (= "13:23:45" (military_time "1:23:45 PM")))
		(is (= "23:59:00" (military_time "11:59:00 PM")))))	


; (re-find #"(\d+):(\d+):(\d+)(\s*)*" "12:34:56")
; upper case and lower case am/pm