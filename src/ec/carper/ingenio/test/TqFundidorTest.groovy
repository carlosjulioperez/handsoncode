package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TqFundidorTest extends ModuleTestBase {

    TqFundidorTest(String testName) {
        super(testName, "Ingenio", "TqFundidor")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"    , "03/08/2019 06:30")

        setValue    ( "bri"  , "11.74")
        setValue    ( "pol"  , "45.67")
        assertValue ( "sac"  , "68.22")
        assertValue ( "pur"  , "96.85")
        assertValue ( "bri2" , "70.44")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
