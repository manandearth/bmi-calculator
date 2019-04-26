(ns bmi-calculator.core-test
  (:require [clojure.test :refer :all]
            [bmi-calculator.core :refer :all]))

(deftest bmi-test
  (testing "Testing range and spec"
    (are [expected arg-map]
        (= expected (bmi (:x arg-map) (:y arg-map)))
      0.01 {:x 100.0 :y 100.0}
      1.0 {:x 100.0 :y 10.0}
      )))

(defn get-input-helper
  "replacing the user stdin string"
  []
  (str 180.0))

;;(prompt-height get-input-helper 77.0)
;;(Double/parseDouble (get-input-helper))

;;TODO make this useful
(deftest input-test
  (print-result 73.0 170.0)
  (is (= nil (prompt-height get-input-helper 77.0) )))


(deftest results-test
  (testing "Testing expected strings"
    (are [expected weight height]
        (.contains (with-out-str (print-result {:weight weight :height height})) expected)
      "underweight"       45.0   1.63
      "normal"            73.0   1.75
      "not possible"      273.0  1.73
      "overweight"        73.0   1.68
      "dangerously"       35.0   1.65
      "obese"             89.0   1.65
      "extremely"         120.0  1.65
      "not likely"        0.0    1.73)))

