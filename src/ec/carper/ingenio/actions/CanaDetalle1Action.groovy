package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class CanaDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        Timestamp hora           = (Timestamp)getView().getValue("hora")
        BigDecimal wH2O          = (BigDecimal)getView().getValue("wH2O")
        BigDecimal wCana         = (BigDecimal)getView().getValue("wCana")
        BigDecimal brixExtracto  = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto   = (BigDecimal)getView().getValue("polExtracto")
        BigDecimal tamizVacioM0  = (BigDecimal)getView().getValue("tamizVacioM0")
        BigDecimal muestraSecaM2 = (BigDecimal)getView().getValue("muestraSecaM2")
        
        //println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        //println("values=" + getView().getValues());
        //*println hora

        if (brixExtracto && polExtracto)
            getView().setValue("polReal", Calculo.instance.getSac(brixExtracto, polExtracto, 1, 2))

        if (tamizVacioM0)
            getView().setValue("muestraHumM1", tamizVacioM0+50)
       
        // =100*((I6-J6)/(I6-H6))
        // i = Muestra Hum M1
        // j = Muestra Seca M2
        // h = Tamiz Vacio M0
        BigDecimal muestraHumM1  = (BigDecimal)getView().getValue("muestraHumM1")
        if (tamizVacioM0 && muestraHumM1 && muestraSecaM2){
            getView().setValue("porcHumedad", (
                100*( (muestraHumM1-muestraSecaM2) / (muestraHumM1-tamizVacioM0) )
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }

        // =F6*(C6-0,25*D6+0,0125*D6*K6)/D6/(1-0,0125*F6)
        BigDecimal porcHumedad   = (BigDecimal)getView().getValue("porcHumedad")
        if (brixExtracto && wH2O && wCana && porcHumedad) {
            getView().setValue("brix", (
                brixExtracto*(wH2O-0.25*wCana+0.0125*wCana*porcHumedad)/wCana/(1-0.0125*brixExtracto)
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }
        
        // =100-K6-L6
        BigDecimal brix = (BigDecimal)getView().getValue("brix")
        if (porcHumedad && brix)
            getView().setValue("porcFibra", 100 - porcHumedad - brix)


        // =E6*(D6+C6-0,0125*M6*D6)/D6
        BigDecimal polReal = (BigDecimal)getView().getValue("polReal")
        BigDecimal porcFibra = (BigDecimal)getView().getValue("porcFibra")
        if (polReal && wH2O && wCana && porcFibra) {
            getView().setValue("porcSacarosa", (
                polReal*(wCana + wH2O - 0.0125*porcFibra*wCana)/wCana 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }

        BigDecimal porcSacarosa = (BigDecimal)getView().getValue("porcSacarosa")
        if (porcSacarosa && brix){
            getView().setValue("pureza", Calculo.instance.getPur(porcSacarosa, brix, 2))

            // =+L6-N6
            getView().setValue("nSac", brix - porcSacarosa)
        }
        
        // https://sourceforge.net/p/openxava/discussion/419690/thread/e3d301aa/?limit=25
        // println("values=" + getView().getRoot().getValues());
        String diaTrabajoId = getView().getRoot().getValue("diaTrabajo.id")
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // println diaTrabajoId
         
        BigDecimal nSac = (BigDecimal)getView().getValue("nSac")
        if (nSac && diaTrabajoId && hora)
            getView().setValue("aR", new TrashCanaDetalle2().getPorcAzuRed(diaTrabajoId, hora))

        BigDecimal aR = (BigDecimal)getView().getValue("aR")
        if (nSac && aR)
            getView().setValue("porcArNsac", (aR/nSac*100).setScale(8, BigDecimal.ROUND_HALF_UP))

    }
}
