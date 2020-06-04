package ec.carper.ingenio.test
 
import org.openxava.tests.*

class AzucarGranelTest extends ModuleTestBase {

    AzucarGranelTest(String testName) {
        super(testName, "Ingenio", "AzucarGranel")
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

        assertValue ( "briCorr"       , "48.05")
        assertValue ( "rho"           , "1,221.445")
        assertValue ( "cedilla"       , "0.586904")
        assertValue ( "briEle"        , "96.10")
        assertValue ( "color"         , "328.84")
        assertValue ( "turb"          , "238.54")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
