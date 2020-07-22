package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class Cto24HDetalle8Action extends OnChangePropertyBaseAction{
   
    void execute() throws Exception{
        def filaNo  = (BigDecimal)getView().getValue("filaNo")
        def brixRef = (BigDecimal)getView().getValue("brixRef")

        if (filaNo == 1)
            getView().setValue("brixEle", brixRef ?: 0 )
        else{
            getView().setValue("brixEle", brixRef ? Calculo.instance.redondear(brixRef*4,i 2): 0 )
            //TODO: Continuar
            getView().setValue("porc", brixRef ? brixRef : 0 )
        }
        

    }
    
}
