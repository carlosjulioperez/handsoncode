package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class ColorMatDetalleAction extends OnChangePropertyBaseAction{

    def computo = {

        BigDecimal bri           = (BigDecimal)getView().getValue("bri${it}")
        BigDecimal absFiltrada   = (BigDecimal)getView().getValue("absFiltrada${it}")
        BigDecimal absSinFiltrar = (BigDecimal)getView().getValue("absSinFiltrar${it}")
        BigDecimal celda         = (BigDecimal)getView().getValue("celda${it}")
        
        if (bri)
            getView().setValue("rho${it}", new BrixDensidadWp().getP(bri))
        
        // =+(C7*H7)/100000
        BigDecimal rho = (BigDecimal)getView().getValue("rho${it}")
        if (bri && rho)
            getView().setValue("cedilla${it}", (bri*rho/100000).setScale(6, BigDecimal.ROUND_HALF_UP))

        // =+(1000*D7)/(F7*G7)
        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla${it}")
        if (absFiltrada && celda && cedilla)
            getView().setValue("color${it}", ((1000*absFiltrada)/(celda*cedilla)).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        // =+((1000*E7)/(F7*G7))-I7
        BigDecimal color = (BigDecimal)getView().getValue("color${it}")
        if (absSinFiltrar && celda && cedilla && color)
            getView().setValue("turb${it}", (((1000*absSinFiltrar)/(celda*cedilla))-color).setScale(2, BigDecimal.ROUND_HALF_UP))
        
    }

    void execute() throws Exception{

        (1..8).each{
            computo.call(it)
        }
        
    }
    
}
