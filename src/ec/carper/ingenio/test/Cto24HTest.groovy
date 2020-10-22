package ec.carper.ingenio.test
 
import org.openxava.tests.*

class Cto24HTest extends ModuleTestBase {

    Cto24HTest(String testName) {
        super(testName, "Ingenio", "Cto24H")
    }

    // Test especial con ejecución de acciones
    void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")
        
        setValue("diaTrabajo.id" , Aux.instance.diaTrabajoId)
        setValue("descripcion"   , "JUNIT")
        execute ("Ingenio.save")
        assertNoErrors()
        
        setConditionValues(["JUNIT"]); // Establece los valores para filtrar los datos
        setConditionComparators(["contains_comparator"]); // Pone los comparadores para filtrar los datosprintHtml()
        execute("List.filter");

        printHtml()
        execute("List.viewDetail", "row=0") // Para test con BD datos de prueba
        assertValue("descripcion", "JUNIT")
        execute    ("Cto24H.cargarDetalles")
        // Detalle 1
        assertCollectionRowCount("detalle1", 1)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section0_detalle1")
        assertDialog()
        setValue    ( "cana"       , "1")
        setValue    ( "j1Extracto" , "2")
        setValue    ( "jDiluido"   , "3")
        setValue    ( "jClaro"     , "4")
        setValue    ( "jFiltrado"  , "5")
        setValue    ( "mClara"     , "6")
        setValue    ( "mielA"      , "7")
        setValue    ( "mielB"      , "8")
        setValue    ( "mielF"      , "9")
        assertValue ( "pd11"       , "771.36")
        assertValue ( "pd12"       , "128.56")
        assertValue ( "pd13"       , "85.71")
        assertValue ( "pd14"       , "64.28")
        assertValue ( "pd15"       , "51.42")
        assertValue ( "pd16"       , "171.41")
        assertValue ( "pd17"       , "146.93")
        assertValue ( "pd18"       , "128.56")
        assertValue ( "pd19"       , "114.28")
        execute("Collection.save")
        assertNoErrors()

        execute("Sections.change", "activeSection=1")
        assertCollectionRowCount("detalle2", 1)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section1_detalle2")
        assertDialog()
        setValue    ( "bv600"       , "224.0168")
        setValue    ( "bv50"        , "28.8723")
        setValue    ( "sc4"         , "4.0003")
        setValue    ( "sc8"         , "8")
        setValue    ( "pf"          , "2.3628")
        setValue    ( "pj"          , "150")
        setValue    ( "bs600"       , "222.6286")
        setValue    ( "bs50"        , "43.8265")
        assertValue ( "masa1"       , "267.2522")
        assertValue ( "masa2"       , "417.2522") 
        assertValue ( "masa3"       , "266.4551")
        assertValue ( "porcInso"    , "-0.53")
        execute("Collection.save")
        assertNoErrors()

        execute("Sections.change", "activeSection=2")
        assertCollectionRowCount("detalle3", 1)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section2_detalle3")
        assertDialog()
        setValue    ( "cana"       , "0")
        assertValue ( "pd311"      , "0.00")
        assertValue ( "pd321"      , "0.00")

        setValue    ( "j1Extracto" , "21.5")
        assertValue ( "pd312"      , "10.75")
        assertValue ( "pd322"      , "0.43")

        setValue    ( "jDiluido"   , "25.2")
        assertValue ( "pd313"      , "12.60")
        assertValue ( "pd323"      , "0.37")

        setValue    ( "jClaro"     , "0")
        assertValue ( "pd314"      , "0.00")
        assertValue ( "pd324"      , "0.00")

        setValue    ( "jFiltrado"  , "0")
        assertValue ( "pd315"      , "0.00")
        assertValue ( "pd325"      , "0.00")

        setValue    ( "mClara"     , "10.2")
        assertValue ( "pd316"      , "0.46")
        assertValue ( "pd326"      , "1.84")

        setValue    ( "mielA"      , "0")
        assertValue ( "pd317"      , "0.00")
        assertValue ( "pd327"      , "0.00")

        setValue    ( "mielB"      , "0")
        assertValue ( "pd318"      , "0.00")
        assertValue ( "pd328"      , "0.00")

        setValue    ( "mielF"      , "15.6")
        assertValue ( "pd319"      , "0.30")
        assertValue ( "pd329"      , "7.50")

        execute("Collection.save")
        assertNoErrors()

        // file:///home/carper/local/utils/ox/openxava-6.3.2/doc/docs/basic-business-logic_es.html#Pruebas-JUnit
        execute("Sections.change", "activeSection=3")
        assertCollectionRowCount("detalle41", 1)
        
        assertValueInCollection("detalle41" , 0 , "j1Extracto" , "18.61")
        assertValueInCollection("detalle41" , 0 , "jDiluido"   , "14.12")
        assertValueInCollection("detalle41" , 0 , "jClaro"     , "15.38")
        assertValueInCollection("detalle41" , 0 , "jFiltrado"  , "21.52")
        assertValueInCollection("detalle41" , 0 , "mClara"     , "64.86")
        assertValueInCollection("detalle41" , 0 , "mielA"      , "77.18")
        assertValueInCollection("detalle41" , 0 , "mielB"      , "85.56")
        assertValueInCollection("detalle41" , 0 , "mielF"      , "86.25")

        assertCollectionRowCount("detalle42", 4)
        (0..3).each{
            setValueInCollection("detalle42", it, "j1Extracto" , "${it+1}")
            setValueInCollection("detalle42", it, "jDiluido"   , "${it+1}")
            setValueInCollection("detalle42", it, "jClaro"     , "${it+1}")
            setValueInCollection("detalle42", it, "jFiltrado"  , "${it+1}")
            setValueInCollection("detalle42", it, "mClara"     , "${it+1}")
            setValueInCollection("detalle42", it, "mielA"      , "${it+1}")
            setValueInCollection("detalle42", it, "mielB"      , "${it+1}")
            setValueInCollection("detalle42", it, "mielF"      , "${it+1}")

            // Nota: 
            // Los totales se actualizan en cada iteración.
            // Si funciona con uno (it==0) es suficiente para este caso especial.
            // Verificando propiedades calculadas del documento (totales).
        }

        assertTotalInCollection("detalle42", 0, "j1Extracto", "3.28")
        assertTotalInCollection("detalle42", 1, "j1Extracto", "2.28")
        assertTotalInCollection("detalle42", 2, "j1Extracto", "0.56") //Puede cambiar
        assertTotalInCollection("detalle42", 3, "j1Extracto", "1.25")
        assertTotalInCollection("detalle42", 4, "j1Extracto", "0.01")

        execute("Sections.change", "activeSection=4")
        assertCollectionRowCount("detalle5", 1)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section4_detalle5")
        assertDialog()
        setValue    ( "pMtra"       , "2.25")
        setValue    ( "pCrisol"     , "3.15")
        setValue    ( "pCriCen"     , "5.4")
        assertValue ( "porcCenizas" , "100.00")
        execute("Collection.save")
        assertNoErrors()

        execute("Sections.change", "activeSection=5")
        assertCollectionRowCount("detalle6", 1)
        execute("Collection.edit" , "row=0,viewObject=xava_view_section5_detalle6")
        assertDialog()
        setValue    ( "bxOc"  , "15")
        setValue    ( "bxDig" , "35")
        assertValue ( "porc"  , "42.86")
        execute("Collection.save")
        assertNoErrors()
        
        execute("Sections.change", "activeSection=6")
        assertCollectionRowCount("detalle7", 0)
        setValue    ( "fr"  , "0.641")
        execute("Collection.new" , "viewObject=xava_view_section6_detalle7")
        assertDialog()
        setValue    ( "horaS"  , "08:00")
        assertValue ( "hora"   , "07/08/2019 08:00")
        setValue    ( "mlTitu" , "1.54")
        setValue    ( "fd"     , "2.55")
        assertValue ( "ppm"    , "367.58")
        execute("Collection.save")
        assertNoErrors()

        assertTotalInCollection("detalle7" , 0 , "mlTitu" , "1.54")
        assertTotalInCollection("detalle7" , 0 , "fd"     , "2.55")
        assertTotalInCollection("detalle7" , 0 , "ppm"    , "367.58")
        
        execute("Sections.change", "activeSection=7")
        assertCollectionRowCount("detalle8", 5)

        setValueInCollection("detalle8" , 0 , "brixRef" , "10");
        setValueInCollection("detalle8" , 1 , "brixRef" , "20");
        setValueInCollection("detalle8" , 2 , "brixRef" , "30");
        setValueInCollection("detalle8" , 3 , "brixRef" , "40");
        setValueInCollection("detalle8" , 4 , "brixRef" , "50");
        
        assertValueInCollection("detalle8" , 0 , "brixEle" , "10.00")
        assertValueInCollection("detalle8" , 1 , "brixEle" , "80.00")
        assertValueInCollection("detalle8" , 2 , "brixEle" , "120.00")
        assertValueInCollection("detalle8" , 3 , "brixEle" , "160.00")
        assertValueInCollection("detalle8" , 4 , "brixEle" , "200.00")

        // detalle8[0].brixEle == null
        assertValueInCollection("detalle8" , 0 , "porc" , "0.00")
        assertValueInCollection("detalle8" , 1 , "porc" , "87.50")
        assertValueInCollection("detalle8" , 2 , "porc" , "33.33")
        assertValueInCollection("detalle8" , 3 , "porc" , "25.00")
        assertValueInCollection("detalle8" , 4 , "porc" , "20.00")
        
        //assertTotalInCollection("detalle8" , 0 , "porc" , "95.00")

        execute ("Ingenio.save")
        // FINALIZAR
        //execute    ("CRUD.delete")
        assertNoErrors()

    }

}
