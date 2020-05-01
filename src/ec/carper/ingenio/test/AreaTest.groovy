package ec.carper.ingenio.test

import org.openxava.tests.*

class AreaTest extends ModuleTestBase {
    AreaTest (String testName) {
        super(testName, "Ingenio", "Area")
    }
 
    void testCreateReadUpdateDelete() throws Exception {
        login("admin", "admin")
 
        String descripcion = "Cam"
        String descripcion2 = "Campo"
        
        // Read
        execute("CRUD.new")
        setValue("descripcion", descripcion)
        execute("CRUD.refresh")
        assertValue("descripcion", descripcion2)
 
    }
 
}

