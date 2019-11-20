package ec.carper.ingenio.test

import java.time.LocalDate
import org.openxava.tests.*

class InicioZafraTest extends ModuleTestBase {
    InicioZafraTest (String testName) {
        super(testName, "Ingenio", "InicioZafra")
    }

    // https://www.mkyong.com/java8/java-8-how-to-convert-string-to-localdate/

    String fechaInicio  = "1979-03-01"
    String fechaInicio2 = "1979-03-02"
    String fechaFin     = "1979-03-03"
    String numeroZafra  = "99"
        
    void testCreateReadUpdateDelete() throws Exception {
        login("admin", "admin")
        
        /*
        // Create
        execute("CRUD.new")
        setValue("fechaInicio" , LocalDate.parse(fechaInicio) )
        setValue("fechaFin"    , LocalDate.parse(fechaFin) )
        setValue("numeroZafra" , numeroZafra)
        execute("CRUD.save")
        assertNoErrors()
        verificarBlancos()

        // Read
        setValue("fechaInicio", fechaInicio)
        execute("CRUD.refresh")
        assertValue("fechaInicio", fechaInicio)
        verificarValores()
 
        // Update
        setValue("fechaInicio", fechaInicio2)
        execute("CRUD.save")
        assertNoErrors()
        verificarBlancos()
 
        // Verify if modified
        setValue("fechaInicio", fechaInicio2)
        execute("CRUD.refresh")
        assertValue("fechaInicio", fechaInicio2)
        verificarValores()
 
        // Delete
        execute("CRUD.delete")
        assertMessage("InicioZafra deleted successfully")
        */
    }

    private void verificarBlancos(){
        assertValue("fechaInicio" , "")
        assertValue("fechaFin"    , "")
        assertValue("numeroZafra" , "")
    }
 
    private void verificarValores(){
        assertValue("fechaFin"    , fechaFin)
        assertValue("numeroZafra" , numeroZafra)
    }
}

