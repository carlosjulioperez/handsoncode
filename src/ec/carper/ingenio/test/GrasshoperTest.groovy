package ec.carper.ingenio.test
 
import org.openxava.tests.*

class GrasshoperTest extends ModuleTestBase {

    GrasshoperTest(String testName) {
        super(testName, "Ingenio", "Grasshoper")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new"   , "viewObject=xava_view_section0_detalle")
        assertDialog()
        
        setValue    ( "horaS"         , "08:00")
        assertValue ( "hora"          , "07/08/2019 08:00")
        setValue    ( "bri"           , "50.98")
        setValue    ( "absFiltrada"   , "0.149")
        setValue    ( "absSinFiltrar" , "0.221")
        setValue    ( "celda"         , "1.00")

        assertValue ( "briCorr"       , "50.42")
        assertValue ( "rho"           , "1,234.623")
        assertValue ( "cedilla"       , "0.622497")
        assertValue ( "briEle"        , "101.96")
        assertValue ( "color"         , "239.36")
        assertValue ( "turb"          , "115.66")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
