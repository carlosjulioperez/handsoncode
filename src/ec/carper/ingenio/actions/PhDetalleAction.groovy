package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class PhDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId)) 
        
        String horaSTJClaro = (String)getView().getValue("horaSTJClaro")
        if (horaSTJClaro)
            getView().setValue("horaTJClaro", SqlUtil.instance.obtenerFecha(horaSTJClaro, diaTrabajoId))
        
        def horaTJClaro = (Timestamp)getView().getValue("horaTJClaro")

        if (diaTrabajoId && horaTJClaro ){
            getView().setValue("tJClaro", (
                new TurbiedadDetalle1().getValorTurJClaro(diaTrabajoId, horaTJClaro) 
            ).setScale(2, BigDecimal.ROUND_HALF_UP))
        }
    }

}
