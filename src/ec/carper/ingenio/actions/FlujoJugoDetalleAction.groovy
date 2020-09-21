package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class FlujoJugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        String horaSBrixJDil = (String)getView().getValue("horaSBrixJDil")
        getView().setValue("horaBrixJDil", (diaTrabajoId && horaSBrixJDil) ? SqlUtil.instance.obtenerFecha(horaSBrixJDil, diaTrabajoId): null ) 
        
        def ini          = getView().getValue("ini")
        def fin          = getView().getValue("fin")
        def horaBrixJDil = getView().getValue("horaBrixJDil")

        getView().setValue("tot", (ini>=0 && fin>=0) ? fin-ini: null)
        getView().setValue("brixJDil", horaBrixJDil ? SqlUtil.instance.getValorDetalleCampoXHora(diaTrabajoId, horaBrixJDil, "jugo", "JugoDetalle", "jdBri") : null)
        
        def brixJDil = getView().getValue("brixJDil")
        getView().setValue("p", brixJDil>=0 ? new BrixDensidadWp().getP(brixJDil): null)

        def tot = getView().getValue("tot")
        def p   = getView().getValue("p")
        def vTot = (tot != null) ? Math.abs(tot): 0
        getView().setValue("tonJugo", (p>=0) ? Calculo.instance.redondear(tot*p/1000,6) : null)
    }

}
