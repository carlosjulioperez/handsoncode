package ec.carper.ingenio.test

import org.openxava.tests.*

class CrearEstructuraDBTest extends ModuleTestBase {
    CrearEstructuraDBTest (String testName) {
        // Se usa el módulo Area para que Hibernate cree la estructura
        super(testName, "Ingenio", "Area")
    }
 
    public void test() throws Exception {
        login("admin", "admin");
    }
 
}

