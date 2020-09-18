package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class FlujoJugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        def ini = getView().getValue("ini")
        def fin = getView().getValue("fin")

        def brixJDil = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdBri")

        getView().setValue("tot", (ini && fin) ? fin-ini: null)
        getView().setValue("brixJDil", (ini && fin) ? fin-ini: null)

        //getView().setValue("tot", (ini && fin) ? new TurbiedadDetalle1().getValorTurJClaro(diaTrabajoId, horaTJClaro): null)

    }

}
