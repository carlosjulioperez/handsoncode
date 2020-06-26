package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class BagazoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", Util.instance.toTimestamp(horaS, diaTrabajo.fecha)) 
        
        String horaSPorcSacJR = (String)getView().getValue("horaSPorcSacJR")
        if (horaSPorcSacJR)
            getView().setValue("horaPorcSacJR", Util.instance.toTimestamp(horaSPorcSacJR, diaTrabajo.fecha)) 
        def horaPorcSacJR  = (Timestamp)getView().getValue("horaPorcSacJR")

        BigDecimal wH2O          = (BigDecimal)getView().getValue("wH2O")
        BigDecimal wBagazo       = (BigDecimal)getView().getValue("wBagazo")
        BigDecimal brixExtracto  = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto   = (BigDecimal)getView().getValue("polExtracto")
        BigDecimal tamizVacioM0  = (BigDecimal)getView().getValue("tamizVacioM0")
        BigDecimal muestraSecaM2 = (BigDecimal)getView().getValue("muestraSecaM2")

        if (brixExtracto && polExtracto)
            getView().setValue("polReal", Calculo.instance.getSac(brixExtracto,polExtracto,1,2))

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
        if (brixExtracto && wH2O && wBagazo && porcHumedad) 
            getView().setValue("brix", Calculo.instance.getBrix(brixExtracto, wH2O, wBagazo, porcHumedad, 2))
        
        // =100-K6-L6
        BigDecimal brix = (BigDecimal)getView().getValue("brix")
        if (porcHumedad && brix)
            getView().setValue("porcFibra", Calculo.instance.getPorcFibra(porcHumedad, brix))

        // =E6*(D6+C6-0,0125*M6*D6)/D6
        BigDecimal polReal = (BigDecimal)getView().getValue("polReal")
        BigDecimal porcFibra = (BigDecimal)getView().getValue("porcFibra")
        if (polReal && wH2O && wBagazo && porcFibra) 
            getView().setValue("porcSacarosa", Calculo.instance.getPorcSacarosa(polReal, wBagazo, wH2O, porcFibra, 2)) 

        if (diaTrabajoId && horaPorcSacJR){
            getView().setValue("porcSacJR", (
                new JugoDetalle().getPorcSacJR(diaTrabajoId, horaPorcSacJR) 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }

    }
}
