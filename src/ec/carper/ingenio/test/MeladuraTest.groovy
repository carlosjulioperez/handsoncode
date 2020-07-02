package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MeladuraTest extends ModuleTestBase {

    MeladuraTest(String testName) {
        super(testName, "Ingenio", "Meladura")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue("horaS"          , "07:00")
        assertValue("hora"        , "07/08/2019 07:00")

        setValue    ( "mcrBri"  , "15.31")
        setValue    ( "mcrPol"  , "53.36")
        assertValue ( "mcrSac"  , "52.38")
        assertValue ( "mcrPur"  , "85.53")
        assertValue ( "mcrBri2" , "61.24")

        setValue    ( "mclBri"  , "15.21")
        setValue    ( "mclPol"  , "52.85")
        assertValue ( "mclSac"  , "51.90")
        assertValue ( "mclPur"  , "85.31")
        assertValue ( "mclBri2" , "60.84")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
