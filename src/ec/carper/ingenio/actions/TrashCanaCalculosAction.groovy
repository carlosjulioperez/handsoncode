package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*

class TrashCanaCalculosAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        BigDecimal mlReductores = (BigDecimal)getNewValue()

        if (mlReductores ){
            BigDecimal tab7SusRed = (mlReductores/4).setScale(2, BigDecimal.ROUND_HALF_UP)
            BigDecimal porcAzuRed = new BrixDensidadTitSus().getSusRed(tab7SusRed)
            
            getView().setValue("calTab7SusRed", tab7SusRed )
            getView().setValue("calPorcAzuRed", porcAzuRed)
        }
    }

}

