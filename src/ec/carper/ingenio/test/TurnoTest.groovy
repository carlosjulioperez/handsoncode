package ec.carper.ingenio.test

import org.openxava.tests.*

class TurnoTest extends ModuleTestBase {
    TurnoTest (String testName) {
        super(testName, "Ingenio", "Turno")
    }
 
    public void testTurnosEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "TURNO 1"); // Row 0 column 0
        assertValueInList(1, 0, "TURNO 2"); // Row 1 column 0
        assertValueInList(2, 0, "TURNO 3"); // Row 2 column 0
    }
 
}

