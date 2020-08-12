package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class BagazoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)
        
        String horaSPorcSacJR = (String)getView().getValue("horaSPorcSacJR")
        //println ">>> horaSPorcSacJR: ${horaSPorcSacJR}"
        getView().setValue("horaPorcSacJR", (diaTrabajoId && horaSPorcSacJR) ? SqlUtil.instance.obtenerFecha(horaSPorcSacJR, diaTrabajoId): null ) 
        
        def horaPorcSacJR  = (Timestamp)getView().getValue("horaPorcSacJR")

        BigDecimal wH2O          = (BigDecimal)getView().getValue("wH2O")
        BigDecimal wBagazo       = (BigDecimal)getView().getValue("wBagazo")
        BigDecimal brixExtracto  = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto   = (BigDecimal)getView().getValue("polExtracto")
        BigDecimal tamizVacioM0  = (BigDecimal)getView().getValue("tamizVacioM0")
        BigDecimal muestraSecaM2 = (BigDecimal)getView().getValue("muestraSecaM2")

        getView().setValue("polReal", (brixExtracto && polExtracto) ? Calculo.instance.getSac(brixExtracto,polExtracto,1,2): null)
        getView().setValue("muestraHumM1", tamizVacioM0 ? tamizVacioM0+50: null)
       
        // =100*((I6-J6)/(I6-H6))
        // i = Muestra Hum M1
        // j = Muestra Seca M2
        // h = Tamiz Vacio M0
        BigDecimal muestraHumM1 = (BigDecimal)getView().getValue("muestraHumM1")
        getView().setValue("porcHumedad", (tamizVacioM0 && muestraHumM1 && muestraSecaM2) ? Calculo.instance.redondear( 100*( (muestraHumM1-muestraSecaM2) / (muestraHumM1-tamizVacioM0) ), 2): null )

        // =F6*(C6-0,25*D6+0,0125*D6*K6)/D6/(1-0,0125*F6)
        BigDecimal porcHumedad = (BigDecimal)getView().getValue("porcHumedad")
        getView().setValue("brix", (brixExtracto && wH2O && wBagazo && porcHumedad) ? Calculo.instance.getBrix(brixExtracto, wH2O, wBagazo, porcHumedad, 2): null)
        
        // =100-K6-L6
        BigDecimal brix = (BigDecimal)getView().getValue("brix")
        getView().setValue("porcFibra", (porcHumedad && brix) ? Calculo.instance.getPorcFibra(porcHumedad, brix): null)

        // =E6*(D6+C6-0,0125*M6*D6)/D6
        BigDecimal polReal = (BigDecimal)getView().getValue("polReal")
        BigDecimal porcFibra = (BigDecimal)getView().getValue("porcFibra")
        getView().setValue("porcSacarosa", (polReal && wH2O && wBagazo && porcFibra) ? Calculo.instance.getPorcSacarosa(polReal, wBagazo, wH2O, porcFibra, 2): null)

        getView().setValue("porcSacJR", (diaTrabajoId && horaPorcSacJR) ? Calculo.instance.redondear(new JugoDetalle().getPorcSacJR(diaTrabajoId, horaPorcSacJR), 2): null)
    }
}
