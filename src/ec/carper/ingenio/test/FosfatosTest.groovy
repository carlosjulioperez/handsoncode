package ec.carper.ingenio.test
 
import org.openxava.tests.*

class FosfatosTest extends ModuleTestBase {

    FosfatosTest(String testName) {
        super(testName, "Ingenio", "Fosfatos")
    }

    // Verificar con datos creados
    public void _testGrabar() throws Exception {
        login("admin", "admin")
        execute("List.viewDetail", "row=0")
        assertValue("cteN1", "0.2183")
        // No funciona con id generado
        // setValue("diaTrabajo.id", "ff8080817235e9f5017235ec85360000")
        // execute("CRUD.save");
        // assertNoErrors();
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        setValue("cteN1" , "0.2183")
        setValue("cteN2" , "0.0118")
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 

        execute("Collection.new"      , "viewObject=xava_view_detalle")
        assertDialog()
        setValue    ( "horaS"         , "09:30")
        assertValue ( "hora"          , "07/08/2019 09:30")

        setValue    ( "jdAbsorbancia" , "1.142")
        setValue    ( "jdMlMuestra"   , "2")
        assertValue ( "jdMgP"         , "0.237")
        assertValue ( "jdFosfatos"    , "118.50")
        
        setValue    ( "jcAbsorbancia" , "2.385")
        setValue    ( "jcMlMuestra"   , "10")
        assertValue ( "jcMgP"         , "0.509")
        assertValue ( "jcFosfatos"    , "50.90")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)
        
        execute("CRUD.delete")
        assertNoErrors()
    }

}
