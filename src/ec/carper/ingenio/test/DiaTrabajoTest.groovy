package ec.carper.ingenio.test

import java.time.LocalDate
import java.time.format.*
import org.openxava.tests.*

class DiaTrabajoTest extends ModuleTestBase {
    DiaTrabajoTest (String testName) {
        super(testName, "Ingenio", "DiaTrabajo")
    }
 
    // 07/08/2019
    // https://www.javatpoint.com/java-localdate
    // https://mkyong.com/java8/java-8-convert-localdatetime-to-timestamp/
    public void test(){
        def fecha = LocalDate.of(2019, 8, 7)
    }

    public void testDiaTrabajoEnLista() throws Exception {
        login("admin", "admin");
        assertValueInList(0, 0, "8/7/19");  // Row 0 column 0
        assertValueInList(0, 1, "3"); // Row 0 column 1
    }
 	
}

