package ec.carper.ingenio.test

import org.openxava.tests.*

class MaterialTest extends ModuleTestBase {
    MaterialTest (String testName) {
        super(testName, "Ingenio", "Material")
    }
 
    public void testMaterialsEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "CAÑA/DIA"); // Row 0 column 0
        assertValueInList(1, 0, "AGUA MACERACION"); // Row 1 column 0
        assertValueInList(2, 0, "JUGO DILUIDO (BR)"); // Row 2 column 0
    }
 
}

