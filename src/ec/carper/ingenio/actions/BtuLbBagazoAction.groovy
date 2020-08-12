package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class BtuLbBagazoAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        String diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")

        BigDecimal pCrisol  = (BigDecimal)getView().getValue("pCrisol")
        BigDecimal pCriCen  = (BigDecimal)getView().getValue("pCriCen")
        BigDecimal pMtra    = (BigDecimal)getView().getValue("pMtra")
        
        getView().setValue("porcHumedad", diaTrabajoId ? SqlUtil.instance.getValorCampo(diaTrabajoId, "Bagazo", "porcHumedad"): null)
        getView().setValue("porcCenBs", (pCrisol && pCriCen && pMtra) ? Calculo.instance.redondear((pCriCen-pCrisol)*100/pMtra, 3): null)
        
        BigDecimal porcHumedad = (BigDecimal)getView().getValue("porcHumedad")
        getView().setValue("porcCenBh", (pCrisol && pCriCen && pMtra && porcHumedad) ? Calculo.instance.redondear((pCriCen-pCrisol)*(100-porcHumedad)/pMtra, 2): null)

        BigDecimal porcCenBh = (BigDecimal)getView().getValue("porcCenBh")
        getView().setValue("pcBtuLb", (porcHumedad && porcCenBh) ? Calculo.instance.redondear(((19599.7-(184.14*(porcCenBh*(1-(porcHumedad/100))))-(217.77*(porcHumedad))))*0.4299, 2): null )

    }

}
