package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MasasTest extends ModuleTestBase {

    MasasTest(String testName) {
        super(testName, "Ingenio", "Masas")
    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle3", 0) // La colección esta vacía 

        execute("Collection.new" , "viewObject=xava_view_detalle1")
        assertDialog()
        setValue    ( "hora" ,  "03/08/2019 11:25")
        setValue    ( "bri"  ,  "13.61")
        setValue    ( "pol"  ,  "50.10")
        assertValue ( "sac"  ,  "74.28")
        assertValue ( "pur"  ,  "90.96")
        assertValue ( "bri2" ,  "81.66")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle2")
        assertDialog()
        setValue    ( "hora" , "03/08/2019 11:37")
        setValue    ( "bri"  , "15.70")
        setValue    ( "pol"  , "45.11")
        assertValue ( "sac"  , "66.32")
        assertValue ( "pur"  , "70.40")
        assertValue ( "bri2" , "94.20")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle3")
        assertDialog()
        setValue    ( "hora" , "04/08/2019 04:30")
        setValue    ( "bri"  , "16.19")
        setValue    ( "pol"  , "44.32")
        assertValue ( "sac"  , "65.03")
        assertValue ( "pur"  , "66.94")
        assertValue ( "bri2" , "97.14")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle3", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle4")
        assertDialog()
        setValue    ( "hora" , "03/08/2019 15:30")
        setValue    ( "bri"  , "15.17")
        setValue    ( "pol"  , "54.8")
        assertValue ( "sac"  , "80.74")
        assertValue ( "pur"  , "88.71")
        assertValue ( "bri2" , "91.02")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle4", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle5")
        assertDialog()
        setValue    ( "hora" , "03/08/2019 19:35")
        setValue    ( "bri"  , "15.44")
        setValue    ( "pol"  , "57.72")
        assertValue ( "sac"  , "84.95")
        assertValue ( "pur"  , "91.70")
        assertValue ( "bri2" , "92.64")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle5", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle6")
        assertDialog()
        setValue    ( "hora" , "03/08/2019 22:30")
        setValue    ( "bri"  , "15.53")
        setValue    ( "pol"  , "55.68")
        assertValue ( "sac"  , "81.91")
        assertValue ( "pur"  , "87.91")
        assertValue ( "bri2" , "93.18")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle6", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle7")
        assertDialog()
        setValue    ( "hora" , "04/08/2019 01:00")
        setValue    ( "bri"  , "15.60")
        setValue    ( "pol"  , "57.38")
        assertValue ( "sac"  , "84.39")
        assertValue ( "pur"  , "90.16")
        assertValue ( "bri2" , "93.60")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle7", 1)

        execute("Collection.new" , "viewObject=xava_view_detalle8")
        assertDialog()
        setValue    ( "hora" , "03/08/2019 03:40")
        setValue    ( "bri"  , "15.49")
        setValue    ( "pol"  , "55.63")
        assertValue ( "sac"  , "81.85")
        assertValue ( "pur"  , "88.07")
        assertValue ( "bri2" , "92.94")
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle8", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
