package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class TqFundidorDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        BigDecimal bri  = (BigDecimal)getView().getValue("bri")
        BigDecimal bri2 = (BigDecimal)getView().getValue("bri2")
        BigDecimal pol  = (BigDecimal)getView().getValue("pol")
        getView().setValue("sac", (bri && pol) ? Calculo.instance.getSac(bri, pol, 6, 2): null)
        
        BigDecimal sac = (BigDecimal)getView().getValue("sac")
        getView().setValue("pur", (sac && bri2) ? Calculo.instance.getPorc(sac, bri2, 2): null)
        getView().setValue("bri2", bri ? Calculo.instance.redondear(bri*6, 2): null)

    }
    
}
