package ec.carper.ingenio.test
 
import org.openxava.tests.*

import ec.carper.ingenio.model.JugoDetalle
import ec.carper.ingenio.util.Util

class JugoDetalleTest extends ModuleTestBase {

    JugoDetalleTest(String testName) {
        super(testName, "Ingenio", "JugoDetalle")
    }
 
    void testGetPorcSacJR(){
        String hora      = "2019-08-07 10:00:00"
        
        def porcSacJR = new JugoDetalle().getPorcSacJR(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora))
        println porcSacJR
    }
}
