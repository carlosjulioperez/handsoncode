package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import org.apache.commons.math3.stat.regression.SimpleRegression 
import java.sql.Timestamp
import org.openxava.actions.*

class TamanoGranoDetalleAction extends OnChangePropertyBaseAction{

    def computo = {

        BigDecimal pesoTamiz        = (BigDecimal)getView().getValue("pesoTamiz${it}")
        BigDecimal pesoTamizMuestra = (BigDecimal)getView().getValue("pesoTamizMuestra${it}")

        getView().setValue("porcRetenido${it}", (pesoTamiz && pesoTamizMuestra) ? (pesoTamizMuestra-pesoTamiz): null)
        
        BigDecimal porcAcumulado = (BigDecimal)getView().getValue("porcAcumulado${it}")
        BigDecimal porcRetenido = (BigDecimal)getView().getValue("porcRetenido${it}")

        if (it==1){
            if (porcRetenido>=0)
                getView().setValue("porcAcumulado${it}", porcRetenido)
        }else{
            BigDecimal porcA = (BigDecimal)getView().getValue("porcAcumulado${it-1}")
            
            if (porcA>=0 && porcRetenido>=0)
                getView().setValue("porcAcumulado${it}", (porcA+porcRetenido) )
        }

        if (it != 5){
            getView().setValue("tamiz${it}", new Parametro().obtenerValor("TAMIZ${it}_NUMERO"))
            getView().setValue("tamizAbertura${it}", new BigDecimal(new Parametro().obtenerValor("TAMIZ${it}_ABERTURA")))
        }else
            getView().setValue("tamiz${it}", "BASE")

    }

    void execute() throws Exception{
        (1..5).each{
            computo.call(it)
        }

        // Calculo de abertura media y % coeficiente de variacion
        BigDecimal t1 = (BigDecimal)getView().getValue("tamizAbertura1")
        BigDecimal t2 = (BigDecimal)getView().getValue("tamizAbertura2")
        BigDecimal t3 = (BigDecimal)getView().getValue("tamizAbertura3")
        BigDecimal t4 = (BigDecimal)getView().getValue("tamizAbertura4")
        
        BigDecimal p1 = (BigDecimal)getView().getValue("porcAcumulado1")
        BigDecimal p2 = (BigDecimal)getView().getValue("porcAcumulado2")
        BigDecimal p3 = (BigDecimal)getView().getValue("porcAcumulado3")
        BigDecimal p4 = (BigDecimal)getView().getValue("porcAcumulado4")

        if (t1 && t2 && t3 && t4 && p1 && p2 && p3 && p4){
        
            def regression = new SimpleRegression();

            regression.addData(t1 , p1)
            regression.addData(t2 , p2)
            regression.addData(t3 , p3)
            regression.addData(t4 , p4)

            double slope      = regression.getSlope();
            double intercept  = regression.getIntercept();

            def aberMed       = (50-intercept)/slope
            def coefVar       = ( ( (16-intercept)/slope - (84-intercept)/slope ) / (2*aberMed))*100

            getView().setValue("aberturaMedia", aberMed ? Calculo.instance.redondear(aberMed,2): null)
            getView().setValue("coeficienteVariacion", coefVar ? Calculo.instance.redondear(coefVar,2): null)

        }
    }
    
}
