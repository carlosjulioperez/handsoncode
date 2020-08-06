package ec.carper.ingenio.test
 
import org.openxava.tests.*

class PhTest extends ModuleTestBase {

    PhTest(String testName) {
        super(testName, "Ingenio", "Ph")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new"     , "viewObject=xava_view_section0_detalle")
        assertDialog()
        setValue    ( "horaS"        , "08:00")
        assertValue ( "hora"         , "07/08/2019 08:00")
        setValue    ( "horaSTJClaro" , "09:00")
        assertValue ( "horaTJClaro"  , "07/08/2019 09:00")
        assertValue ( "tJClaro"      , "188.90")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
