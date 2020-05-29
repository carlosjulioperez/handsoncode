package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MagmasTest extends ModuleTestBase {

    MagmasTest(String testName) {
        super(testName, "Ingenio", "Magmas")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"    , "03/08/2019 06:30")

        setValue    ( "mbBri"  , "15.16")
        setValue    ( "mbPol"  , "59.74")
        assertValue ( "mbSac"  , "88.02")
        assertValue ( "mbPur"  , "96.77")
        assertValue ( "mbBri2" , "90.96")

        setValue    ( "mcBri"  , "14.47")
        setValue    ( "mcPol"  , "56.69")
        assertValue ( "mcSac"  , "83.76")
        assertValue ( "mcPur"  , "96.48")
        assertValue ( "mcBri2" , "86.82")

        setValue    ( "mrBri"  , "15.17")
        setValue    ( "mrPol"  , "60.49")
        assertValue ( "mrSac"  , "89.12")
        assertValue ( "mrPur"  , "97.91")
        assertValue ( "mrBri2" , "91.02")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
