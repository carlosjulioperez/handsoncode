#[
Objects 
In Nim, objects are like structs from C family languages and define a
grouping of fields. They are by default traced by the garbage collector, so
there is no need to explicitly free them when allocated.
]#
type Animal* = object
    name*, species*: string
    age: int

proc sleep*(a: var Animal) =
    a.age += 1

proc dead*(a: Animal): bool =
    result = a.age > 20

var carl: Animal
carl = Animal(name: "Carl", species: "L. glama", age: 12)

let joe = Animal(name: "Joe", species: "H. sapiens", age: 23)

assert(not carl.dead)
for i in 0..10:
    carl.sleep
assert carl.dead

echo carl, joe

#[
Object types are declared in a type section, as usual. They can be exported, and
individual fields can also be exported. Fields can be safely exported without
violating encapsulation because call syntax is equivalent between them.

Initially, carl is created on the stack and initialized to zeros (or "" in case
of fields of type string), so its value is [name = "", species = "", age = 0].
It is mutable, so that means that the contents of carl can be changed. This also
means it can be passed to functions that require a variable parameter, like
sleep(), which can modify its value.

joe is also created on the stack, but it’s contents are immutable and can not be
changed. Attempting to do so, say through joe.age = 57, will fail with an error
at compile time.
]#

let mittens: ref Animal = new(Animal)
mittens.name = "Mittens"
mittens.species = "P. leo"
mittens.age = 6
echo mittens.name, mittens.species, mittens.age
echo repr mittens

#[
mittens is a reference to an object allocated on the heap. The value of mittens
cannot be changed, so mittens can never point to anything else, but the value
that mittens is pointing at can and is changed from the default initialization
value of zeros.

You might ask whether there is a more concise way of initializing reference
types, and there is if you give the reference type a name:
]#
type AnimalRef* = ref Animal

let spot = AnimalRef(name:"Spot", species: "C. lupus", age: 2)
echo repr spot
#[
In many cases it is only wanted to have the object be a reference type, which is
possible by declaring it as a ref object.
]#

type
  Thing* = ref object
    positionX*, positionY*: int

let cosa = Thing(positionX: 12, positionY: 23)
cosa.positionX = 100
echo repr cosa