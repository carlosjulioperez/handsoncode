package ec.carper.ingenio.test

import org.openxava.tests.*

class StockProcesoTest extends ModuleTestBase {

    StockProcesoTest(String testName) {
        super(testName, "Ingenio", "StockProceso")
    }
    
    void _testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        setValue("descripcion"   , "JUNIT")
        execute ("Ingenio.save")
        assertNoErrors()
    
        setConditionValues("JUNIT")
        setConditionComparators("contains_comparator")
        execute("List.filter")
        // printHtml() 
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute("StockProceso.cargarItems")

        //assertCollectionRowCount("detalle1", 62)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "30"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=1,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "95"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=2,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "60"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=3,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "45"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=4,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "95"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=5,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "70"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=7,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "100"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=8,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "85"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=9,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "100"); setValue ( "eq" , "4"); execute("Collection.save")
        
        execute("Collection.edit" , "row=10,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "100"); setValue ( "eq" , "6"); execute("Collection.save")
        
        execute("Collection.edit" , "row=11,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "90"); setValue ( "eq" , "8"); execute("Collection.save")
        
        execute("Collection.edit" , "row=12,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "65"); setValue ( "eq" , "14"); execute("Collection.save")
        
        execute("Collection.edit" , "row=13,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "65"); execute("Collection.save")
        
        execute("Collection.edit" , "row=14,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "65"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=15,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "75"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=16,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "70"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=17,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "75"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=18,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=19,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=20,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=21,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=22,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=23,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=24,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=25,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "70"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=26,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "70"); setValue ( "eq" , "13"); execute("Collection.save")
        
        execute("Collection.edit" , "row=27,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "17"); execute("Collection.save")
        
        execute("Collection.edit" , "row=28,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "17"); execute("Collection.save")
        
        execute("Collection.edit" , "row=29,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "45"); setValue ( "eq" , "18"); execute("Collection.save")
        
        execute("Collection.edit" , "row=30,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "25"); execute("Collection.save")
        
        execute("Collection.edit" , "row=31,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "25"); setValue ( "eq" , "18"); execute("Collection.save")
        
        execute("Collection.edit" , "row=32,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "25"); execute("Collection.save")
        
        execute("Collection.edit" , "row=33,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); execute("Collection.save")
        
        execute("Collection.edit" , "row=34,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); execute("Collection.save")
        
        execute("Collection.edit" , "row=35,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=36,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); execute("Collection.save")
        
        execute("Collection.edit" , "row=37,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=38,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=39,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "55"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=40,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=41,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); execute("Collection.save")
        
        execute("Collection.edit" , "row=42,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=43,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=44,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=45,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "50"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=46,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=47,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "45"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=48,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=49,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "19"); execute("Collection.save")
        
        // Ir a la siguiente página
        execute('List.goNextPage' , ',collection=detalle1')

        execute("Collection.edit" , "row=50,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); setValue ( "eq" , "19"); execute("Collection.save")
        
        execute("Collection.edit" , "row=51,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); execute("Collection.save")
        
        execute("Collection.edit" , "row=52,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); setValue ( "eq" , "17"); execute("Collection.save")
        
        execute("Collection.edit" , "row=53,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "40"); setValue ( "eq" , "17"); execute("Collection.save")
        
        execute("Collection.edit" , "row=54,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "18"); execute("Collection.save")
        
        execute("Collection.edit" , "row=55,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); setValue ( "eq" , "18"); execute("Collection.save")
        
        execute("Collection.edit" , "row=56,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "80"); setValue ( "eq" , "18"); execute("Collection.save")

        execute("Collection.edit" , "row=57,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "85"); execute("Collection.save")
        
        execute("Collection.edit" , "row=58,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "45"); setValue ( "eq" , "19"); execute("Collection.save")

        execute("Collection.edit" , "row=59,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); execute("Collection.save")

        execute("Collection.edit" , "row=60,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); execute("Collection.save")

        execute("Collection.edit" , "row=61,viewObject=xava_view_section0_detalle1"); assertDialog()
        setValue ( "temp" , "35"); execute("Collection.save")

        // Totales
        execute('Sections.change','activeSection=1')
        execute('Collection.edit','row=0,viewObject=xava_view_section1_detalle2'); assertDialog()
        setValue ("volumen1" , "1207"); setValue ("eq" , "4"); execute("Collection.save")

        execute('Collection.edit','row=1,viewObject=xava_view_section1_detalle2'); assertDialog()
        setValue ("volumen1" , "423");  setValue ("eq" , "1"); execute("Collection.save")

        // assertValueInCollection("detalle1" , 0 , 2, "3.050") 
        // assertValueInCollection("detalle1" , 1 , 2, "5.650") 

        // **************************************************
        // FINALIZAR
        //execute    ("CRUD.delete")
        assertNoErrors()
    }
    
    void testLectura() throws Exception {
        login("admin", "admin")
        
        setConditionValues     ( [ "", "JUNIT"] )
        setConditionComparators( [ "=", "contains_comparator"] )
        execute("List.filter")
        // printHtml()
        
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute("StockProceso.cargarItems")
        assertNoErrors()
        
    }

}
