package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

class TrashCanaDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        // TODO: Obtener objeto DiaTrabajo
        // String diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        // DiaTrabajo diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(getView().getRoot().getValue("diaTrabajo.id"))
        String horaS = (String)getView().getValue("horaS")
        if (horaS)
            getView().setValue("hora", Util.instance.toTimestamp(horaS, diaTrabajo.fecha)) 

        BigDecimal cantidadCana = (BigDecimal)getView().getValue("cantidadCana") //getNewValue()
        BigDecimal netaCana     = (BigDecimal)getView().getValue("netaCana") //getNewValue()
        if (cantidadCana && netaCana){
           BigDecimal trashCana = cantidadCana - netaCana
           getView().setValue("calTrashCana", trashCana)
            
           BigDecimal porcTrash = ((trashCana / cantidadCana)*100).setScale(3, BigDecimal.ROUND_HALF_UP)
           getView().setValue("calPorcTrash", porcTrash)
        }
    }
}
