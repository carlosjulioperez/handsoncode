package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class BlcCargarItemsAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("items_no_cargados")
            return
        }
        MapFacade.setValues("Blc",
            getView().getKeyValues(), getView().getValues()
        )

        Blc blc = XPersistence.getManager().find( Blc.class, getView().getValue("id") )
        if (blc.itemsCargados){
            addError("items_ya_cargados")
            return
        }
        blc.cargarItems()
        getView().refresh()
        addMessage("items_cargados")
        hideAction = true
    }

    String getActionToHide(){
        return hideAction ? "Blc.cargarItems": null
    }
}


