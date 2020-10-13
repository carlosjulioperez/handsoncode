package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class BlcCenicanaConsultarDatosAction extends ViewBaseAction {

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("datos_no_consultados")
            return
        }
        MapFacade.setValues("BlcCenicana",
            getView().getKeyValues(), getView().getValues()
        )

        BlcCenicana blcCenicana = XPersistence.getManager().find( BlcCenicana.class, getView().getValue("id") )
        blcCenicana.consultarDatos()
        getView().refresh()
        addMessage("datos_consultados")
    }

}


