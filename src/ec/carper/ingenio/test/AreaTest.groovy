package ec.carper.ingenio.test

import org.openxava.tests.*

class AreaTest extends ModuleTestBase {
    AreaTest (String testName) {
        super(testName, "Ingenio", "Area")
    }
 
    void testCreateReadUpdateDelete() throws Exception {
        login("admin", "admin")
 
        String descripcion = "JUNIT"
        String descripcion2 = "JUNI2"
        
        // Create
        execute("CRUD.new")
        setValue("descripcion", descripcion)
        execute("CRUD.save")
        assertNoErrors()
        assertValue("descripcion", "")
 
        // Read
        setValue("descripcion", descripcion)
        execute("CRUD.refresh")
        assertValue("descripcion", descripcion)
 
        // Update
        setValue("descripcion", descripcion2)
        execute("CRUD.save")
        assertNoErrors()
        assertValue("descripcion", "")
 
        // Verify if modified
        setValue("descripcion", descripcion2)
        execute("CRUD.refresh")
        assertValue("descripcion", descripcion2)
 
        // Delete
        execute("CRUD.delete")
        assertMessage("Area deleted successfully")
    }
 
}

