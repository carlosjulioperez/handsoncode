package ec.carper.ingenio.test
 
import org.openxava.tests.*

class GrasshoperTest extends ModuleTestBase {

    GrasshoperTest(String testName) {
        super(testName, "Ingenio", "Grasshoper")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "hora"          , "03/08/2019 06:30")

        setValue    ( "bri"           , "48.95")
        setValue    ( "absFiltrada"   , "0.298")
        setValue    ( "absSinFiltrar" , "0.504")
        setValue    ( "celda"         , "1.00")

        assertValue ( "briCorr"       , "48.41")
        assertValue ( "rho"           , "1,223.626")
        assertValue ( "cedilla"       , "0.592357")
        assertValue ( "briEle"        , "97.90")
        assertValue ( "color"         , "503.08")
        assertValue ( "turb"          , "347.76")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
