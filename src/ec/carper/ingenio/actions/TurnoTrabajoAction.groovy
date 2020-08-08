package ec.carper.ingenio.actions

import org.openxava.actions.*

class TurnoTrabajoAction extends OnChangePropertyBaseAction{
   
    void execute() throws Exception{
        def horaDesde = (String)getView().getValue("horaDesde")
        //println ">>> ${horaDesde}"

        if (horaDesde){
            def horaHasta = ""
            use (groovy.time.TimeCategory) {
                def horaInicio = Date.parse("HH:mm", horaDesde)
                def horaFin = horaInicio-1.minutes
                horaHasta = horaFin.format("HH:mm")
            }
            if (horaHasta)
                getView().setValue("horaHasta", horaHasta) 
        }
    }
    
}
