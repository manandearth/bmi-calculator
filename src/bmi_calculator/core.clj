(ns bmi-calculator.core
  "Calculates Body Mass Index given weight and height"
  (:require [orchestra.core :refer [defn-spec]]
            [orchestra.spec.test :as st])
  (:gen-class))

(defn-spec bmi double?
  [weight double? height double?]
  (/ weight (Math/pow height 2)))

(defn-spec statement any?
  [num pos?] (cond
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

(defn-spec print-result any?
  [weight double? height double?]
  (let [result (bmi weight height)]
    (println (str "Your Body/Mass index is: " result))
    (Thread/sleep 2000 )
    (println  (rand-nth thoughtful-remarks) (statement result))))

(defn get-input 
  "Waits for user to enter text and hit enter, then cleans the input"
  []
  (let [input (clojure.string/trim (read-line))]
     input))

(defn prompt-height [weight]
  (println "What is your height? [in cm]")
  (let [height (/ (Double/parseDouble (get-input)) 100)]
    (print-result weight height)))

(defn prompt-weight
  []
  (println "What is your weight? [in kg]")
  (let [weight (Double/parseDouble (get-input))]
    (prompt-height weight)))

(st/instrument)

(defn -main [& args]
  (println "BMI calculator")
  (println "==============")
  (println "")
  (prompt-weight))
