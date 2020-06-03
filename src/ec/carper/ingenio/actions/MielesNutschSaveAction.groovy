package ec.carper.ingenio.actions

import org.openxava.actions.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.model.*

class MielesNutschSaveAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{

        def id = getView().getValue("id")

        if (id == null){
            addError("registro_no_actualizado")
            return
        }
        MapFacade.setValues("MielesNutsch",
            getView().getKeyValues(), getView().getValues()
        )

        MielesNutsch mielesNutsch = XPersistence.getManager().find( MielesNutsch.class, getView().getValue("id") )
        mielesNutsch.save()
        getView().refresh()
        addMessage("promedios_actualizados")

        nextAction = "CRUD.save"
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

