package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo
import ec.carper.ingenio.util.SqlUtil

import java.sql.Timestamp
import org.openxava.actions.*

class BtuLbBagazoAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        String diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")

        BigDecimal pHum     = (BigDecimal)getView().getValue("pHum")
        BigDecimal pCrisol  = (BigDecimal)getView().getValue("pCrisol")
        BigDecimal pCriCen  = (BigDecimal)getView().getValue("pCriCen")
        BigDecimal pMtra    = (BigDecimal)getView().getValue("pMtra")
        
        if (pHum)
            getView().setValue("porcHumedad", SqlUtil.instance.getPromedio(diaTrabajoId, "Bagazo", "porcHumedad"))

        if (pCrisol && pCriCen && pMtra)
            getView().setValue("porcCenBs", ((pCriCen-pCrisol)*100/pMtra).setScale(3, BigDecimal.ROUND_HALF_UP))
        
        BigDecimal porcHumedad = (BigDecimal)getView().getValue("porcHumedad")
        if (pCrisol && pCriCen && pMtra && porcHumedad)
            getView().setValue("porcCenBh", ((pCriCen-pCrisol)*(100-porcHumedad)/pMtra).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal porcCenBh = (BigDecimal)getView().getValue("porcCenBh")
        if (porcHumedad && porcCenBh)
            getView().setValue("pcBtuLb",(((19599.7-(184.14*(porcCenBh*(1-(porcHumedad/100))))-(217.77*(porcHumedad))))*0.4299)
            .setScale(2, BigDecimal.ROUND_HALF_UP))

    }

}
