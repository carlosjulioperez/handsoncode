package ec.carper.ingenio.test
 
import ec.carper.ingenio.util.SqlUtil

import org.openxava.tests.*

class Cto24HTest extends ModuleTestBase {

    Cto24HTest(String testName) {
        super(testName, "Ingenio", "Cto24H")
    }

    public void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id" , "ff808081711cd37c01711cd403a70000")
        setValue("fFelining" , "0.997")
        
        setValue    ( "atC1"   , "24.2")
        setValue    ( "atJ11"  , "24.2")
        setValue    ( "atJd1"  , "38.5")
        setValue    ( "atJc1"  , "24.2")
        setValue    ( "atJf1"  , "38.5")
        setValue    ( "atMc1"  , "18.8")
        setValue    ( "atMa1"  , "18.8")
        setValue    ( "atMb1"  , "18.8")
        setValue    ( "atMf1"  , "18.8")
        assertValue ( "atC2"   , "31.86")
        assertValue ( "atJ12"  , "10.62")
        assertValue ( "atJd2"  , "6.68")
        assertValue ( "atJc2"  , "10.62")
        assertValue ( "atJf2"  , "6.68")
        assertValue ( "atMc2"  , "54.71")
        assertValue ( "atMa2"  , "54.71")
        assertValue ( "atMb2"  , "54.71")
        assertValue ( "atMf2"  , "54.71")

        execute("Sections.change", "activeSection=1"); // Cambia a la pestaña 1
        setValue    ( "siC1"   , "219.951")
        setValue    ( "siC2"   , "28.8738")
        setValue    ( "siC3"   , "4.0005")
        setValue    ( "siC4"   , "8.0001")
        setValue    ( "siC5"   , "2.3331")
        setValue    ( "siC6"   , "150")
        setValue    ( "siC7"   , "219.9")
        setValue    ( "siC8"   , "43.8")
        assertValue ( "siM1"   , "263.1585")
        assertValue ( "siM2"   , "413.1585")
        assertValue ( "siM3"   , "263.70")
        assertValue ( "siPorc" , "0.36")

        execute("Sections.change", "activeSection=2");
        setValue    ( "arC1"  , "11.11")
        setValue    ( "arJ11" , "22.2")
        setValue    ( "arJd1" , "28.7")
        setValue    ( "arJc1" , "33.3")
        setValue    ( "arJf1" , "20")
        setValue    ( "arMc1" , "10.8")
        setValue    ( "arMa1" , "11")
        setValue    ( "arMb1" , "12")
        setValue    ( "arMf1" , "13")
        assertValue ( "arC2"  , "2.78")
        assertValue ( "arC3"  , "1.73")
        assertValue ( "arJ12" , "11.10")
        assertValue ( "arJ13" , "0.42")
        assertValue ( "arJd2" , "14.35")
        assertValue ( "arJd3" , "0.33")
        assertValue ( "arJc2" , "16.65")
        assertValue ( "arJc3" , "0.28")
        assertValue ( "arJf2" , "10.00")
        assertValue ( "arJf3" , "0.47")
        assertValue ( "arMc2" , "0.43")
        assertValue ( "arMc3" , "1.72")
        assertValue ( "arMa2" , "0.42")
        assertValue ( "arMa3" , "1.68")
        assertValue ( "arMb2" , "0.39")
        assertValue ( "arMb3" , "7.80")
        assertValue ( "arMf2" , "0.36")
        assertValue ( "arMf3" , "9.00")
        
        execute("Sections.change", "activeSection=3");
        setValue    ( "ccJ11" , "1")
        setValue    ( "ccJ12" , "2")
        setValue    ( "ccJ13" , "3")
        setValue    ( "ccJ15" , "4")
        assertValue ( "ccJ14" , "19.28")
        assertValue ( "ccJ16" , "3.28407225")
        assertValue ( "ccJ17" , "2.28407225")
        assertValue ( "ccJ18" , "0.771")
        assertValue ( "ccJ19" , "1.250")
        assertValue ( "ccJ10" , "0.01")
        
        // setValue    ( "" , "")
        // assertValue ( "" , "")
        
        assertNoErrors()
    }

}
