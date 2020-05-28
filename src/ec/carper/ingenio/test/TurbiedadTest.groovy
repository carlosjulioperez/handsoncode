package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TurbiedadTest extends ModuleTestBase {

    TurbiedadTest(String testName) {
        super(testName, "Ingenio", "Turbiedad")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 

        execute("Collection.new"  , "viewObject=xava_view_detalle1")
        assertDialog()
        setValue    ( "hora"      , "03/08/2019 19:00")
        setValue    ( "abs900Nm"  , "0.186")
        assertValue ( "turJClaro" , "18.60")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)
        
        
        execute("Collection.new"   , "viewObject=xava_view_detalle2")
        assertDialog()
        setValue    ( "hora"       , "03/08/2019 07:00")
        setValue    ( "polCachaza" , "2.11")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
