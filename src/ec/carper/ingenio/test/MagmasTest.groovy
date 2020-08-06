package ec.carper.ingenio.test
 
import org.openxava.tests.*

class MagmasTest extends ModuleTestBase {

    MagmasTest(String testName) {
        super(testName, "Ingenio", "Magmas")
    }
    
    // Modificar
    public void _testValidarPromedios() throws Exception {
        login("admin", "admin")

        assertValueInList(0, 0, "DIA TRABAJO 03/08/2019");
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        
        assertValue("diaTrabajo.id"     , "ff808081711cd37c01711cd403a70000");
        assertValue("promMbBri"  , "14.93");
        assertValue("promMbPol"  , "58.97");
        assertValue("promMbSac"  , "86.97");
        assertValue("promMbPur"  , "97.05");
        assertValue("promMbBri2" , "89.60");

        // assertCollectionRowCount ( "productos", 2); // Tiene 2 productos
        // assertValueInCollection  ( "productos", 0, "numero", "2")
        // assertValueInCollection  ( "productos", 0, "descripcion", "Arco iris de lágrimas")
        // assertValueInCollection  ( "productos", 1, "numero", "3")
        // assertValueInCollection  ( "productos", 1, "descripcion", "Ritmo de sangre")

    }
 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new", "viewObject=xava_view_section0_detalle")
        assertDialog()
        
        setValue    ( "horaS"  , "08:00")
        assertValue ( "hora"   , "07/08/2019 08:00")

        setValue    ( "mbBri"  , "15.16")
        setValue    ( "mbPol"  , "59.74")
        assertValue ( "mbSac"  , "88.02")
        assertValue ( "mbPur"  , "96.77")
        assertValue ( "mbBri2" , "90.96")

        setValue    ( "mcBri"  , "14.47")
        setValue    ( "mcPol"  , "56.69")
        assertValue ( "mcSac"  , "83.76")
        assertValue ( "mcPur"  , "96.48")
        assertValue ( "mcBri2" , "86.82")

        setValue    ( "mrBri"  , "15.17")
        setValue    ( "mrPol"  , "60.49")
        assertValue ( "mrSac"  , "89.12")
        assertValue ( "mrPur"  , "97.91")
        assertValue ( "mrBri2" , "91.02")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
