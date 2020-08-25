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
        assertValueInCollection("detalle1" , 0 , 2, "3.050") 
        assertValueInCollection("detalle1" , 1 , 2, "5.650") 
        assertValueInCollection("detalle1" , 2 , 2, "2.330") 
        assertValueInCollection("detalle1" , 3 , 2, "6.420") 
        assertValueInCollection("detalle1" , 4 , 2, "14.120") 
        assertValueInCollection("detalle1" , 5 , 2, "12.090") 
        assertValueInCollection("detalle1" , 6 , 2, "0.820") 
        assertValueInCollection("detalle1" , 7 , 2, "16.000") 
        assertValueInCollection("detalle1" , 8 , 2, "1,055.317") 

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
        assertValueInCollection("detalle2" ,  0 , 2, "4.000") 
        assertValueInCollection("detalle2" ,  1 , 2, "4.000") 
        assertValueInCollection("detalle2" ,  2 , 2, "3.100") 
        assertValueInCollection("detalle2" ,  3 , 2, "9.920") 
        assertValueInCollection("detalle2" ,  4 , 2, "1.020") 
        assertValueInCollection("detalle2" ,  5 , 2, "8.000") 
        assertValueInCollection("detalle2" ,  6 , 2, "1.310") 
        assertValueInCollection("detalle2" ,  7 , 2, "11.230") 
        assertValueInCollection("detalle2" ,  8 , 2, "15.380") 
        assertValueInCollection("detalle2" ,  9 , 2, "13.240") 
        assertValueInCollection("detalle2" , 10 , 2, "1.580") 
        assertValueInCollection("detalle2" , 11 , 2, "20.000") 
        assertValueInCollection("detalle2" , 12 , 2, "20.000") 
        assertValueInCollection("detalle2" , 13 , 2, "1,060.448") 

        execute("Sections.change", "activeSection=2")
        assertCollectionRowCount("detalle3", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section2_detalle3")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle3" ,  0 , 2, "1.980") 
        assertValueInCollection("detalle3" ,  1 , 2, "1.820") 
        assertValueInCollection("detalle3" ,  2 , 2, "5.600") 
        assertValueInCollection("detalle3" ,  3 , 2, "15.320") 
        assertValueInCollection("detalle3" ,  4 , 2, "13.210") 
        assertValueInCollection("detalle3" ,  5 , 2, "0.780") 
        assertValueInCollection("detalle3" ,  6 , 2, "100.000") 
        assertValueInCollection("detalle3" ,  7 , 2, "1,060.448") 

        execute("Sections.change", "activeSection=3")
        assertCollectionRowCount("detalle4", 9)
        execute("StockFabrica.editDetail" , "row=7,viewObject=xava_view_section3_detalle4")
        assertDialog()
        setValue    ( "valor" , "79")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle4" ,  0 , 2, "2.450") 
        assertValueInCollection("detalle4" ,  1 , 2, "3.350") 
        assertValueInCollection("detalle4" ,  2 , 2, "1.500") 
        assertValueInCollection("detalle4" ,  3 , 2, "9.730") 
        assertValueInCollection("detalle4" ,  4 , 2, "21.520") 
        assertValueInCollection("detalle4" ,  5 , 2, "16.680") 
        assertValueInCollection("detalle4" ,  6 , 2, "1.770") 
        assertValueInCollection("detalle4" ,  7 , 2, "79.000") 
        assertValueInCollection("detalle4" ,  8 , 2, "1,087.675")

        // Clarificador de Jugo
        execute("Sections.change", "activeSection=4")
        assertCollectionRowCount("detalle5", 6)
        execute("StockFabrica.editDetail" , "row=4,viewObject=xava_view_section4_detalle5")
        assertDialog()
        setValue    ( "valor" , "50")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle5" ,  0 , 2, "129.650") 
        assertValueInCollection("detalle5" ,  1 , 2, "15.380") 
        assertValueInCollection("detalle5" ,  2 , 2, "13.240") 
        assertValueInCollection("detalle5" ,  3 , 2, "18.200") 
        assertValueInCollection("detalle5" ,  4 , 2, "50.000") 
        assertValueInCollection("detalle5" ,  5 , 2, "1,060.448") 

        // Torres de sulfitación
        execute("Sections.change", "activeSection=5")
        assertCollectionRowCount("detalle6", 12)
        execute("StockFabrica.editDetail" , "row=10,viewObject=xava_view_section5_section0_detalle6")
        assertDialog()
        setValue    ( "valor" , "60")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle6" ,   0 , 2, "2.500") 
        assertValueInCollection("detalle6" ,   1 , 2, "4.300") 
        assertValueInCollection("detalle6" ,   2 , 2, "21.110") 
        assertValueInCollection("detalle6" ,   3 , 2, "0.200") 
        assertValueInCollection("detalle6" ,   4 , 2, "0.250") 
        assertValueInCollection("detalle6" ,   5 , 2, "0.440") 
        assertValueInCollection("detalle6" ,   6 , 2, "12.930") 
        assertValueInCollection("detalle6" ,   7 , 2, "15.320") 
        assertValueInCollection("detalle6" ,   8 , 2, "13.210") 
        assertValueInCollection("detalle6" ,   9 , 2, "1.810") 
        assertValueInCollection("detalle6" ,  10 , 2, "60.000") 
        assertValueInCollection("detalle6" ,  11 , 2, "1,060.448") 

        execute("Sections.change", "activeSection=1,viewObject=xava_view_section5")
        assertCollectionRowCount("detalle7", 12)
        execute("StockFabrica.editDetail" , "row=10,viewObject=xava_view_section5_section1_detalle7")
        assertDialog()
        setValue    ( "valor" , "0")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle7" ,   0 , 2, "1.500") 
        assertValueInCollection("detalle7" ,   1 , 2, "1.700") 
        assertValueInCollection("detalle7" ,   2 , 2, "3.000") 
        assertValueInCollection("detalle7" ,   3 , 2, "0.210") 
        assertValueInCollection("detalle7" ,   4 , 2, "0.500") 
        assertValueInCollection("detalle7" ,   5 , 2, "0.340") 
        assertValueInCollection("detalle7" ,   6 , 2, "0.000") 
        assertValueInCollection("detalle7" ,   7 , 2, "64.050") 
        assertValueInCollection("detalle7" ,   8 , 2, "55.060") 
        assertValueInCollection("detalle7" ,   9 , 2, "0.000") 
        assertValueInCollection("detalle7" ,  10 , 2, "0.000") 
        assertValueInCollection("detalle7" ,  11 , 2, "1,310.490") 

        // Calentadores de Jugo
        execute("Sections.change", "activeSection=6")
        assertCollectionRowCount("detalle8", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section6_section0_detalle8")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle8" ,  0 , 2, "1.400") 
        assertValueInCollection("detalle8" ,  1 , 2, "5.060") 
        assertValueInCollection("detalle8" ,  2 , 2, "7.790") 
        assertValueInCollection("detalle8" ,  3 , 2, "15.380") 
        assertValueInCollection("detalle8" ,  4 , 2, "13.240") 
        assertValueInCollection("detalle8" ,  5 , 2, "1.090") 
        assertValueInCollection("detalle8" ,  6 , 2, "100.000") 
        assertValueInCollection("detalle8" ,  7 , 2, "1,060.448") 

        execute("Sections.change", "activeSection=1,viewObject=xava_view_section6")
        assertCollectionRowCount("detalle9", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section6_section1_detalle9")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle9" ,  0 , 2, "1.200") 
        assertValueInCollection("detalle9" ,  1 , 2, "5.060") 
        assertValueInCollection("detalle9" ,  2 , 2, "5.720") 
        assertValueInCollection("detalle9" ,  3 , 2, "14.120") 
        assertValueInCollection("detalle9" ,  4 , 2, "12.090") 
        assertValueInCollection("detalle9" ,  5 , 2, "0.730") 
        assertValueInCollection("detalle9" ,  6 , 2, "100.000") 
        assertValueInCollection("detalle9" ,  7 , 2, "1,055.317") 

        execute("Sections.change", "activeSection=2,viewObject=xava_view_section6")
        assertCollectionRowCount("detalle10", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section6_section2_detalle10")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle10" ,  0 , 2, "1.200") 
        assertValueInCollection("detalle10" ,  1 , 2, "5.060") 
        assertValueInCollection("detalle10" ,  2 , 2, "5.720") 
        assertValueInCollection("detalle10" ,  3 , 2, "14.120") 
        assertValueInCollection("detalle10" ,  4 , 2, "12.090") 
        assertValueInCollection("detalle10" ,  5 , 2, "0.730") 
        assertValueInCollection("detalle10" ,  6 , 2, "100.000") 
        assertValueInCollection("detalle10" ,  7 , 2, "1,055.317") 

        execute("Sections.change", "activeSection=3,viewObject=xava_view_section6")
        assertCollectionRowCount("detalle11", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section6_section3_detalle11")
        assertDialog()
        setValue    ( "valor" , "0")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle11" ,  0 , 2, "1.200") 
        assertValueInCollection("detalle11" ,  1 , 2, "5.060") 
        assertValueInCollection("detalle11" ,  2 , 2, "0.000") 
        assertValueInCollection("detalle11" ,  3 , 2, "14.120") 
        assertValueInCollection("detalle11" ,  4 , 2, "12.090") 
        assertValueInCollection("detalle11" ,  5 , 2, "0.000") 
        assertValueInCollection("detalle11" ,  6 , 2, "0.000") 
        assertValueInCollection("detalle11" ,  7 , 2, "1,055.317") 

        execute("Sections.change", "activeSection=4,viewObject=xava_view_section6")
        assertCollectionRowCount("detalle12", 8)
        execute("StockFabrica.editDetail" , "row=6,viewObject=xava_view_section6_section4_detalle12")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle12" ,  0 , 2, "1.200") 
        assertValueInCollection("detalle12" ,  1 , 2, "5.060") 
        assertValueInCollection("detalle12" ,  2 , 2, "5.720") 
        assertValueInCollection("detalle12" ,  3 , 2, "14.120") 
        assertValueInCollection("detalle12" ,  4 , 2, "12.090") 
        assertValueInCollection("detalle12" ,  5 , 2, "0.730") 
        assertValueInCollection("detalle12" ,  6 , 2, "100.000") 
        assertValueInCollection("detalle12" ,  7 , 2, "1,055.317") 

        // Línea de Evaporación
        execute("Sections.change", "activeSection=7")
        assertCollectionRowCount("detalle13", 13)
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section0_detalle13")
        assertDialog()
        setValue    ( "valor" , "0.75")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle13" ,  0  , 2,  "4.100") 
        assertValueInCollection("detalle13" ,  1  , 2,  "0.750")
        assertValueInCollection("detalle13" ,  2  , 2,  "9.902")
        assertValueInCollection("detalle13" ,  3  , 2,  "1.149")
        assertValueInCollection("detalle13" ,  4  , 2, "15.170")
        assertValueInCollection("detalle13" ,  5  , 2,  "1.980")
        assertValueInCollection("detalle13" ,  6  , 2,  "0.398")
        assertValueInCollection("detalle13" ,  7  , 2,  "3.006")
        assertValueInCollection("detalle13" ,  8  , 2, "28.078")
        assertValueInCollection("detalle13" ,  9  , 2, "15.380")
        assertValueInCollection("detalle13" ,  10 , 2, "13.240")
        assertValueInCollection("detalle13" ,  11 , 2,  "3.940")
        assertValueInCollection("detalle13" ,  12 , 2, "1,060.448")
        
        execute("Sections.change", "activeSection=1,viewObject=xava_view_section7")
        assertCollectionRowCount("detalle14", 13)
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section1_detalle14")
        assertDialog()
        setValue    ( "valor" , "0.68")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle14" ,  0  , 2,  "3.900") 
        assertValueInCollection("detalle14" ,  1  , 2,  "0.680")
        assertValueInCollection("detalle14" ,  2  , 2,  "8.123")
        assertValueInCollection("detalle14" ,  3  , 2,  "1.150")
        assertValueInCollection("detalle14" ,  4  , 2, "13.738")
        assertValueInCollection("detalle14" ,  5  , 2,  "1.580")
        assertValueInCollection("detalle14" ,  6  , 2,  "0.434")
        assertValueInCollection("detalle14" ,  7  , 2,  "2.712")
        assertValueInCollection("detalle14" ,  8  , 2, "24.573")
        assertValueInCollection("detalle14" ,  9  , 2, "25.000")
        assertValueInCollection("detalle14" ,  10 , 2, "21.520")
        assertValueInCollection("detalle14" ,  11 , 2,  "5.840")
        assertValueInCollection("detalle14" ,  12 , 2, "1,103.589")
        
        execute("Sections.change", "activeSection=2,viewObject=xava_view_section7")
        assertCollectionRowCount("detalle15", 13)
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section2_detalle15")
        assertDialog()
        setValue    ( "valor" , "0.18")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle15" ,  0  , 2,  "2.400") 
        assertValueInCollection("detalle15" ,  1  , 2,  "0.180")
        assertValueInCollection("detalle15" ,  2  , 2,  "0.814")
        assertValueInCollection("detalle15" ,  3  , 2,  "1.100")
        assertValueInCollection("detalle15" ,  4  , 2,  "4.976")
        assertValueInCollection("detalle15" ,  5  , 2,  "0.875")
        assertValueInCollection("detalle15" ,  6  , 2,  "0.277")
        assertValueInCollection("detalle15" ,  7  , 2,  "0.626")
        assertValueInCollection("detalle15" ,  8  , 2,  "6.416")
        assertValueInCollection("detalle15" ,  9  , 2, "36.000")
        assertValueInCollection("detalle15" ,  10 , 2, "30.990")
        assertValueInCollection("detalle15" ,  11 , 2,  "2.300")
        assertValueInCollection("detalle15" ,  12 , 2, "1,156.297")
        
        execute("Sections.change", "activeSection=3,viewObject=xava_view_section7")
        assertCollectionRowCount("detalle16", 13)
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section3_detalle16")
        assertDialog()
        setValue    ( "valor" , "0.96")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle16" ,  0  , 2,  "2.400") 
        assertValueInCollection("detalle16" ,  1  , 2,  "0.960")
        assertValueInCollection("detalle16" ,  2  , 2,  "4.343")
        assertValueInCollection("detalle16" ,  3  , 2,  "1.100")
        assertValueInCollection("detalle16" ,  4  , 2,  "4.976")
        assertValueInCollection("detalle16" ,  5  , 2,  "0.875")
        assertValueInCollection("detalle16" ,  6  , 2,  "0.277")
        assertValueInCollection("detalle16" ,  7  , 2,  "0.626")
        assertValueInCollection("detalle16" ,  8  , 2,  "9.945")
        assertValueInCollection("detalle16" ,  9  , 2, "64.050")
        assertValueInCollection("detalle16" ,  10 , 2, "55.060")
        assertValueInCollection("detalle16" ,  11 , 2,  "7.180")
        assertValueInCollection("detalle16" ,  12 , 2, "1,310.490")
        
        execute("Sections.change", "activeSection=4,viewObject=xava_view_section7")
        assertCollectionRowCount("detalle17", 13)
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section4_detalle17")
        assertDialog()
        setValue    ( "valor" , "0")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle17" ,  0  , 2,  "2.400") 
        assertValueInCollection("detalle17" ,  1  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  2  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  3  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  4  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  5  , 2,  "0.875")
        assertValueInCollection("detalle17" ,  6  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  7  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  8  , 2,  "0.000")
        assertValueInCollection("detalle17" ,  9  , 2, "64.050")
        assertValueInCollection("detalle17" ,  10 , 2, "55.060")
        assertValueInCollection("detalle17" ,  11 , 2,  "0.000")
        assertValueInCollection("detalle17" ,  12 , 2, "1,310.490")
        
        // Tq Meladura Cruda
        execute("Sections.change", "activeSection=8")
        assertCollectionRowCount("detalle18", 6)
        execute("StockFabrica.editDetail" , "row=4,viewObject=xava_view_section8_detalle18")
        assertDialog()
        setValue    ( "valor" , "93")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle18" ,  0 , 2, "7.530") 
        assertValueInCollection("detalle18" ,  1 , 2, "64.050") 
        assertValueInCollection("detalle18" ,  2 , 2, "55.060") 
        assertValueInCollection("detalle18" ,  3 , 2, "5.430") 
        assertValueInCollection("detalle18" ,  4 , 2, "93.000") 
        assertValueInCollection("detalle18" ,  5 , 2, "1,310.490") 
        
        // Calentador meladura
        // I
        execute("Sections.change", "activeSection=9")
        assertCollectionRowCount("detalle19", 6)
        execute("StockFabrica.editDetail" , "row=4,viewObject=xava_view_section9_section0_detalle19")
        assertDialog()
        setValue    ( "valor" , "100")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle19" ,  0 , 2, "0.550") 
        assertValueInCollection("detalle19" ,  1 , 2, "64.050") 
        assertValueInCollection("detalle19" ,  2 , 2, "55.060") 
        assertValueInCollection("detalle19" ,  3 , 2, "0.400") 
        assertValueInCollection("detalle19" ,  4 , 2, "100.000") 
        assertValueInCollection("detalle19" ,  5 , 2, "1,310.490") 
        
        // II
        execute("Sections.change", "activeSection=1,viewObject=xava_view_section9")
        assertCollectionRowCount("detalle20", 6)
        execute("StockFabrica.editDetail" , "row=4,viewObject=xava_view_section9_section1_detalle20")
        assertDialog()
        setValue    ( "valor" , "0")
        execute("Collection.save")
        assertNoErrors()
        assertValueInCollection("detalle20" ,  0 , 2, "0.000") 
        assertValueInCollection("detalle20" ,  1 , 2, "64.050") 
        assertValueInCollection("detalle20" ,  2 , 2, "55.060") 
        assertValueInCollection("detalle20" ,  3 , 2, "0.000") 
        assertValueInCollection("detalle20" ,  4 , 2, "0.000") 
        assertValueInCollection("detalle20" ,  5 , 2, "1,310.490") 
        
        // FINALIZAR
        //execute    ("CRUD.delete")
        assertNoErrors()
    }

    void _testLectura() throws Exception {
        login("admin", "admin")
        
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        execute("Sections.change", "activeSection=7")
        execute("Sections.change", "activeSection=1,viewObject=xava_view_section7")
        execute("StockFabrica.editDetail" , "row=1,viewObject=xava_view_section7_section1_detalle14")
        setValue ( "valor" , "0.68")
        execute("Collection.save")
        assertNoErrors()
        
        // FINALIZAR
        assertNoErrors()
    }
}
