package ec.carper.ingenio.test
 
import org.openxava.tests.*

class AzucarCrudoTest extends ModuleTestBase {

    AzucarCrudoTest(String testName) {
        super(testName, "Ingenio", "AzucarCrudo")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"          , "03/08/2019 14:30")

        setValue    ( "bri"           , "48.58")
        setValue    ( "absFiltrada"   , "0.193")
        setValue    ( "absSinFiltrar" , "0.333")
        setValue    ( "celda"         , "1.00")

        assertValue ( "briCorr"       , "48.44")
        assertValue ( "rho"           , "1,221.445")
        assertValue ( "cedilla"       , "0.591668")
        assertValue ( "briEle"        , "97.16")
        assertValue ( "color"         , "326.20")
        assertValue ( "turb"          , "236.62")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}