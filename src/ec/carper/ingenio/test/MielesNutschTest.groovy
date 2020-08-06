package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MielesNutschTest extends ModuleTestBase {

    MielesNutschTest(String testName) {
        super(testName, "Ingenio", "MielesNutsch")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new", "viewObject=xava_view_section0_detalle")
        assertDialog()
        
        setValue    ( "horaS" , "08:00")
        assertValue ( "hora"  , "07/08/2019 08:00")

        setValue    ( "maBri" , "12.89")
        setValue    ( "maPol" , "40.95")
        assertValue ( "maSac"  , "60.89")
        assertValue ( "maPur"  , "78.73")
        assertValue ( "maBri2" , "77.34")

        setValue    ( "mbBri"  , "14.28")
        setValue    ( "mbPol"  , "30.3")
        assertValue ( "mbSac"  , "44.80")
        assertValue ( "mbPur"  , "52.29")
        assertValue ( "mbBri2" , "85.68")

        setValue    ( "mcBri"  , "13.08")
        setValue    ( "mcPol"  , "42.18")
        assertValue ( "mcSac"  , "62.67")
        assertValue ( "mcPur"  , "79.85")
        assertValue ( "mcBri2" , "78.48")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
