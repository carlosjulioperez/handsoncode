package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class StockFabricaCalcularAction extends ViewBaseAction {

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("datos_no_consultados")
            return
        }
        MapFacade.setValues("StockFabrica",
            getView().getKeyValues(), getView().getValues()
        )

        StockFabrica stockFabrica = XPersistence.getManager().find( StockFabrica.class, getView().getValue("id") )
        stockFabrica.calcular()
        getView().refresh()
        addMessage("datos_consultados")
    }

}
