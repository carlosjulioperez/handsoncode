package ec.carper.ingenio.test
 
import org.openxava.tests.*

class BagazoTest extends ModuleTestBase {

    BagazoTest(String testName) {
        super(testName, "Ingenio", "Bagazo")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()

        setValue("hora"            , "03/08/2019 18:30")
        setValue("wH2O"            , "3000")
        setValue("wBagazo"         , "300")
        setValue("brixExtracto"    , "0.66")
        setValue("polExtracto"     , "1.06")
        assertValue("polReal"      , "0.28")

        setValue("tamizVacioM0"    , "537.20")
        assertValue("muestraHumM1" , "587.20")

        setValue("muestraSecaM2"   , "569.10")
        assertValue("porcHumedad"  , "36.20")
        assertValue("brix"         , "6.79")
        assertValue("porcFibra"    , "57.01")
        assertValue("porcSacarosa" , "2.88")

        setValue("horaPorcSacJR"   , "03/08/2019 06:00")
        assertValue("porcSacJR"    , "5.07")
        setValue("gradosAguaMac"   , "83.00")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
