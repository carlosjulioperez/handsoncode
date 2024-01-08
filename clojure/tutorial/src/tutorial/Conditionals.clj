(ns tutorial.Conditionals)

  (defn CondIf
  []
  (println "\nCondIf:")
  (if (= 5 4)
    (println "Equal")
    (println "Not Equal")
    ))
(CondIf)

(defn CondIfDo
  []
  (println "\nCondIfDo:")
  (if (= 5 6)
    (do (println "Equal first statement")
        (println "Second statement"))
    (do (println "Not equal first statement")
        (println "Second statement"))

    ))
(CondIfDo)

(defn CondNestIf
  []
  (println "\nCondNestIf:")
  (if (and (= 5 5) (= 2 3))
    (println "True")
    (println "False")
  ))
(CondNestIf)

(defn CondCase
  [pet]
  (println "\nCondCase:")
  (case pet
    "cat" (println "I have a cat")
    "dog" (println "I have a dog")
    "fish" (println "I have a goldfish")
    ))
(CondCase "dog")

(defn CondCond
  [amount]
  (cond                                                     ;evaluate expressions in order
    (<= amount 2) (println "Few")
    (<= amount 10) (println "Several")
    (<= amount 100) (println "Many")
    :else (println "Loads")
    ))
(CondCond 5)