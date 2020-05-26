package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.BrixDensidadWp
import org.openxava.tests.*

class BrixDensidadWpTest extends ModuleTestBase {

    BrixDensidadWpTest(String testName) {
        super(testName, "Ingenio", "BrixDensidadWp")
    }
 
    /*
    protected void setUp() throws Exception { // setUp() is always executed before each test
        super.setUp() // It's needed because ModuleTestBase uses it for initializing, even JPA is initialized here.
    }
    protected void tearDown() throws Exception { // tearDown() is always executed after each test
        super.tearDown() // It's needed, ModuleTestBase closes resources here
    }
    */

    void testGetP(){
        // log.warn (getP(14.93))
        // def valor = (String)new BrixDensidadWp().getP(14.93)
        // //assertEquals("Verificando:", valor, "1058.733")
        // assertEquals(valor, "1058.73")

        def valor = (String)new BrixDensidadWp().getP(14.23)
        assertEquals(valor, "1055.743")
    }

}
