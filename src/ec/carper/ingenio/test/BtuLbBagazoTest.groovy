package ec.carper.ingenio.test
 
import ec.carper.ingenio.util.SqlUtil

import org.openxava.tests.*

class BtuLbBagazoTest extends ModuleTestBase {

    BtuLbBagazoTest(String testName) {
        super(testName, "Ingenio", "BtuLbBagazo")
    }

    public void testPromedio() throws Exception {
        println SqlUtil.instance.getPromedio("ff808081711cd37c01711cd403a70000","Bagazo","porcHumedad")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
    
        setValue    ( "pHum"        , "1")
        setValue    ( "pCrisol"     , "0.25")
        setValue    ( "pCriCen"     , "0.55")
        setValue    ( "pMtra"       , "0.19")
        assertValue ( "porcHumedad" , "43.92")
        assertValue ( "porcCenBs"   , "157.895")
        assertValue ( "porcCenBh"   , "88.55")
        assertValue ( "pcBtuLb"     , "383.07")
        assertNoErrors()
    }

}
