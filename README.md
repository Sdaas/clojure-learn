# clojure-learn

TODO Description

## List of Modules
* `bigint`  : For infinite precision integer arithmetic
* `diagnonal` :
* `triplet` : 
* `clock`   : [convert time from 12-hour format to 24-hour format](https://www.hackerrank.com/challenges/time-conversion)
* `position` : [find the index of the given number in an array](https://www.hackerrank.com/challenges/tutorial-intro)

## Using the Gorilla Repl

* Add `  :plugins [[lein-gorilla "0.4.0"]]` to `project.clj`
* Run `lein gorilla` to launch the repl

## Adding a new module
To add a new module called `XXX`

### Code
* Add a new file `src/XXX.clj`
* At the top of the file, add the name space for this module
 ```
(ns clojure-learn.XXX (:gen-class))
```

### Test Cases

* Add a new file test/XXX_test.clj
* At the top of the file add the namespace of the test and all the dependencies
```
(ns clojure-learn.XXX-test
  (:require [clojure.test :refer :all]
            [clojure-learn.bigint :refer :all]))
```
To run all tests in project
```
lein test
```
To run all tests for `XXX`
```
lein test clojure-learn.XXX-test
```
To run only a specific test
```
lein test :only clojure-learn.bigint-test/name_of_the_test
```

## References
* [Github markdown editor and viewer](http://dillinger.io/)
* [Clojars](https://clojars.org/) - repo for open source clojure modules 
* [Gorilla Repl](http://gorilla-repl.org/) - A notebook type repl for Clojure (think Jupyter ...)


Profling Clojure Code https://torsten.io/stdout/how-to-profile-clojure-code/
using (VisualVM)[https://visualvm.github.io/]

## HackerRank Notes

The [HackerRank Environment for Clojure](https://www.hackerrank.com/environment) imposes a 8second time limit for execution, and a maximum memory consumption of 512MB

## TODO
### General
* Support for debug printing

### bigint
* SUBTRACT works only for +ve numbers and where v1 > v2
* no support for MULT, DIV, or MOD


