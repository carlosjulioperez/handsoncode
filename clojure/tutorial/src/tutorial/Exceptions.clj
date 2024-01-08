(ns tutorial.Exceptions)

(defn ExHandling
  [x]
  (try
     (inc x)
     (catch ClassCastException e (println "Cough exception:" (.getMessage  e)))
     (catch Exception e (println "Cought generic exception:" ))
     (finally (println "cleanup and move on") )
  )
)
(ExHandling "hello")