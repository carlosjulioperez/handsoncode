package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import java.sql.Timestamp
import org.openxava.actions.*

class CanaDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        Timestamp hora           = (Timestamp)getView().getValue("hora")

        BigDecimal wH2O          = (BigDecimal)getView().getValue("wH2O")
        BigDecimal wCana         = (BigDecimal)getView().getValue("wCana")
        BigDecimal brixExtracto  = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto   = (BigDecimal)getView().getValue("polExtracto")
        BigDecimal tamizVacioM0  = (BigDecimal)getView().getValue("tamizVacioM0")
        BigDecimal muestraSecaM2 = (BigDecimal)getView().getValue("muestraSecaM2")

        // =(G6*0,26)/(0,9971883+0,00385310413*F6+0,0000132218495*F6*F6+0,00000004655189*F6*F6*F6)
        if (brixExtracto && polExtracto) {
            getView().setValue("polReal", (
               (polExtracto*0.26)/(0.9971883 + 0.00385310413*brixExtracto + 0.0000132218495*brixExtracto*brixExtracto + 0.00000004655189*brixExtracto*brixExtracto*brixExtracto) 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }

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

        // =(N6/L6)*100
        BigDecimal porcSacarosa = (BigDecimal)getView().getValue("porcSacarosa")
        if (porcSacarosa && brix){
            getView().setValue("pureza", (
                (porcSacarosa/brix)*100 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))

            // =+L6-N6
            getView().setValue("nSac", brix - porcSacarosa)
        }
        
        // https://sourceforge.net/p/openxava/discussion/419690/thread/e3d301aa/?limit=25
        // println("values=" + getView().getRoot().getValues());
        String diaTrabajoId = getView().getRoot().getValue("diaTrabajo.id")
        // println diaTrabajoId
         
        BigDecimal nSac = (BigDecimal)getView().getValue("nSac")
        if (nSac && diaTrabajoId)
            getView().setValue("aR", new TrashCanaDetalle2().getPorcAzuRed(diaTrabajoId, hora))

        BigDecimal aR = (BigDecimal)getView().getValue("aR")
        if (nSac && aR)
            getView().setValue("porcArNsac", (aR/nSac*100).setScale(8, BigDecimal.ROUND_HALF_UP))


    }
}
