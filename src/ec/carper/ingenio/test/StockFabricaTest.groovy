package ec.carper.ingenio.test

import org.openxava.tests.*

class StockFabricaTest extends ModuleTestBase {

    StockFabricaTest(String testName) {
        super(testName, "Ingenio", "StockFabrica")
    }
    
    void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        setValue("descripcion"   , "JUNIT")
        execute ("Ingenio.save")
        assertNoErrors()
        
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute    ("StockFabrica.cargarItems")
        assertNoErrors()
        
        assertCollectionRowCount("detalle1", 9)
        execute("StockFabrica.editDetail" , "row=7,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue    ( "valor" , "16")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle1" , 0 , 2, "3.05") 
        assertValueInCollection("detalle1" , 1 , 2, "5.65") 
        assertValueInCollection("detalle1" , 2 , 2, "2.33") 
        assertValueInCollection("detalle1" , 3 , 2, "6.42") 
        assertValueInCollection("detalle1" , 4 , 2, "14.12") 
        assertValueInCollection("detalle1" , 5 , 2, "12.09") 
        assertValueInCollection("detalle1" , 6 , 2, "0.82") 
        assertValueInCollection("detalle1" , 7 , 2, "16.00") 
        assertValueInCollection("detalle1" , 8 , 2, "1,055.32") 

        execute("Sections.change", "activeSection=1")
        assertCollectionRowCount("detalle2", 14)
        execute("StockFabrica.editDetail" , "row=11,viewObject=xava_view_section1_detalle2")
        assertDialog()
        setValue    ( "valor" , "20")
        execute("Collection.save")
        assertNoErrors()
        execute("StockFabrica.editDetail" , "row=12,viewObject=xava_view_section1_detalle2")
        assertDialog()
        setValue    ( "valor" , "20")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle2" ,  0 , 2, "4.00") 
        assertValueInCollection("detalle2" ,  1 , 2, "4.00") 
        assertValueInCollection("detalle2" ,  2 , 2, "3.10") 
        assertValueInCollection("detalle2" ,  3 , 2, "9.92") 
        assertValueInCollection("detalle2" ,  4 , 2, "1.02") 
        assertValueInCollection("detalle2" ,  5 , 2, "8.00") 
        assertValueInCollection("detalle2" ,  6 , 2, "1.31") 
        assertValueInCollection("detalle2" ,  7 , 2, "11.23") 
        assertValueInCollection("detalle2" ,  8 , 2, "15.38") 
        assertValueInCollection("detalle2" ,  9 , 2, "13.24") 
        assertValueInCollection("detalle2" , 10 , 2, "1.58") 
        assertValueInCollection("detalle2" , 11 , 2, "20.00") 
        assertValueInCollection("detalle2" , 12 , 2, "20.00") 
        assertValueInCollection("detalle2" , 13 , 2, "1,060.45") 

        execute("Sections.change", "activeSection=2")
        assertCollectionRowCount("detalle3", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section2_detalle3")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle3" ,  0 , 2, "1.98") 
        assertValueInCollection("detalle3" ,  1 , 2, "1.82") 
        assertValueInCollection("detalle3" ,  2 , 2, "5.60") 
        assertValueInCollection("detalle3" ,  3 , 2, "15.32") 
        assertValueInCollection("detalle3" ,  4 , 2, "13.21") 
        assertValueInCollection("detalle3" ,  5 , 2, "0.78") 
        assertValueInCollection("detalle3" ,  6 , 2, "100.00") 
        assertValueInCollection("detalle3" ,  7 , 2, "1,060.45") 

        execute("Sections.change", "activeSection=3")
        assertCollectionRowCount("detalle4", 9)
        execute("StockFabrica.editDetail" , "row=7,viewObject=xava_view_section3_detalle4")
        assertDialog()
        setValue    ( "valor" , "79")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle4" ,  0 , 2, "2.45") 
        assertValueInCollection("detalle4" ,  1 , 2, "3.35") 
        assertValueInCollection("detalle4" ,  2 , 2, "1.50") 
        assertValueInCollection("detalle4" ,  3 , 2, "9.73") 
        assertValueInCollection("detalle4" ,  4 , 2, "21.52") 
        assertValueInCollection("detalle4" ,  5 , 2, "16.68") 
        assertValueInCollection("detalle4" ,  6 , 2, "1.77") 
        assertValueInCollection("detalle4" ,  7 , 2, "79.00") 
        assertValueInCollection("detalle4" ,  8 , 2, "1,087.68") 

        execute("Sections.change", "activeSection=4")
        assertCollectionRowCount("detalle5", 6)
        execute("StockFabrica.editDetail" , "row=4,viewObject=xava_view_section4_detalle5")
        assertDialog()
        setValue    ( "valor" , "50")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle5" ,  0 , 2, "129.65") 
        assertValueInCollection("detalle5" ,  1 , 2, "15.38") 
        assertValueInCollection("detalle5" ,  2 , 2, "13.24") 
        assertValueInCollection("detalle5" ,  3 , 2, "18.20") 
        assertValueInCollection("detalle5" ,  4 , 2, "50.00") 
        assertValueInCollection("detalle5" ,  5 , 2, "1,060.45") 

        // FINALIZAR
        execute    ("CRUD.delete")
        assertNoErrors()
    }

    void _testLectura() throws Exception {
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
