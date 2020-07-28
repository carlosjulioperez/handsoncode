package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class ParoDetalleAction extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        String horaI = (String)getView().getValue("horaI")
        if (horaI)
            getView().setValue("fechaInicio", Util.instance.toTimestamp(horaI, diaTrabajo.fecha)) 
        
        String horaF = (String)getView().getValue("horaF")
        if (horaF)
            getView().setValue("fechaFin", Util.instance.toTimestamp(horaF, diaTrabajo.fecha)) 
        
        Timestamp fechaInicio = (Timestamp)getView().getValue("fechaInicio")
        Timestamp fechaFin    = (Timestamp)getView().getValue("fechaFin")

        if (fechaInicio && fechaFin) {
            long startTime = fechaInicio.getTime()
            long endTime = fechaFin.getTime()
            //log.warn ("Start: ${startTime}, end: ${endTime} ")
            
            getView().setValue("totalParo", Util.instance.getDurationAsString(startTime, endTime)) 
        }

    }
}
