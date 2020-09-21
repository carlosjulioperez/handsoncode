package ec.carper.ingenio.test

import org.openxava.tests.*

class FlujoJugoTest extends ModuleTestBase {

    FlujoJugoTest(String testName) {
        super(testName, "Ingenio", "FlujoJugo")
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
        //printHtml()
        execute("List.viewDetail", "row=0"); // Pulsamos en la primera fila
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 

        execute('Collection.new' , 'viewObject=xava_view_section0_detalle')
        assertDialog()
        setValue    ( "horaS"         , "07:30")
        assertValue ( "hora"          , "07/08/2019 07:30")
        setValue    ( "ini"           , "0")
        setValue    ( "fin"           , "2061")
        assertValue ( "tot"           , "2061")
        setValue    ( "horaSBrixJDil" , "06:00")
        assertValue ( "horaBrixJDil"  , "07/08/2019 06:00")
        assertValue ( "brixJDil"      , "0.00")
        assertValue ( "p"             , "998.203")
        assertValue ( "tonJugo"       , "2,057.296383")
        execute("Collection.save")

        execute('Collection.new' , 'viewObject=xava_view_section0_detalle')
        assertDialog()
        setValue    ( "horaS"         , "08:30")
        assertValue ( "hora"          , "07/08/2019 08:30")
        setValue    ( "ini"           , "2061")
        setValue    ( "fin"           , "2081")
        assertValue ( "tot"           , "20")
        setValue    ( "horaSBrixJDil" , "06:00")
        assertValue ( "horaBrixJDil"  , "07/08/2019 06:00")
        assertValue ( "brixJDil"      , "0.00")
        assertValue ( "p"             , "998.203")
        assertValue ( "tonJugo"       , "19.96406")
        execute("Collection.save")

        execute('Collection.new' , 'viewObject=xava_view_section0_detalle')
        assertDialog()
        setValue    ( "horaS"         , "09:30")
        assertValue ( "hora"          , "07/08/2019 09:30")
        setValue    ( "ini"           , "2081")
        setValue    ( "fin"           , "21")
        assertValue ( "tot"           , "-2060")
        setValue    ( "horaSBrixJDil" , "08:00")
        assertValue ( "horaBrixJDil"  , "07/08/2019 08:00")
        assertValue ( "brixJDil"      , "13.41")
        assertValue ( "p"             , "1,052.344")
        assertValue ( "tonJugo"       , "")
        execute("Collection.save")
        
        assertNoErrors()
        // **************************************************
        // FINALIZAR
        //execute    ("CRUD.delete")
        assertNoErrors()
    }

}
