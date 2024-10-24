import n00_utils

#[ Seqs, abbreviated from “sequence”, provide dynamically expandable storage.

There are two ways to create seqs, with the @ operator and with the newSeq[T](n:
int) method. Once a seq is created, it can be modified using the add(item: T),
delete(idx: int). The length of a seq can be found through len: int, and the
maximum index through high: int. The standard items: T and pairs: tuple[i: int,
v: T] iterators are also available. ]#

var
    a = @[1, 2, 3]
    b = newSeq[int](3)

for i, v in a:
    b[i] = v*v
echo repr a, b

for i in 4..100:
    b.add(i*i)
echo repr a, b

b.delete(0) # takes O(n) time
echo b
b = a[0] & b  # Same as original b
echo b

#[ Immutability 
Sequences are dynamically allocated (i.e. allocated on the heap,
not the stack), but they are immutable unless marked as var. That means ]#

let c = @[1, 2, 3]
# c.add(4) # It will fail to compile because a cannot be assigned to.

var d = @[1, 2, 3]
d.add(4) # It works!
echo d

#[  Sequences passed as “argument by value” are not modifiable. For example, the
following will fail to compile. ]#

# proc doSomething(mySeq: seq[int]) =
#     mySeq[0] = 2  # this is a compile-time error
# var testSeq = @[1, 2, 3]
# doSomething(testSeq)

#[ seq arguments can be mutable if they are passed as “argument by reference”,
ie. the parameter is annotated with the var or ref: ]#
proc foo(mySeq: var seq[int]) =
    mySeq[9] = 999

var thisSeq = newSeq[int](10)
echo thisSeq
foo(thisSeq)
echo thisSeq
# assert thisSeq[9] == 999
echo checkAssert(thisSeq[9] == 999)

# You can copy a sequence passed as “argument by value” and modify the copy:
proc doSomething(mySeq: seq[int]) =
    var varMySeq = mySeq  # copy the seq
    varMySeq[0] = 999
    assert varMySeq[0] == 999

var testSeq = @[1, 2, 3]
doSomething(testSeq)
assert testSeq[0] == 1