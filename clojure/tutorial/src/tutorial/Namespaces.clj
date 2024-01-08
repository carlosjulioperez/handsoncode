(ns tutorial.Namespaces
  ;(:require [clojure.string :refer [capitalize]]) )
   (:require [clojure.string :refer :all]))

(defn -main
  []
  ;(println (clojure.string/capitalize "Hello"))
  (println (capitalize "Hello"))
  )
(-main)