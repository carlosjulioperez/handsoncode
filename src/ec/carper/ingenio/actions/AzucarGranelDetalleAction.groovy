package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class AzucarGranelDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (horaS && diaTrabajoId) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)
        
        BigDecimal briCorr       = (BigDecimal)getView().getValue("briCorr")
        BigDecimal bri           = (BigDecimal)getView().getValue("bri")
        BigDecimal absFiltrada   = (BigDecimal)getView().getValue("absFiltrada")
        BigDecimal absSinFiltrar = (BigDecimal)getView().getValue("absSinFiltrar")
        BigDecimal celda         = (BigDecimal)getView().getValue("celda")
        
        getView().setValue("rho", bri ? new BrixDensidadWp().getP(bri): null)
        getView().setValue("briCorr", bri ? Calculo.instance.redondear(bri*0.989,2): null)
        
        // =+(C7*H7)/100000
        BigDecimal rho = (BigDecimal)getView().getValue("rho")
        getView().setValue("cedilla", (briCorr && rho) ? Calculo.instance.getCedilla(briCorr, rho, 6): null)
        getView().setValue("briEle", briCorr ? Calculo.instance.redondear(briCorr*2, 2): null)

        // =+(1000*D7)/(F7*G7)
        BigDecimal cedilla = (BigDecimal)getView().getValue("cedilla")
        getView().setValue("color", (absFiltrada && celda && cedilla) ? Calculo.instance.getColor(absFiltrada, celda, cedilla, 2): null)
        
        // =+((1000*E7)/(F7*G7))-I7
        BigDecimal color = (BigDecimal)getView().getValue("color")
        getView().setValue("turb", (absSinFiltrar && celda && cedilla && color) ? (Calculo.instance.getColor(absSinFiltrar, celda, cedilla, 2) - color): null)
        
    }
    
}
