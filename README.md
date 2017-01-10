# clojure-learn

TODO Description

## List of Modules
* `bigint`  : For infinite precision integer arithmetic

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


## TODO
### General
* Support for debug printing

### bigint
* Add support for negative numbers


