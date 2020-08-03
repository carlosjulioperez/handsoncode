package ec.carper.ingenio.test

import ec.carper.ingenio.util.Util

import groovy.time.TimeCategory
import java.time.LocalDate
import java.time.format.*

class CodigoTest extends GroovyTestCase {

    // https://groovy-lang.org/testing.html
    /*
    void testAssertions() {
        assertTrue(1 == 1)
        assertEquals("test", "test")

        def x = "42"
        assertNotNull "x must not be null", x
        assertNull null

        assertSame x, x
    }
    */

    void test(){
        getDiferenciaHoras()
    }

    // https://stackoverflow.com/questions/3909855/groovy-time-durations
    //
    // http://www.javamultiplex.com/2017/01/java-program-how-to-subtract-two-given-times.html
    // https://www.leveluplunch.com/java/examples/subtract-hours-from-date/
    void getDiferenciaHoras(){
        //println Util.instance.getDiferenciaHoras("02:15:55", "24:00:00")
        println Util.instance.getFraccionTiempo("20:17:00")
        println Util.instance.getFraccionTiempo("00:00:00")
    }

    void _testTimestamp() {
        def hora = "23:20"
        def fecha = LocalDate.of(2019, 8, 7)
        println Util.instance.toTimestamp(hora, fecha)
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
