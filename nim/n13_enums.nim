#[
Enums 
Enums in Nim are like enums in C, but are type-checked. There are no
anonymous enums in Nim.

The {.pure.} pragma that Colors and OtherColors have requires that all ambigious
references to the enums’ values be qualified. This mean that Orange can be
referred to without OtherColors.Orange (although OtherColors.Orange is still
allowed).

Enums can be given custom values and stringify values, as shown by Colors and Signals.
]#
type
    CompassDirections = enum
        cdNort, cdEast, cdSouth, cdWest
    
    Colors {.pure.} = enum
        Red = "FF0000", Green = (1, "00FF00"), Blue = "0000FF"

    OtherColors {.pure.} = enum
        Red = 0xFF0000, Orange = 0xFFA500, Yellow = 0xFFFF00

    Signals = enum
        sigQuit = 3, sigAbort = 6, sigKill = 9
    
echo repr CompassDirections
echo CompassDirections.cdEast

# echo Red  # Error: ambiguous identifier: 'Red'
echo Colors.Red
echo OtherColors.Red
echo OtherColors.Orange, " ", Orange

#[
Ordinals
While enums can also have disjoint values, it should not be used for any other
reason than compatibility with C because it breaks the idea that enums are
ordinal.
]#
for direction in ord(low(CompassDirections))..ord(high(CompassDirections)):
    echo CompassDirections(direction), " ord: ", direction

var ordinal = low(int)
inc ordinal
dec ordinal
echo high(char)

#[
Because enums are ordinals, they have the low, high, inc, dec, and ord methods
defined, where

* low gives the lowest possible value 
* high give the highest possible value 
* inc increments 
* dec decrements 
* ord gives the integer value of the enum
* CompassDirections is a cast that gives an enum from an integer

It is also possible to iterate through all possible values of ordinal enums,
either as shown above, or cdNorth..cdWest, which is equivalent.

Signals is not an ordinal type, and so doesn’t have the inc and dec procedures.
when false:
    var nonOrdinal = sigQuit
    inc nonOrdinal
    dec nonOrdinal
]#