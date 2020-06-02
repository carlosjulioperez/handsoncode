package ec.carper.ingenio.test
 
import org.openxava.tests.*

class ColorMatTest extends ModuleTestBase {

    ColorMatTest(String testName) {
        super(testName, "Ingenio", "ColorMat")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 

        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        setValue    ( "bri1"           , "0.8")
        setValue    ( "absFiltrada1"   , "0.127")
        setValue    ( "absSinFiltrar1" , "0.155")
        setValue    ( "celda1"         , "1")
        assertValue ( "cedilla1"       , "0.008010")
        assertValue ( "rho1"           , "1,001.293000")
        assertValue ( "color1"         , "15,855.18")
        assertValue ( "turb1"          , "3,495.63")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        // assertValue("color1", "15,855.18")
        // assertValue("turb1" , "3,495.63")

        execute("CRUD.delete")
        assertNoErrors()
    }

}
