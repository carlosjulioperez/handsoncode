package ec.carper.ingenio.test

import ec.carper.ingenio.util.Calculo

import org.apache.commons.math3.stat.regression.SimpleRegression 
import org.openxava.tests.*

class TamanoGranoTest extends ModuleTestBase {

    TamanoGranoTest(String testName) {
        super(testName, "Ingenio", "TamanoGrano")
    }
 
    void testCalculos() throws Exception {
        def regression = new SimpleRegression();
        //    regression.addData(0.0, 1.0);
        //    regression.addData(1.0, 2.5);
        //    regression.addData(2.0, 3.0);

        regression.addData(1    , 20.10)
        regression.addData(0.71 , 59.50)
        regression.addData(0.5  , 91.40)
        regression.addData(0.25 , 100)

        double slope = regression.getSlope();
        double intercept = regression.getIntercept();
        long n = regression.getN();
        double err = regression.getMeanSquareError();
            
        def aberturaMedia = (50-intercept)/slope
        println Calculo.instance.redondear(aberturaMedia,2)

        def coefVar = ( ( (16-intercept)/slope - (84-intercept)/slope ) / (2*aberturaMedia))*100
        println Calculo.instance.redondear(coefVar,2)

    }

    void testCrear() throws Exception {
        login("admin", "admin")
        
        execute("CRUD.new")

        setValue("diaTrabajo.id", Aux.instance.diaTrabajoId)
        
        assertCollectionRowCount("detalle", 0) // La colección esta vacía 
        execute("Collection.new" , "viewObject=xava_view_detalle")
        assertDialog()
        
        setValue    ( "pesoTamiz1"           , "405.80")
        setValue    ( "pesoTamizMuestra1"    , "425.90")
        setValue    ( "pesoTamiz2"           , "405.50")
        setValue    ( "pesoTamizMuestra2"    , "444.90")
        setValue    ( "pesoTamiz3"           , "375.20")
        setValue    ( "pesoTamizMuestra3"    , "407.10")
        setValue    ( "pesoTamiz4"           , "350.30")
        setValue    ( "pesoTamizMuestra4"    , "358.90")
        setValue    ( "pesoTamiz5"           , "359.30")
        setValue    ( "pesoTamizMuestra5"    , "359.30")

        assertValue ( "porcRetenido1"        , "20.10")
        assertValue ( "porcAcumulado1"       , "20.10")
        assertValue ( "porcRetenido2"        , "39.40")
        assertValue ( "porcAcumulado2"       , "59.50")
        assertValue ( "porcRetenido3"        , "31.90")
        assertValue ( "porcAcumulado3"       , "91.40")
        assertValue ( "porcRetenido4"        , "8.60")
        assertValue ( "porcAcumulado4"       , "100.00")
        assertValue ( "porcRetenido5"        , "0.00")
        assertValue ( "porcAcumulado5"       , "100.00")

        assertValue ( "aberturaMedia"        , "0.78")
        assertValue ( "coeficienteVariacion" , "39.61")

        execute("Collection.save")
        assertNoErrors()
        assertCollectionRowCount("detalle", 1)

        execute("CRUD.delete")
        assertNoErrors()
    }

}
