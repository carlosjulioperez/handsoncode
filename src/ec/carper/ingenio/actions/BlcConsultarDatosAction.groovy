package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import ec.carper.ingenio.model.*

class BlcConsultarDatosAction extends ViewBaseAction {

    void execute() throws Exception{
        def id = getView().getValue("id")

        if (id == null){
            addError("datos_no_consultados")
            return
        }
        MapFacade.setValues("Blc",
            getView().getKeyValues(), getView().getValues()
        )

        Blc blc = XPersistence.getManager().find( Blc.class, getView().getValue("id") )
        blc.consultarDatos()
        getView().refresh()
        addMessage("datos_consultados")
    }

}


