package ec.carper.ingenio.test
 
import java.util.List
import java.time.LocalDate

import org.apache.commons.logging.*
import ec.carper.ingenio.model.Jugo
import org.openxava.tests.*

class JugoTest extends ModuleTestBase {

    private static Log log = LogFactory.getLog(JugoTest.class)
 
    JugoTest(String testName) {
        super(testName, "Ingenio", "Jugo")
    }
 
    void testGetAvgField(){
        LocalDate date = LocalDate.of(2019, 8, 2);
        log.warn ("============================================================")
        def valor = (String)new Jugo().getAvgField(date, "avgJdBri")
        log.warn (valor)

        // def valor = (String)new BrixDensidadWp().getP(100)
        // assertEquals(valor, "1515.88")
    }

}
