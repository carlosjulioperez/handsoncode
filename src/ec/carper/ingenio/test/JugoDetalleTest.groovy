package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.JugoDetalle
import ec.carper.ingenio.util.*

import org.openxava.tests.*

class JugoDetalleTest extends ModuleTestBase {

    JugoDetalleTest(String testName) {
        super(testName, "Ingenio", "JugoDetalle")
    }
 
    void testGetPorcSacJR(){
        String hora      = "2019-08-07 08:00:00"
        //def porcSacJR = new JugoDetalle().getPorcSacJR(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora))
        def jrSac = SqlUtil.instance.getValorDetalleCampoXHora(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora), "jugo", "JugoDetalle", "jrSac")
        println jrSac 
    }
}
