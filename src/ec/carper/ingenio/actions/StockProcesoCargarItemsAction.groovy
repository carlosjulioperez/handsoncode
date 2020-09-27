package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

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

        if ( SqlUtil.instance.isCerrado("StockProceso", id) ){
            addMessage ("dia_trabajo_cerrado_administrador")
            resetDescriptionsCache()
            getView().clear()
            getView().setEditable(false); // Dejamos la vista como no editable
            hideAction = false
            return
        }

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
