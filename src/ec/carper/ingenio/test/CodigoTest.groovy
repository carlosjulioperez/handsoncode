package ec.carper.ingenio.test

import ec.carper.ingenio.util.Util

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

    void testTimestamp() {
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
