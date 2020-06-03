package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class AzucarGranelDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

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
        if (briCorri){
            if (rho)
                getView().setValue("cedilla", (briCorr*rho/100000).setScale(6, BigDecimal.ROUND_HALF_UP))

            getView().setValue("briEle", (briCorr*2).setScale(6, BigDecimal.ROUND_HALF_UP))
        }

        // =+(1000*D7)/(F7*G7)
        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla")
        if (absFiltrada && celda && cedilla)
            getView().setValue("color", ((1000*absFiltrada)/(celda*cedilla)).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        // =+((1000*E7)/(F7*G7))-I7
        BigDecimal color = (BigDecimal)getView().getValue("color")
        if (absSinFiltrar && celda && cedilla && color)
            getView().setValue("turb", (((1000*absSinFiltrar)/(celda*cedilla))-color).setScale(2, BigDecimal.ROUND_HALF_UP))
        
    }
    
}
