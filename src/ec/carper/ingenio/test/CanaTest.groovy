package ec.carper.ingenio.test
 
import org.openxava.tests.*

class CanaTest extends ModuleTestBase {

    CanaTest(String testName) {
        super(testName, "Ingenio", "Cana")
    }

    // https://sourceforge.net/p/openxava/discussion/437013/thread/89f44ba3/
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 

        execute("Collection.new","viewObject=xava_view_detalle1")
        assertDialog()
        
        setValue("hora"            , "03/08/2019 06:00")
        //printHtml()

        setValue("wH2O"            , "2400")
        setValue("wCana"           , "800")
        setValue("brixExtracto"    , "4.38")
        setValue("polExtracto"     , "15.05")
        assertValue("polReal"      , "3.86")

        setValue("tamizVacioM0"    , "540.5")
        assertValue("muestraHumM1" , "590.50")

        setValue("muestraSecaM2"   , "558")
        assertValue("porcHumedad"  , "65.00")
        assertValue("brix"         , "16.51")
        assertValue("porcFibra"    , "18.49")
        assertValue("porcSacarosa" , "14.55")
        assertValue("pureza"       , "88.13")
        assertValue("nSac"         , "1.96")
        assertValue("aR"           , "0.50")
        assertValue("porcArNsac"   , "25.51020408")

        execute("Collection.new","viewObject=xava_view_detalle2")
        assertDialog()
    }

}
