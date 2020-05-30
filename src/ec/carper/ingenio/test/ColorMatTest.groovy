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
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle3", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle4", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle5", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle6", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle7", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle8", 0) // La colección esta vacía 

        execute("Collection.new" , "viewObject=xava_view_detalle1")
        assertDialog()
        setValue    ( "bri"           , "0.8")
        setValue    ( "absFiltrada"   , "0.127")
        setValue    ( "absSinFiltrar" , "0.155")
        setValue    ( "celda"         , "1")
        assertValue ( "cedilla"       , "0.008010")
        assertValue ( "rho"           , "1,001.293000")
        assertValue ( "color"         , "15,855.18")
        assertValue ( "turb"          , "3,495.63")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
