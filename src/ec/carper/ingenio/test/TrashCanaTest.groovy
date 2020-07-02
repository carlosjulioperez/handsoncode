package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TrashCanaTest extends ModuleTestBase {

    TrashCanaTest(String testName) {
        super(testName, "Ingenio", "TrashCana")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle1", 0) // La colección esta vacía 
        assertCollectionRowCount("detalle2", 0) // La colección esta vacía 

        execute("Collection.new"   , "viewObject=xava_view_detalle1")
        assertDialog()
        setValue("horaS"           , "06:00")
        assertValue("hora"         , "07/08/2019 06:00")
        setValue("modulo.id"       , "01")
        setValue("turno.id"        , "1")
        setValue("variedad.id"     , "01")
        setValue("cantidadCana"    , "26.8")
        setValue("netaCana"        , "24.3")
        assertValue("calTrashCana" , "2.500")
        assertValue("calPorcTrash" , "9.328")
        
        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle1", 1)

        execute("Collection.new"    , "viewObject=xava_view_detalle2")
        assertDialog()
        setValue("horaS"            , "07:00")
        setValue("mlReductores"     , "37.6")
        assertValue("hora"          , "07/08/2019 07:00")
        assertValue("calTab7SusRed" , "9.40")
        assertValue("calPorcAzuRed" , "0.500")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle2", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
