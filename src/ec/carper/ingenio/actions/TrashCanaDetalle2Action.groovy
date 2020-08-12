package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

class TrashCanaDetalle2Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 

        BigDecimal mlReductores = (BigDecimal)getView().getValue("mlReductores")

        if (mlReductores ){
            BigDecimal tab7SusRed = (mlReductores/4).setScale(2, BigDecimal.ROUND_HALF_UP)
            BigDecimal porcAzuRed = new BrixDensidadTitSus().getSusRed(tab7SusRed)

            getView().setValue("calTab7SusRed", tab7SusRed)
            getView().setValue("calPorcAzuRed", porcAzuRed)
        }else{
            getView().setValue("calTab7SusRed", null)
            getView().setValue("calPorcAzuRed", null)
        }
    }

}

