package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class MasasDetalle3Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal bri  = (BigDecimal)getView().getValue("bri")
        BigDecimal bri2 = (BigDecimal)getView().getValue("bri2")
        BigDecimal pol  = (BigDecimal)getView().getValue("pol")
        
        // =((D6*0,26)/(0,9971883+0,00385310413*C6+0,0000132218495*C6*C6+0,00000004655189*C6*C6*C6))*6
        if (bri && pol)
            getView().setValue("sac", (Calculo.instance.getSac(bri,pol)*6).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        // =(E6/G6)*100
        BigDecimal sac  = (BigDecimal)getView().getValue("sac")
        if (sac && bri2)
            getView().setValue("pur", Calculo.instance.getPur(sac, bri2).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        // =C6*6
        if (bri)
            getView().setValue("bri2", (bri*6).setScale(2, BigDecimal.ROUND_HALF_UP))

    }
    
}
