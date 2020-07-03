package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class AzucarGranelDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", Util.instance.toTimestamp(horaS, diaTrabajo.fecha)) 
        
        BigDecimal briCorr       = (BigDecimal)getView().getValue("briCorr")
        BigDecimal bri           = (BigDecimal)getView().getValue("bri")
        BigDecimal absFiltrada   = (BigDecimal)getView().getValue("absFiltrada")
        BigDecimal absSinFiltrar = (BigDecimal)getView().getValue("absSinFiltrar")
        BigDecimal celda         = (BigDecimal)getView().getValue("celda")
        
        if (bri){
            getView().setValue("rho", new BrixDensidadWp().getP(bri))
            getView().setValue("briCorr", (bri*0.989).setScale(2, BigDecimal.ROUND_HALF_UP))
        }
        
        // =+(C7*H7)/100000
        BigDecimal rho = (BigDecimal)getView().getValue("rho")
        if (briCorr){
            if (rho)
                getView().setValue("cedilla", Calculo.instance.getCedilla(briCorr, rho, 6))

            getView().setValue("briEle", (briCorr*2).setScale(2, BigDecimal.ROUND_HALF_UP))
        }

        // =+(1000*D7)/(F7*G7)
        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla")
        if (absFiltrada && celda && cedilla)
            getView().setValue("color", Calculo.instance.getColor(absFiltrada, celda, cedilla, 2))
        
        // =+((1000*E7)/(F7*G7))-I7
        BigDecimal color = (BigDecimal)getView().getValue("color")
        if (absSinFiltrar && celda && cedilla && color)
            getView().setValue("turb", Calculo.instance.getColor(absSinFiltrar, celda, cedilla, 2) - color)
        
    }
    
}
