package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.BrixDensidadTitSus
import org.openxava.tests.*

class BrixDensidadTitSusTest extends ModuleTestBase {

    BrixDensidadTitSusTest(String testName) {
        super(testName, "Ingenio", "BrixDensidadTitSus")
    }
 
    void testGetSusRed(){
        def valor = (String)new BrixDensidadTitSus().getSusRed(9.4)
        assertEquals(valor, "0.50")
    }

    void _testGetSqlToList(){
        def lista = new BrixDensidadTitSus().getSqlToList() 

        // Documentación
        // println new BrixDensidadTitSus().getSusRed(lista, 5.09)

        // http://grails.asia/groovy-find
        def tupla = lista.find { it[0] <= 5.10 }
        BigDecimal susRed = tupla[1] 
        log.warn (susRed)
        assertEquals(susRed, 0.91)
    }

}
