package ec.carper.ingenio.test

import org.openxava.tests.*

class AreaTest extends ModuleTestBase {
    AreaTest (String testName) {
        super(testName, "Ingenio", "Area")
    }
 
    public void testAreasEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "CAMPO"); // Row 0 column 0
        assertValueInList(1, 0, "MOLINO"); // Row 1 column 0
        assertValueInList(2, 0, "INSTRUMENTACION"); // Row 2 column 0
    }
 
}

