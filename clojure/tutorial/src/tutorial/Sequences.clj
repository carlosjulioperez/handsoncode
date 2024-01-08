(ns tutorial.Sequences)

(defn Seq
  []
  (def colors (seq ["red" "green" "blue"]))                 ;define a vector (println colors)

  (println (cons "yellow" colors))                          ;append element to a sequence
  (println (cons colors "yellow"))

  (println (conj colors "yellow"))                          ;adds yellow at the beginning
  (println (conj ["red" "green" "blue"] "yellow"))          ;vector adds the end

  (println (concat colors (seq ["black" "white"])))
  (println (distinct (seq [1 2 3 5 3 2 4])))

  (println (reverse colors) )

  (println (first colors))
  (println (rest colors))
  (println (last colors))

  (println (sort (seq [1 2 3 5 3 2 4] )) )
  )
(Seq)
