package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MielesTest extends ModuleTestBase {

    MielesTest(String testName) {
        super(testName, "Ingenio", "Mieles")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"    , "03/08/2019 06:30")

        setValue    ( "maBri"  , "12.89")
        setValue    ( "maPol"  , "40.95")
        assertValue ( "maSac"  , "60.89")
        assertValue ( "maPur"  , "78.73")
        assertValue ( "maBri2" , "77.34")

        setValue    ( "mbBri"  , "14.28")
        setValue    ( "mbPol"  , "30.3")
        assertValue ( "mbSac"  , "44.80")
        assertValue ( "mbPur"  , "52.29")
        assertValue ( "mbBri2" , "85.68")

        setValue    ( "mfBri"  , "13.08")
        setValue    ( "mfPol"  , "42.18")
        assertValue ( "mfSac"  , "62.67")
        assertValue ( "mfPur"  , "79.85")
        assertValue ( "mfBri2" , "78.48")

        setValue    ( "mrBri"  , "13.05")
        setValue    ( "mrPol"  , "42.15")
        assertValue ( "mrSac"  , "62.63")
        assertValue ( "mrPur"  , "79.99")
        assertValue ( "mrBri2" , "78.30")

        setValue    ( "mpBri"  , "14.05")
        setValue    ( "mpPol"  , "29.77")
        assertValue ( "mpSac"  , "44.06")
        assertValue ( "mpPur"  , "52.27")
        assertValue ( "mpBri2" , "84.30")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
