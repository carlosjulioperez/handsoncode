proc countTo(n: int): iterator(): int = 
    return iterator (): int = 
        var i = 0
        while i<=n:
            yield i
            inc i

let countTo20 = countTo(20)
echo countTo20()

var output = ""
#Raw iterator usage
while true:
    # 1. grab the element
    let next = countTo20()
    # 2. Is the element bogus? It's the end of the loop, discard it
    if finished(countTo20):
        break
    # 3. Loop body goes here:
    output.add($next & " ")

    echo output

    output = ""
    let countTo9 = countTo(9)
    for i in countTo9():
        output.add($i)
    echo output