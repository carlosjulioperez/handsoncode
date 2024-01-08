(ns tutorial.Agents)

(defn Agents
  []
  (def amount (agent 100))
  (println @amount)

  (send amount inc)
  (println @amount)
  (println "Some time must pass")
  (println @amount)

  (println (agent-error amount))
  )
(Agents)