#[
Procs

Procedures in Nim are declared using proc and require that their parameter and
return types be annotated. After the types and parameters, an = is used to
denote the start of the function body. Another thing to note is that procedures
have uniform function call syntax, which means that they can called as both
foo(a, b) or a.foo(b).
]#
proc fibonacci(n: int): int =
    if n < 2:
        result = n
    else:
        result = fibonacci(n-1) + (n-2).fibonacci

echo fibonacci(20)

#[
Side effect analyses

Nim provides support for functional programming and so includes the
{.noSideEffect.} pragma, which statically ensures there are no side effects.
]#
proc sum(x, y: int): int {. noSideEffect .} = 
    x + y

proc minus(x, y: int): int {. noSideEffect .} = 
    x - y # error: 'minus' can have side effects

echo sum(23,20)
echo minus(23, 20)

#[
Operators 

To create an operator, the symbols that are to be used must be encased inside `s
to signify they are operators.
]#
proc `$`(a: array[2, array[2, int]]): string =
    result = ""
    for v in a:
        for vx in v:
            result.add($vx & ", ")
        result.add("\n")

echo ([[1,2], [3,4]])

proc `^&*^@%`(a, b: string): string =
  ## A confusingly named useless operator
  result = a[0] & b[high(b)]

assert("foo" ^&*^@% "bar" == "fr")
echo("foo" ^&*^@% "bar" == "fr")

#[
Generic Functions 

Generic functions are like C++’s templates and allow for the
same statically checked duck-typing semantics as templates.
]#
proc `+`(a,b: string): string =
    a & b
proc `*`[T](a: T, b: int): T =
    result = default(T)
    for i in 0..b-1:
        result = result + a  # calls `+` from line 2

assert("a" * 10 == "aaaaaaaaaa")
echo("a" * 10 == "aaaaaaaaaa")