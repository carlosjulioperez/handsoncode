package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*

import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*

class StockProcesoCargarItemsAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("items_no_cargados")
            return
        }
        MapFacade.setValues("StockProceso",
            getView().getKeyValues(), getView().getValues()
        )

        StockProceso stockProceso = XPersistence.getManager().find( StockProceso.class, getView().getValue("id") )
        if (stockProceso.itemsCargados){
            addError("items_ya_cargados")
            return
        }
        stockProceso.cargarItems()
        getView().refresh()
        addMessage("items_cargados")
        hideAction = true
    }

    String getActionToHide(){
        return hideAction ? "StockProceso.cargarItems": ""
    }
}
