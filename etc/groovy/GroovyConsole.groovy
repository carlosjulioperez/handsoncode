def timeList = []
timeList << "A"
timeList << "B"
timeList << "C"

timeList.each {println it}


// https://stackoverflow.com/questions/4560546/how-do-i-round-a-number-in-groovy    
def valor = 1.255 / 0.12
println valor
println Math.round(valor * 100) / 100

println ( new BigDecimal(1.2686).setScale(2, BigDecimal.ROUND_HALF_UP))

// Ejemplo de closure e iteradores.
//int i = 1
//String texto = "campo${i}"
//println texto

def closure = {
    println "campo${it}"
}

//closure.call(1)  devuelte: campo1

(1..8).each{
    closure.call(it)
}
