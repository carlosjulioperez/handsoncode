package ec.carper.ingenio.test

import org.openxava.tests.*

class UnidadTest extends ModuleTestBase {
    UnidadTest (String testName) {
        super(testName, "Ingenio", "Unidad")
    }
 
    public void testUnidadsEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "TON"); // Row 0 column 0
        assertValueInList(1, 0, "M³"); // Row 1 column 0
        assertValueInList(2, 0, "QQ"); // Row 2 column 0
    }
 
}

