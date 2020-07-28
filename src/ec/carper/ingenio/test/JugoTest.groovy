package ec.carper.ingenio.test
 
import ec.carper.ingenio.model.Jugo
import ec.carper.ingenio.util.SqlUtil

import org.openxava.tests.*

class JugoTest extends ModuleTestBase {

    JugoTest(String testName) {
        super(testName, "Ingenio", "Jugo")
    }
 
    void _testGetAvgField(){
        def valor = (String)SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId, "Jugo", "jdBri")
        assertEquals(valor, "13.92")
    }
    
    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue("horaS"      , "06:00")
        assertValue("hora"    , "07/08/2019 06:00")
        
        setValue    ( "jeBri" , "18.17")
        setValue    ( "jePol" , "63.36")
        assertValue ( "jeSac" , "15.37")
        assertValue ( "jePur" , "84.59")
        setValue    ( "jdBri" , "13.41")
        setValue    ( "jdPol" , "46.92")
        assertValue ( "jdSac" , "11.60")
        assertValue ( "jdPur" , "86.50")
        setValue    ( "jcBri" , "15.74")
        setValue    ( "jcPol" , "55.04")
        assertValue ( "jcSac" , "13.48")
        assertValue ( "jcPur" , "85.64")
        setValue    ( "jnBri" , "15.81")
        setValue    ( "jnPol" , "55.58")
        assertValue ( "jnSac" , "13.61")
        assertValue ( "jnPur" , "86.08")
        setValue    ( "jrBri" , "5.07")
        setValue    ( "jrPol" , "16.37")
        assertValue ( "jrSac" , "4.18")
        assertValue ( "jrPur" , "82.45")
        setValue    ( "jfBri" , "28.16")
        setValue    ( "jfPol" , "97.58")
        assertValue ( "jfSac" , "22.71")
        assertValue ( "jfPur" , "80.65")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
