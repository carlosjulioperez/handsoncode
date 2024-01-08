(ns tutorial.petstore)
(defn petToHumanAge
  "This function return the age a pet in human years"
  [x]                                                       ;type of pet
  (def petStore {'dog 7, 'cat 5, 'goldfish 10})             ;define a map with name and ratio of pet
  (get petStore x))                                         ;get the value of petStore of variable x

(defn age
  "This function returns the age of a pet"
  [petName petType petAge]                                  ;parameters
  (def ratio (petToHumanAge petType))                       ;define variable of petType's ratio
  (println petName "is" (* ratio petAge)
           "years old in human years" ))

(age "Fido" 'dog 4)
(age "Fifi" 'cat 2)
(age "Bubbles" 'goldfish 10)
