#[
Blocks

Blocks can be introduced in two different ways: by indenting statements or with
()s.
The first way is to use indenting, e.g. using if-elif-else, while, for
statements, or the block statement.
]#
if true:
    echo "Nim is great!"
while false:
    echo "This line is never output!"
block:
    echo "This line, on the other hand, is always output"

#[
The block statement can also be labeled, making it useful for breaking out of
loops and is useful for general scoping as well.
]#
block outer:
    for i in 0..2000:
        for j in 0..2000:
            if i+j == 3145:
                echo i, ", ", j
                break outer

let b = 3
block:
    let b = "3" # shadowing is probably a dumb idea