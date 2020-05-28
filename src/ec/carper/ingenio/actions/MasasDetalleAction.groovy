package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class MasasDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal bri  = (BigDecimal)getView().getValue("bri")
        BigDecimal bri2 = (BigDecimal)getView().getValue("bri2")
        BigDecimal pol  = (BigDecimal)getView().getValue("pol")
        
        if (bri && pol)
            getView().setValue("sac", Calculo.instance.getSac(bri, pol, 6, 2))
        
        BigDecimal sac  = (BigDecimal)getView().getValue("sac")
        if (sac && bri2)
            getView().setValue("pur", Calculo.instance.getPorc(sac, bri2, 2))
        
        if (bri)
            getView().setValue("bri2", (bri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

    }
    
}
