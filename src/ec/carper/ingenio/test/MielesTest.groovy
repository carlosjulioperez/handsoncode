package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MielesTest extends ModuleTestBase {

    MielesTest(String testName) {
        super(testName, "Ingenio", "Mieles")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new"   , "viewObject=xava_view_section0_detalle")
        assertDialog()
        
        setValue    ( "horaS" , "08:00")
        assertValue ( "hora"  , "07/08/2019 08:00")

        setValue    ( "maBri" , "12.89")
        setValue    ( "maPol" , "40.95")
        assertValue ( "maSac" , "60.89")
        assertValue ( "maPur"  , "78.73")
        assertValue ( "maBri2" , "77.34")

        setValue    ( "mbBri"  , "14.28")
        setValue    ( "mbPol"  , "30.3")
        assertValue ( "mbSac"  , "44.80")
        assertValue ( "mbPur"  , "52.29")
        assertValue ( "mbBri2" , "85.68")

        setValue    ( "mfBri"  , "14.20")
        setValue    ( "mfPol"  , "4.70")
        assertValue ( "mfSac"  , "28.20")
        assertValue ( "mfPur"  , "33.10")
        assertValue ( "mfBri2" , "85.20")

        setValue    ( "mrBri"  , "14.10")
        setValue    ( "mrPol"  , "4.10")
        assertValue ( "mrSac"  , "24.60")
        assertValue ( "mrPur"  , "29.08")
        assertValue ( "mrBri2" , "84.60")

        setValue    ( "mpBri"  , "16.10")
        setValue    ( "mpPol"  , "6.10")
        assertValue ( "mpSac"  , "8.95")
        assertValue ( "mpPur"  , "9.27")
        assertValue ( "mpBri2" , "96.60")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
