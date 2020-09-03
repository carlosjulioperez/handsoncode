package ec.carper.ingenio.test

import org.openxava.tests.*

class StockProcesoTest extends ModuleTestBase {

    StockProcesoTest(String testName) {
        super(testName, "Ingenio", "StockProceso")
    }
    
    void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        setValue("descripcion"   , "JUNIT")
        execute ("Ingenio.save")
        assertNoErrors()
    
        setConditionValues("JUNIT")
        setConditionComparators("contains_comparator")
        execute("List.filter")
        printHtml()
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute    ("StockProceso.cargarItems")

        //assertCollectionRowCount("detalle1", 62)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "30")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=1,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "95")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=2,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "60")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=3,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "45")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=4,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "95")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=5,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "70")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=7,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "100")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=8,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "85")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=9,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "100")
        setValue ( "eq"   , "4")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=10,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "100")
        setValue ( "eq"   , "6")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=11,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "90")
        setValue ( "eq"   , "8")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Collection.edit" , "row=12,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue ( "temp" , "65")
        setValue ( "eq"   , "14")
        execute("Collection.save")
        assertNoErrors()


        // assertValueInCollection("detalle1" , 0 , 2, "3.050") 
        // assertValueInCollection("detalle1" , 1 , 2, "5.650") 

        // **************************************************
        // FINALIZAR
        //execute    ("CRUD.delete")
        assertNoErrors()
    }

}
