package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*

class TrashCanaDetalle1Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

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
