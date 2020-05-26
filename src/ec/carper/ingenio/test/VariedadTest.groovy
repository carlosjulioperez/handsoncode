package ec.carper.ingenio.test

import org.openxava.tests.*

class VariedadTest extends ModuleTestBase {
    VariedadTest (String testName) {
        super(testName, "Ingenio", "Variedad")
    }
 
    public void testVariedadsEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "V71-51");  // Row 0 column 0
        assertValueInList(1, 0, "CC85-68"); // Row 1 column 0
    }
 
}

