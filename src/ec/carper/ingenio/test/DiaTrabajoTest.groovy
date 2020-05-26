package ec.carper.ingenio.test

import org.openxava.tests.*

class DiaTrabajoTest extends ModuleTestBase {
    DiaTrabajoTest (String testName) {
        super(testName, "Ingenio", "DiaTrabajo")
    }
 
    public void testDiaTrabajoEnLista() throws Exception {
        login("admin", "admin");
        //assertValueInList(0, 0, "03/08/2019");  // Row 0 column 0
        assertValueInList(0, 1, "DIA TRABAJO 03/08/2019"); // Row 0 column 1
    }
 	
}

