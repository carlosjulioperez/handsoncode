package ec.carper.ingenio.test
 
import java.util.List

import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.model.TrashCanaDetalle2
import ec.carper.ingenio.model.DiaTrabajo

import ec.carper.ingenio.util.Util
import org.apache.commons.logging.*
import org.openxava.tests.*

class TrashCanaDetalle2Test extends ModuleTestBase {

    private static Log log = LogFactory.getLog(TrashCanaDetalle2Test.class)
 
    TrashCanaDetalle2Test(String testName) {
        super(testName, "Ingenio", "TrashCanaDetalle2")
    }
 
    void testGetPorcAzuRed(){
        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")

        DiaTrabajo diaTrabajo = XPersistence.getManager().find( DiaTrabajo.class, "ff808081711cd37c01711cd403a70000" )

        def valor = (String)new TrashCanaDetalle2().getPorcAzuRed( 
            diaTrabajo, Util.instance.toTimestamp("2019-08-03 07:00:00.0")
        )
        log.warn(valor)
        assertEquals(valor, "0.500")
    }

}
