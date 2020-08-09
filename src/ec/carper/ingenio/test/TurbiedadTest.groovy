package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TurbiedadTest extends ModuleTestBase {

    TurbiedadTest(String testName) {
        super(testName, "Ingenio", "Turbiedad")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        execute("Collection.new"   , "viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue    ("horaS"      , "09:00")
        assertValue ("hora"       , "07/08/2019 09:00")
        setValue    ( "abs900Nm"  , "1.889")
        assertValue ( "turJClaro" , "188.90")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)
        
        
        execute("Sections.change", "activeSection=1")
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 
        execute("Collection.new"    , "viewObject=xava_view_section1_detalle2")
        assertDialog()
        setValue    ("horaS"      , "08:00")
        assertValue ("hora"       , "07/08/2019 08:00")
        setValue    ( "polCachaza" , "3.31")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
