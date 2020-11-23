package ec.carper.ingenio.test

import ec.carper.ingenio.util.*

import java.time.LocalDate
import java.time.format.*

class CodigoTest extends GroovyTestCase {

    // https://groovy-lang.org/testing.html
    /*
    Hvoid testAssertions() {
        assertTrue(1 == 1)
        assertEquals("test", "test")

        def x = "42"
        assertNotNull "x must not be null", x
        assertNull null

        assertSame x, x
    }
    */

    void test(){
        nulos()
        //println Util.instance.getDiferenciaHoras("13:40:00", "24:00:00")
        // getDiferenciaHoras()
    }

    void nulos(){
        def v1 = null
        def v2 = null
        println ( (v1 && v2) ? v1+v2 : 0 )
    }

    void numerosDeString(){
        def s = "StockFabricaDetalle20"
        int numero = s.findAll(/\d+/)*.toInteger()[0]?:0
        println numero

        // Buscar un entero en una lista. Devuelve el número buscado caso contrario 0 (de null)
        def l = [1, 6, 7, 16, 17, 58, 62]
        println l.find {it==6} ?: 0

        // Suma usando closures:
        def a = { desde, hasta ->
            def suma = 0
            (desde..hasta).each{
                suma += 10
            }
            suma
        }

        println a(1,2) // 20
    }

    // https://stackoverflow.com/questions/3909855/groovy-time-durations
    //
    // http://www.javamultiplex.com/2017/01/java-program-how-to-subtract-two-given-times.html
    // https://www.leveluplunch.com/java/examples/subtract-hours-from-date/
    void getDiferenciaHoras(){
        //println Util.instance.getDiferenciaHoras("02:15:55", "24:00:00")
        println Calculo.instance.getFraccionTiempo("20:17:00")
        println Calculo.instance.getFraccionTiempo("00:00:00")
        
        // Restar un minuto a un hora
        use (groovy.time.TimeCategory) {
            def horaInicio = Date.parse("HH:mm", "07:00")
            def horaFin = horaInicio-1.minutes
            println horaFin.format("HH:mm")
        }
    }

    void _testTimestampPlantilla() {
        def fecha = LocalDate.of(2019, 8, 7)
        def hora = "02:20"
        def digitos = hora.split(":")
        digitos.each{
            println it
        }
        def strFecha = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
        //println (strFecha)
        def strHora = Util.instance.toTimestamp(strFecha+" "+hora+":00")
        println (strHora)
    }
}
