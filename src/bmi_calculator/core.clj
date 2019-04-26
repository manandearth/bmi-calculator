(ns bmi-calculator.core
  "Calculates Body Mass Index given weight and height"
  (:require [clojure.spec.alpha :as spec]
   [orchestra.core :refer [defn-spec]]
            [orchestra.spec.test :as st])
  (:gen-class))

(spec/def ::weight double?)
(spec/def ::height double?)
(spec/def ::bmi    (spec/and pos? double?))
(spec/def ::present-string (spec/and string? (complement empty?)))
(spec/def ::values-map (spec/keys :req-un [::weight ::height]))

(defn-spec bmi ::bmi
  [{:keys [weight height] :as values-map} ::values-map]
  (/ weight (Math/pow height 2)))

(defn-spec statement ::present-string
  [num ::bmi] (cond
                (<= num 0)  "not likely"
                (<= num 16) "dangerously underweight"
                (<= num 20) "underweight"
                (<= num 25) "normal"
                (<= num 30) "overweight.."
                (<= num 40) "obese"
                (<= num 60) "extremely obese"
                :else      "not possible"))

(def thoughtful-remarks
  ["Which falls in the category"
   "Which is"
   "In other words"
   "Otherwise known as"
   "I suppose it is"
   "As far as I know it is"
   "If I am not mistaken it is"])

(defn-spec print-result ::present-string
  "takes weight and height values and prints back result"
  [values-map ::values-map]
  (let [result (bmi values-map)]
    (println  "Your Body/Mass index is: " result)
    (println  (rand-nth thoughtful-remarks) (statement result))
    result))

(defn get-input 
  "get user input"
  []
  (let [input (clojure.string/trim (read-line))]
     input))

(defn prompt-height [weight]
  (println "What is your height? [in cm]")
  (let [height (/ (Double/parseDouble (get-input)) 100)]
    (print-result {:weight weight :height height})))

(defn prompt-weight
  []
  (println "What is your weight? [in kg]")
  (let [weight (Double/parseDouble (get-input))]
    (prompt-height weight)))


(defn -main [& args]
  (println "BMI calculator")
  (println "==============")
  (println "")
  (prompt-weight))
