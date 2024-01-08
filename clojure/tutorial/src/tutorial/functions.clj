(ns tutorial.functions
  (:gen-class))

(defn -main
  "Fisrt function"
  []
  (println "My name is John")
  (println "Loving Clojure so far")
  (+ 2 5))

#(println "Hello" %)
(def increment (fn [x] (+ x 1)))

(defn increment_set
  [x]
  (map increment x))

(def greet (fn [name] (str "Hello, " name)))

(defn DataTypes []
  (def a 1)
  (def b 1.25)
  (def c 1.25e-12)
  (def d 0xfbfc)
  (def e nil)
  (def f true)
  (def g "Hello")
  (def h 'thanks)                                           ;keyword
  (def status true)
  (def STATUS false)

  (println a)
  (println b)
  (println c)
  (println d)
  (println e)
  (println f)
  (println g)
  (println h)
  (println status)
  (println STATUS)
  )
(DataTypes)

#(println (- (/ (+ 2 5) 3) 1) )