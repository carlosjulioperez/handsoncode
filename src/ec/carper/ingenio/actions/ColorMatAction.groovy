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
        
        if (bri)
            getView().setValue("rho${it}", new BrixDensidadWp().getP(bri))
        
        // =+(C7*H7)/100000
        BigDecimal rho = (BigDecimal)getView().getValue("rho${it}")
        if (bri && rho)
            getView().setValue("cedilla${it}", Calculo.instance.getCedilla(bri,rho,6))

        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla${it}")
        if (absFiltrada && celda && cedilla)
            getView().setValue("color${it}", Calculo.instance.getColor(absFiltrada, celda, cedilla, 2))
        
        // BigDecimal color = (BigDecimal)getView().getValue("color${it}")
        // if (absSinFiltrar && celda && cedilla && color)
        //     getView().setValue("turb${it}", (((1000*absSinFiltrar)/(celda*cedilla))-color).setScale(2, BigDecimal.ROUND_HALF_UP))

        BigDecimal color = (BigDecimal)getView().getValue("color${it}")
        if (absSinFiltrar && celda && cedilla && color)
            getView().setValue("turb${it}", Calculo.instance.getColor(absSinFiltrar, celda, cedilla, 2) - color)

    }

    void execute() throws Exception{
        (1..8).each{
            computo.call(it)
        }
    }
    
}
