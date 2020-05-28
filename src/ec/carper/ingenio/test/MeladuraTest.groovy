package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MeladuraTest extends ModuleTestBase {

    MeladuraTest(String testName) {
        super(testName, "Ingenio", "Meladura")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"    , "03/08/2019 07:00")

        setValue    ( "mcrBri"  , "17.4")
        setValue    ( "mcrPol"  , "61.8")
        assertValue ( "mcrSac"  , "60.15")
        assertValue ( "mcrPur"  , "86.42")
        assertValue ( "mcrBri2" , "69.60")

        setValue    ( "mclBri"  , "13.1")
        setValue    ( "mclPol"  , "44.9")
        assertValue ( "mclSac"  , "44.47")
        assertValue ( "mclPur"  , "84.87")
        assertValue ( "mclBri2" , "52.40")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
