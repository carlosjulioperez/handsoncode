package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class Cto24HCargarDetallesAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("detalles_no_cargados")
            return
        }
        MapFacade.setValues("Cto24H",
            getView().getKeyValues(), getView().getValues()
        )

        Cto24H cto24H = XPersistence.getManager().find( Cto24H.class, getView().getValue("id") )
        if (cto24H.detallesCargados){
            addError("detalles_ya_cargados")
            //TODO: Validar
            hideAction = false
            return
        }
        cto24H.cargarDetalles()
        getView().refresh()
        addMessage("detalles_cargados")
        hideAction = true
    }

    String getActionToHide(){
        return hideAction ? "Cto24H.cargarDetalles": null
    }
}
