package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class ParoDetalleAction extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaI = (String)getView().getValue("horaI")
        if (horaI)
            getView().setValue("fechaInicio", SqlUtil.instance.obtenerFecha(horaI, diaTrabajoId)) 
        
        String horaF = (String)getView().getValue("horaF")
        if (horaF)
            getView().setValue("fechaFin", SqlUtil.instance.obtenerFecha(horaF, diaTrabajoId)) 
        
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
