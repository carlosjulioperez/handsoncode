package ec.carper.ingenio.test
 
import org.openxava.tests.*

import ec.carper.ingenio.model.JugoDetalle
import ec.carper.ingenio.util.Util

class JugoDetalleTest extends ModuleTestBase {

    JugoDetalleTest(String testName) {
        super(testName, "Ingenio", "JugoDetalle")
    }
 
    void testGetPorcSacJR(){
        String diaTrabajoId = "ff808081711cd37c01711cd403a70000"
        String hora      = "2019-08-03 06:00:00"
        
        def porcSacJR = new JugoDetalle().getPorcSacJR(diaTrabajoId, Util.instance.toTimestamp(hora))
        println porcSacJR
    }
}
