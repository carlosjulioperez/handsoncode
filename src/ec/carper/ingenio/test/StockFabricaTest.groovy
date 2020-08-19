package ec.carper.ingenio.test

import org.openxava.tests.*

class StockFabricaTest extends ModuleTestBase {

    StockFabricaTest(String testName) {
        super(testName, "Ingenio", "StockFabrica")
    }

    void testLectura() throws Exception {
        login("admin", "admin")
        
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute("StockFabrica.editDetail" , "row=7,viewObject=xava_view_section0_detalle1")
        setValue ( "valor" , "10")
        execute("Collection.save")
        assertNoErrors()
        
        // FINALIZAR
        assertNoErrors()
    }
}
