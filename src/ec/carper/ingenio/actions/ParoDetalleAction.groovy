package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class ParoDetalleAction extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaI = (String)getView().getValue("horaI")
        getView().setValue("fechaInicio", (diaTrabajoId && horaI) ? SqlUtil.instance.obtenerFecha(horaI, diaTrabajoId): null)
        
        String horaF = (String)getView().getValue("horaF")
        getView().setValue("fechaFin", (diaTrabajoId && horaF) ? SqlUtil.instance.obtenerFecha(horaF, diaTrabajoId): null)
        
        Timestamp fechaInicio = (Timestamp)getView().getValue("fechaInicio")
        Timestamp fechaFin    = (Timestamp)getView().getValue("fechaFin")

        if (fechaInicio && fechaFin) {
            long startTime = fechaInicio.getTime()
            long endTime = fechaFin.getTime()
            //log.warn ("Start: ${startTime}, end: ${endTime} ")
            
            getView().setValue("totalParo", Util.instance.getDurationAsString(startTime, endTime)) 
        }else
            getView().setValue("totalParo", null) 

    }
}
