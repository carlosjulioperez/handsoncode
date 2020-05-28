package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TrashTest extends ModuleTestBase {

    TrashTest(String testName) {
        super(testName, "Ingenio", "Trash")
    }

    // Este formulario se crea con datos parciales desde 
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new"           , "viewObject=xava_view_detalle")
        assertDialog()
        setValue("hora"                    , "03/08/2019 17:00")
        setValue("modulo.id"               , "04")
        setValue("turno.id"                , "2")
        setValue("variedad.id"             , "01")
        setValue("cantidadCana"            , "23.70")
        setValue("netaCana"                , "22.60")
        setValue("calTrashCana"            , "1.100")
        setValue("calPorcTrash"            , "4.640")

        setValue("cogollos"                , "0.50")
        assertValue("calPorcCogollos"      , "2.11")

        setValue("hojas"                   , "0.40")
        assertValue("calPorcHojas"         , "1.69")

        setValue("cepa"                    , "0.10")
        assertValue("calPorcCepa"          , "0.42")

        setValue("canaSeca"                , "0.10")
        assertValue("calPorcCanaSeca"      , "0.42")

        setValue("suelo"                   , "0.00")
        assertValue("calPorcSuelo"         , "0.00")

        setValue("otros"                   , "0.00")
        assertValue("calPorcOtros"         , "0.00")

        setValue("canaInfectada"           , "1.50")
        assertValue("calPorcCanaInfectada" , "6.64")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
