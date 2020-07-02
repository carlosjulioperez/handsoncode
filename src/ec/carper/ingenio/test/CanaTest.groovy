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

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 

        execute("Collection.new","viewObject=xava_view_detalle1")
        assertDialog()
        setValue("horaS"           , "06:00")
        assertValue("hora"         , "07/08/2019 06:00")
        setValue("wH2O"            , "2400")
        setValue("wCana"           , "800")
        setValue("brixExtracto"    , "4.46")
        setValue("polExtracto"     , "15.57")
        assertValue("polReal"      , "3.99")

        setValue("tamizVacioM0"    , "540.1")
        assertValue("muestraHumM1" , "590.10")

        setValue("muestraSecaM2"   , "556.9")
        assertValue("porcHumedad"  , "66.40")
        assertValue("brix"         , "16.91")
        assertValue("porcFibra"    , "16.69")
        assertValue("porcSacarosa" , "15.13")
        assertValue("pureza"       , "89.47")
        assertValue("nSac"         , "1.78")
        assertValue("aR"           , "0.45")
        assertValue("porcArNsac"   , "25.28089888")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)

        execute("Collection.new","viewObject=xava_view_detalle2")
        assertDialog()
        setValue("horaSD"          , "06:00")
        setValue("horaDesde"       , "07/08/2019 06:00")
        setValue("horaSH"          , "11:00")
        setValue("horaHasta"       , "07/08/2019 11:00")
        assertValue("porcHumedad"  , "66.43")

        setValue("brixExtracto"    , "3.1")
        assertValue("brix"         , "11.55")
        assertValue("porcFibra"    , "22.02")

        setValue("polExtracto"     , "5.05")
        assertValue("polReal"      , "1.30")
        assertValue("porcSacarosa" , "4.84")
        assertValue("pureza"       , "41.90")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
