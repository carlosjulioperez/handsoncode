package ec.carper.ingenio.test
 
import org.openxava.tests.*

class TrashTest extends ModuleTestBase {

    TrashTest(String testName) {
        super(testName, "Ingenio", "Trash")
    }

    // Este formulario se crea con datos parciales desde 
    void testPonerDetalle() throws Exception {
        login("admin", "admin")
        assertListNotEmpty()
        execute("List.viewDetail", "row=0")
        execute("List.orderBy"   , "property=hora,collection=detalle")
        execute("Collection.edit", "row=0,viewObject=xava_view_section0_detalle")
        
        assertDialog()

        setValue("horaS"                   , "06:00")
        assertValue("hora"                 , "07/08/2019 06:00")
        setValue("modulo.id"               , "01")
        setValue("turno.id"                , "1")
        setValue("variedad.id"             , "01")
        setValue("cantidadCana"            , "26.8")
        setValue("netaCana"                , "24.3")
        assertValue("calTrashCana"         , "2.500")
        assertValue("calPorcTrash"         , "9.328")

        setValue("cogollos"                , "1")
        assertValue("calPorcCogollos"      , "3.73")

        setValue("hojas"                   , "0.60")
        assertValue("calPorcHojas"         , "2.24")

        setValue("cepa"                    , "0.20")
        assertValue("calPorcCepa"          , "0.75")

        setValue("canaSeca"                , "0.00")
        assertValue("calPorcCanaSeca"      , "0.00")

        setValue("suelo"                   , "0.00")
        assertValue("calPorcSuelo"         , "0.00")

        setValue("otros"                   , "0.00")
        assertValue("calPorcOtros"         , "0.00")

        setValue("canaInfectada"           , "0.50")
        assertValue("calPorcCanaInfectada" , "2.06")

        assertNoErrors()
    }

}
