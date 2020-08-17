package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class StockFabricaCargarItemsAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("items_no_cargados")
            return
        }
        MapFacade.setValues("StockFabrica",
            getView().getKeyValues(), getView().getValues()
        )

        StockFabrica stockFabrica = XPersistence.getManager().find( StockFabrica.class, getView().getValue("id") )
        if (stockFabrica.itemsCargados){
            addError("items_ya_cargados")
            return
        }
        stockFabrica.cargarItems()
        getView().refresh()
        addMessage("items_cargados")
        hideAction = true
    }

    String getActionToHide(){
        return hideAction ? "StockFabrica.cargarItems": ""
    }
}
