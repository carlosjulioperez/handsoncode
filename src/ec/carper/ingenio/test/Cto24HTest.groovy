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
        
        setValue    ( "ccJd1" , "1")
        setValue    ( "ccJd2" , "2")
        setValue    ( "ccJd3" , "3")
        setValue    ( "ccJd5" , "4")
        assertValue ( "ccJd4" , "14.23")
        assertValue ( "ccJd6" , "3.28407225")
        assertValue ( "ccJd7" , "2.28407225")
        assertValue ( "ccJd8" , "0.569")
        assertValue ( "ccJd9" , "1.250")
        assertValue ( "ccJd0" , "0.01")
        
        setValue    ( "ccJc1" , "2")
        setValue    ( "ccJc2" , "3")
        setValue    ( "ccJc3" , "4")
        setValue    ( "ccJc5" , "5")
        assertValue ( "ccJc4" , "15.33")
        assertValue ( "ccJc6" , "4.74683544")
        assertValue ( "ccJc7" , "2.74683544")
        assertValue ( "ccJc8" , "0.767")
        assertValue ( "ccJc9" , "1.000")
        assertValue ( "ccJc0" , "0.01")
        
        setValue    ( "ccJf1" , "1.05")
        setValue    ( "ccJf2" , "2.05")
        setValue    ( "ccJf3" , "3.05")
        setValue    ( "ccJf5" , "0.05")
        assertValue ( "ccJf4" , "13.45")
        assertValue ( "ccJf6" , "3.35982955")
        assertValue ( "ccJf7" , "2.30982955")
        assertValue ( "ccJf8" , "0.007")
        assertValue ( "ccJf9" , "100.000")
        assertValue ( "ccJf0" , "0.54")
        
        setValue    ( "ccMc1" , "2.05")
        setValue    ( "ccMc2" , "3.05")
        setValue    ( "ccMc3" , "4.05")
        setValue    ( "ccMc5" , "0.01")
        assertValue ( "ccMc4" , "62.66")
        assertValue ( "ccMc6" , "4.81718392")
        assertValue ( "ccMc7" , "2.76718392")
        assertValue ( "ccMc8" , "0.006")
        assertValue ( "ccMc9" , "500.000")
        assertValue ( "ccMc0" , "3.90")
        
        setValue    ( "ccMa1" , "1.10")
        setValue    ( "ccMa2" , "2.10")
        setValue    ( "ccMa3" , "3.10")
        setValue    ( "ccMa5" , "0.02")
        assertValue ( "ccMa4" , "77.27")
        assertValue ( "ccMa6" , "3.43530182")
        assertValue ( "ccMa7" , "2.33530182")
        assertValue ( "ccMa8" , "0.015")
        assertValue ( "ccMa9" , "250.000")
        assertValue ( "ccMa0" , "1.39")
        
        setValue    ( "ccMb1" , "3.05")
        setValue    ( "ccMb2" , "3.05")
        setValue    ( "ccMb3" , "3.05")
        setValue    ( "ccMb5" , "0.03")
        assertValue ( "ccMb4" , "85.16")
        assertValue ( "ccMb6" , "4.99877079")
        assertValue ( "ccMb7" , "1.94877079")
        assertValue ( "ccMb8" , "0.026")
        assertValue ( "ccMb9" , "166.667")
        assertValue ( "ccMb0" , "1.35")
        
        setValue    ( "ccMf1" , "1.05")
        setValue    ( "ccMf2" , "1.05")
        setValue    ( "ccMf3" , "2.05")
        setValue    ( "ccMf5" , "0.04")
        assertValue ( "ccMf4" , "84.63")
        assertValue ( "ccMf6" , "1.78829941")
        assertValue ( "ccMf7" , "0.73829941")
        assertValue ( "ccMf8" , "0.034")
        assertValue ( "ccMf9" , "125.000")
        assertValue ( "ccMf0" , "0.36")
        
        execute("Sections.change", "activeSection=4");
        setValue    ( "csPMtra"   , "0.54")
        setValue    ( "csPCrisol" , "0.23")
        setValue    ( "csPCriCen" , "0.4")
        assertValue ( "csPorcCen" , "31.48")
        
        execute("Sections.change", "activeSection=5");
        setValue    ( "ipBXOc"  , "15.55")
        setValue    ( "ipBXDig" , "16.66")
        assertValue ( "ipPorc"  , "93.34")

        /*
        execute("Sections.change", "activeSection=6");
        setValue    ( "fr"     , "0.641")

        assertCollectionRowCount("detalle", 0)
        execute("Collection.new"   , "viewObject=xava_view_detalle")
        assertDialog()

        setValue    ( "mlTitu" , "1")
        setValue    ( "fd"     , "3")
        assertValue ( "ppm"    , "280.81")
        */
    
        execute("Sections.change", "activeSection=7");
        setValue    ( "ceBr1"  , "1.1")
        setValue    ( "ceBr2"  , "2.2")
        setValue    ( "ceBr3"  , "3.3")
        setValue    ( "ceBr4"  , "4.4")
        setValue    ( "ceBr5"  , "5.5")
        assertValue ( "ceBe1" , "1.10")
        assertValue ( "ceBe2" , "8.80")
        assertValue ( "ceBe3" , "13.20")
        assertValue ( "ceBe4" , "17.60")
        assertValue ( "ceBe5" , "22.00")
        assertValue ( "cePc2" , "87.50")
        assertValue ( "cePc3" , "33.33")
        assertValue ( "cePc4" , "25.00")
        assertValue ( "cePc5" , "20.00")
        assertValue ( "cePc6" , "95.00")

        // setValue    ( "" , "")
        // assertValue ( "" , "")
        
        assertNoErrors()
    }

}