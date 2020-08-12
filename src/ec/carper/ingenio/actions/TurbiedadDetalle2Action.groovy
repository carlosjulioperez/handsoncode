package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class TurbiedadDetalle2Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): false)
        
    }

}
