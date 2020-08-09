package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class TurbiedadDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId)) 
        
        BigDecimal abs900Nm = (BigDecimal)getView().getValue("abs900Nm")
        println("values=" + getView().getValues());

        if (abs900Nm)
            getView().setValue("turJClaro", (abs900Nm*100).setScale(2, BigDecimal.ROUND_HALF_UP))
        
    }

}
