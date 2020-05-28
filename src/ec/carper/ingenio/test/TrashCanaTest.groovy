package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TrashCanaTest extends ModuleTestBase {

    TrashCanaTest(String testName) {
        super(testName, "Ingenio", "TrashCana")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 

        execute("Collection.new"   , "viewObject=xava_view_detalle1")
        assertDialog()
        setValue("hora"            , "03/08/2019 06:00")
        setValue("modulo.id"       , "04")
        setValue("turno.id"        , "2")
        setValue("variedad.id"     , "01")
        setValue("cantidadCana"    , "24")
        setValue("netaCana"        , "22.1")
        assertValue("calTrashCana" , "1.900")
        assertValue("calPorcTrash" , "7.917")
        
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)

        execute("Collection.new"    , "viewObject=xava_view_detalle2")
        assertDialog()
        setValue("hora"             , "03/08/2019 07:00")
        setValue("mlReductores"     , "37.6")
        assertValue("calTab7SusRed" , "9.40")
        assertValue("calPorcAzuRed" , "0.500")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
