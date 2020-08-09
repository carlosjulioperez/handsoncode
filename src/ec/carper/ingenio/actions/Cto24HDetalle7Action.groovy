package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class Cto24HDetalle7Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId)) 

        BigDecimal fr     = (BigDecimal)getView().getRoot().getValue("fr")
        
        BigDecimal mlTitu = (BigDecimal)getView().getValue("mlTitu")
        BigDecimal fd     = (BigDecimal)getView().getValue("fd")

        // =+(Q27*0,1*60000)/($T$37*100)*R27
        if (fr && mlTitu && fd)
            getView().setValue("ppm", Calculo.instance.redondear((mlTitu*0.1*60000)/(fr*100)*fd, 2) )

    }
}
