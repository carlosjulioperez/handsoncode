package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.Jugo
import org.openxava.tests.*

class JugoTest extends ModuleTestBase {

    JugoTest(String testName) {
        super(testName, "Ingenio", "Jugo")
    }
 
    void testGetAvgField(){
        String diaTrabajoId = "ff808081711cd37c01711cd403a70000"
        def valor = (String)new Jugo().getAvgField(diaTrabajoId, "jdBri")
        assertEquals(valor, "14.23")
    }
    
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora" , "04/08/2019 02:00")
        
        setValue    ( "jeBri" , "19.99")
        setValue    ( "jePol" , "71.61")
        assertValue ( "jeSac" , "17.24")
        assertValue ( "jePur" , "86.24")
        setValue    ( "jdBri" , "16.73")
        setValue    ( "jdPol" , "58.88")
        assertValue ( "jdSac" , "14.37")
        assertValue ( "jdPur" , "85.89")
        setValue    ( "jcBri" , "17.04")
        setValue    ( "jcPol" , "60.37")
        assertValue ( "jcSac" , "14.71")
        assertValue ( "jcPur" , "86.33")
        setValue    ( "jnBri" , "16.65")
        setValue    ( "jnPol" , "58.72")
        assertValue ( "jnSac" , "14.33")
        assertValue ( "jnPur" , "86.07")
        setValue    ( "jrBri" , "9.22")
        setValue    ( "jrPol" , "30.23")
        assertValue ( "jrSac" , "7.60")
        assertValue ( "jrPur" , "82.43")
        setValue    ( "jfBri" , "13.57")
        setValue    ( "jfPol" , "40.91")
        assertValue ( "jfSac" , "10.11")
        assertValue ( "jfPur" , "74.50")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
