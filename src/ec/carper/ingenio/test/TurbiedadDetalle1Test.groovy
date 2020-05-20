package ec.carper.ingenio.test
 
import java.sql.Timestamp

import org.apache.commons.logging.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*
import org.openxava.tests.*

import ec.carper.ingenio.model.TurbiedadDetalle1
import ec.carper.ingenio.util.Util

class TurbiedadDetalle1Test extends ModuleTestBase {

    private static Log log = LogFactory.getLog(TurbiedadDetalle1Test.class)
 
    TurbiedadDetalle1Test(String testName) {
        super(testName, "Ingenio", "TurbiedadDetalle1")
    }
 
    void testGetTurJClaro(){
        String diaTrabajoId = "ff808081711cd37c01711cd403a70000"
        String hora      = "2019-08-03 19:00:00"
        
        def turJClaro = new TurbiedadDetalle1().getValorTurJClaro(diaTrabajoId, Util.instance.toTimestamp(hora))
        println turJClaro 
    }
}
