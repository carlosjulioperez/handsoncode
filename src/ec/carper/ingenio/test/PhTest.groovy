package ec.carper.ingenio.test
 
import org.openxava.tests.*

class PhTest extends ModuleTestBase {

    PhTest(String testName) {
        super(testName, "Ingenio", "Ph")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        setValue("hora" , "03/08/2019 18:00")
        setValue("horaTJClaro" , "03/08/2019 19:00")
        assertValue("tJClaro"  , "18.60")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
