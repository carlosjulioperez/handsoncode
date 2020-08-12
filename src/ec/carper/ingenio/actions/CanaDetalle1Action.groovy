package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class CanaDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        String diaTrabajoId = getView().getRoot().getValue("diaTrabajo.id")

        def hora = (Timestamp)getView().getValue("hora")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)

        BigDecimal wH2O          = (BigDecimal)getView().getValue("wH2O")
        BigDecimal wCana         = (BigDecimal)getView().getValue("wCana")
        BigDecimal brixExtracto  = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto   = (BigDecimal)getView().getValue("polExtracto")
        BigDecimal tamizVacioM0  = (BigDecimal)getView().getValue("tamizVacioM0")
        BigDecimal muestraSecaM2 = (BigDecimal)getView().getValue("muestraSecaM2")
        
        //println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        //println("values=" + getView().getValues());
        //*println hora

        getView().setValue("polReal", (brixExtracto && polExtracto) ? Calculo.instance.getSac(brixExtracto, polExtracto, 1, 2): null)
        getView().setValue("muestraHumM1", tamizVacioM0 ? tamizVacioM0+50: null)
       
        // =100*((I6-J6)/(I6-H6))
        // i = Muestra Hum M1
        // j = Muestra Seca M2
        // h = Tamiz Vacio M0
        BigDecimal muestraHumM1 = (BigDecimal)getView().getValue("muestraHumM1")
        getView().setValue("porcHumedad", (tamizVacioM0 && muestraHumM1 && muestraSecaM2) ? Calculo.instance.redondear( 100*( (muestraHumM1-muestraSecaM2) / (muestraHumM1-tamizVacioM0) ) , 2): null)

        // =F6*(C6-0,25*D6+0,0125*D6*K6)/D6/(1-0,0125*F6)
        BigDecimal porcHumedad = (BigDecimal)getView().getValue("porcHumedad")
        getView().setValue("brix", (brixExtracto && wH2O && wCana && porcHumedad) ? Calculo.instance.getBrix(brixExtracto, wH2O, wCana, porcHumedad, 2): null)
        
        // =100-K6-L6
        BigDecimal brix = (BigDecimal)getView().getValue("brix")
        getView().setValue("porcFibra", (porcHumedad && brix) ? Calculo.instance.getPorcFibra(porcHumedad, brix): null)

        // =E6*(D6+C6-0,0125*M6*D6)/D6
        BigDecimal polReal = (BigDecimal)getView().getValue("polReal")
        BigDecimal porcFibra = (BigDecimal)getView().getValue("porcFibra")
        getView().setValue("porcSacarosa", (polReal && wH2O && wCana && porcFibra) ? Calculo.instance.getPorcSacarosa(polReal, wCana, wH2O, porcFibra, 2): null)

        BigDecimal porcSacarosa = (BigDecimal)getView().getValue("porcSacarosa")
        getView().setValue("pureza", (porcSacarosa && brix) ? Calculo.instance.getPorc(porcSacarosa, brix, 2): null)

        // =+L6-N6
        getView().setValue("nSac", (porcSacarosa && brix) ? brix - porcSacarosa: null)
        
        // https://sourceforge.net/p/openxava/discussion/419690/thread/e3d301aa/?limit=25
        // println("values=" + getView().getRoot().getValues());
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // println diaTrabajoId
         
        BigDecimal nSac = (BigDecimal)getView().getValue("nSac")
        getView().setValue("aR", (diaTrabajoId && hora) ? new TrashCanaDetalle2().getPorcAzuRed(diaTrabajoId, hora): null)

        BigDecimal aR = (BigDecimal)getView().getValue("aR")
        getView().setValue("porcArNsac", (nSac && aR) ? Calculo.instance.redondear(aR/nSac*100,2): null)

    }
}
