package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class TurbiedadDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null)
        
        BigDecimal abs900Nm = (BigDecimal)getView().getValue("abs900Nm")
        //println("values=" + getView().getValues());

        getView().setValue("turJClaro", abs900Nm ? Calculo.instance.redondear(abs900Nm*100, 2): null)
        
    }

}
