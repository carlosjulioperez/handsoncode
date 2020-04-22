package ec.carper.ingenio.test

import org.openxava.tests.*

class UnidadTest extends ModuleTestBase { // Ha de extender de ModuleTestBase
    UnidadTest (String testName) {
        // We indicate the application descripcion (Ingenio) and the module descripcion (Unidad)
        super(testName, "Ingenio", "Unidad") // 
    }
 
    // The test methods must start with 'test'
    void testCreateReadUpdateDelete() throws Exception {
        login("admin", "admin") // The user sign in to access the module
 
        String id = "99"
        String descripcion= "JUNIT"
        String descripcion2 = "JUNI2"
        
        // Create
        execute("CRUD.new")
        setValue("id", id)
        setValue("descripcion", descripcion)
        execute("CRUD.save")
        assertNoErrors()
        println "****************************************************************************************************"
        println getValue(descripcion)
        assertValue("id", "")
        assertValue("descripcion", "")
 
        // Read
        setValue("id", id)
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
        assertMessage("Unidad deleted successfully") // Verifies that the message 'Unidad deleted successfully' is shown to the user
    }
 
}

