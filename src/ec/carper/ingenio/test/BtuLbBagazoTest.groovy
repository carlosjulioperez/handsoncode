package ec.carper.ingenio.test
 
import ec.carper.ingenio.util.SqlUtil

import org.openxava.tests.*

class BtuLbBagazoTest extends ModuleTestBase {

    BtuLbBagazoTest(String testName) {
        super(testName, "Ingenio", "BtuLbBagazo")
    }

    public void testPromedio() throws Exception {
        println SqlUtil.instance.getValorCampo(Aux.instance.diaTrabajoId,"Bagazo","porcHumedad")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
    
        setValue    ( "pHum"        , "1")
        setValue    ( "pCrisol"     , "0.25")
        setValue    ( "pCriCen"     , "0.55")
        setValue    ( "pMtra"       , "0.19")
        assertValue ( "porcHumedad" , "46.00")
        assertValue ( "porcCenBs"   , "157.895")
        assertValue ( "porcCenBh"   , "85.26")
        assertValue ( "pcBtuLb"     , "474.78")
        assertNoErrors()
    }

}
