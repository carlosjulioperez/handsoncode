(ns tutorial.Destructuring)

(defn Destruct
  []
  (def myVect [1 2 3 4 5])
  (let [[a b c] myVect] (println a b c))
  (let [[a b & rest] myVect] (println a b rest) )

  (def myMap {'name "John" 'lastName "Smith" })
  (let [ {a 'name b 'lastName} myMap] (println a b) )
  (let [ {a 'name b 'lastName c 'noname} myMap] (println a b c) )

  )
(Destruct)