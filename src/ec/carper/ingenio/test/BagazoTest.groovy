package ec.carper.ingenio.test
 
import org.openxava.tests.*

class BagazoTest extends ModuleTestBase {

    BagazoTest(String testName) {
        super(testName, "Ingenio", "Bagazo")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()

        setValue("horaS"           , "06:00")
        assertValue("hora"         , "07/08/2019 06:00")
        setValue("wH2O"            , "3000")
        setValue("wBagazo"         , "300")
        setValue("brixExtracto"    , "0.29")
        setValue("polExtracto"     , "1.05")
        assertValue("polReal"      , "0.27")

        setValue("tamizVacioM0"    , "536.70")
        assertValue("muestraHumM1" , "586.70")

        setValue("muestraSecaM2"   , "564.60")
        assertValue("porcHumedad"  , "44.20")
        assertValue("brix"         , "3.00")
        assertValue("porcFibra"    , "52.80")
        assertValue("porcSacarosa" , "2.79")

        setValue("horaSPorcSacJR"  , "10:00")
        setValue("horaPorcSacJR"   , "07/08/2019 10:00")
        assertValue("porcSacJR"    , "0.00")
        setValue("gradosAguaMac"   , "85.00")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
