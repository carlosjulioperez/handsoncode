package ec.carper.ingenio.test

import org.openxava.tests.*

class ModuloTest extends ModuleTestBase {
    ModuloTest (String testName) {
        super(testName, "Ingenio", "Modulo")
    }
 
    public void testModulosEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "MODULO 1"); // Row 0 column 0
        assertValueInList(1, 0, "MODULO 2"); // Row 1 column 0
        assertValueInList(2, 0, "MODULO 3"); // Row 2 column 0
    }
 
}

