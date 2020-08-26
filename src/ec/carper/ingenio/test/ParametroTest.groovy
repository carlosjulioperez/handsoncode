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
        
        assertEquals((String)new Parametro().obtenerValor("CTO24H_FACTOR_FELINING"), "0.997")
        assertEquals((String)new Parametro().obtenerValor("CTO24H_FR"), "0.641")
        assertEquals((String)new Parametro().obtenerValor("FT3"), "0.02831685")

        //assertEquals((String)new Parametro().obtenerValor("DIA_TRABAJO_UN_REGISTRO"), "S")
    }

}
