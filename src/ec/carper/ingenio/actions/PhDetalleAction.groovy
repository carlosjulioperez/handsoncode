package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class PhDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        String horaSTJClaro = (String)getView().getValue("horaSTJClaro")
            getView().setValue("horaTJClaro", (diaTrabajoId && horaSTJClaro) ? SqlUtil.instance.obtenerFecha(horaSTJClaro, diaTrabajoId): null)
        
        def horaTJClaro = (Timestamp)getView().getValue("horaTJClaro")

        //getView().setValue("tJClaro", (diaTrabajoId && horaTJClaro ) ? new TurbiedadDetalle1().getValorTurJClaro(diaTrabajoId, horaTJClaro): null)
        getView().setValue("tJClaro", (diaTrabajoId && horaTJClaro ) ? SqlUtil.instance.getValorDetalleCampoXHora(diaTrabajoId, horaTJClaro, "turbiedad", "TurbiedadDetalle1", "turJClaro") : null)
    }

}
