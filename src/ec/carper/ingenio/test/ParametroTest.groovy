package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.Parametro
import org.openxava.tests.*

class ParametroTest extends ModuleTestBase {

    ParametroTest(String testName) {
        super(testName, "Ingenio", "Parametro")
    }
 
    void testGetSusRed(){
        def valor = (String)new Parametro().obtenerValor("TAMIZ1_NUMERO")
        assertEquals(valor, "18")
    }

}
