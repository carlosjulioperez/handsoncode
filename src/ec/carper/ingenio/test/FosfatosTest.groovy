package ec.carper.ingenio.test
 
import org.openxava.tests.*

class FosfatosTest extends ModuleTestBase {

    FosfatosTest(String testName) {
        super(testName, "Ingenio", "Fosfatos")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        setValue("cteN1" , "0.2183")
        setValue("cteN2" , "0.0118")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 

        execute("Collection.new"      , "viewObject=xava_view_detalle")
        assertDialog()
        setValue    ( "hora"          , "03/08/2019 09:30")

        setValue    ( "jdAbsorbancia" , "2.887")
        setValue    ( "jdMlMuestra"   , "2.00")
        assertValue ( "jdMgP"         , "0.618")
        assertValue ( "jdFosfatos"    , "309.00")
        
        setValue    ( "jcAbsorbancia" , "0.994")
        setValue    ( "jcMlMuestra"   , "2.00")
        assertValue ( "jcMgP"         , "0.205")
        assertValue ( "jcFosfatos"    , "102.50")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)
        
        execute("CRUD.delete")
        assertNoErrors()
    }

}
