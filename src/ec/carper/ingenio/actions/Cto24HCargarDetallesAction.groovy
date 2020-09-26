package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*

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
        
        if ( SqlUtil.instance.isCerrado("Cto24H", id) ){
            addMessage ("dia_trabajo_cerrado_administrador")
            resetDescriptionsCache()
            getView().clear()
            getView().setEditable(false); // Dejamos la vista como no editable
            hideAction = false
            return
        }

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
