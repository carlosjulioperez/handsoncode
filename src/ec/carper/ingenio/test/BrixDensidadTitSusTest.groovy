package ec.carper.ingenio.test
 
import java.util.List

import ec.carper.ingenio.model.BrixDensidadTitSus
import org.apache.commons.logging.*
import org.openxava.tests.*

class BrixDensidadTitSusTest extends ModuleTestBase {

    private static Log log = LogFactory.getLog(BrixDensidadTitSusTest.class)
 
    BrixDensidadTitSusTest(String testName) {
        super(testName, "Ingenio", "BrixDensidadTitSus")
    }
 
    // void testGetSusRed(){
    //     log.warn ("============================================================")
    //
    //     def valor = (String)new BrixDensidadTitSus().getSusRed(9.4)
    //     log.warn(valor)
    //     assertEquals(valor, "0.50")
    // }

    void testGetSqlToList(){
        log.warn (">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
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
