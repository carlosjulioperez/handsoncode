package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class TrashCanaCrearTrashAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false

    void execute() throws Exception{
        def id = getView().getValue("id")
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // println id

        if (id == null){
            addError("formulario_trash_no_creado")
            return
        }
        MapFacade.setValues("TrashCana",
            getView().getKeyValues(), getView().getValues()
        )

        TrashCana trashCana = XPersistence.getManager().find( TrashCana.class, getView().getValue("id") )
        trashCana.crearTrash()
        getView().refresh()
        addMessage("formulario_trash_creado")
        hideAction = true
    }

    String getActionToHide(){
        return hideAction ? "TrashCana.crearTrash": null
    }
}

