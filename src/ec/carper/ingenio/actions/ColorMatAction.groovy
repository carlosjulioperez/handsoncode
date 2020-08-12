package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class ColorMatAction extends OnChangePropertyBaseAction{

    def computo = {

        BigDecimal bri           = (BigDecimal)getView().getValue("bri${it}")
        BigDecimal absFiltrada   = (BigDecimal)getView().getValue("absFiltrada${it}")
        BigDecimal absSinFiltrar = (BigDecimal)getView().getValue("absSinFiltrar${it}")
        BigDecimal celda         = (BigDecimal)getView().getValue("celda${it}")
        
        getView().setValue("rho${it}", bri ? new BrixDensidadWp().getP(bri): null)
        
        // =+(C7*H7)/100000
        BigDecimal rho = (BigDecimal)getView().getValue("rho${it}")
        getView().setValue("cedilla${it}", (bri && rho) ? Calculo.instance.getCedilla(bri,rho,6): null)

        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla${it}")
        getView().setValue("color${it}", (absFiltrada && celda && cedilla) ? Calculo.instance.getColor(absFiltrada, celda, cedilla, 2): null)
        
        // BigDecimal color = (BigDecimal)getView().getValue("color${it}")
        // if (absSinFiltrar && celda && cedilla && color)
        //     getView().setValue("turb${it}", (((1000*absSinFiltrar)/(celda*cedilla))-color).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal color = (BigDecimal)getView().getValue("color${it}")
        getView().setValue("turb${it}", (absSinFiltrar && celda && cedilla && color) ? (Calculo.instance.getColor(absSinFiltrar, celda, cedilla, 2) - color): null)

    }

    void execute() throws Exception{
        (1..8).each{
            computo.call(it)
        }
    }
    
}
