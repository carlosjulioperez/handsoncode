package ec.carper.ingenio.actions

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import ec.carper.ingenio.model.*
import static org.openxava.jpa.XPersistence.*

class TrashCanaGrabarPromediosAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{
        // def id = getView().getValue("id")
        //
        // if (id == null){
        //     addError("formulario_sin_registro_seleccionado")
        //     return
        // }
        //
        //
        // MapFacade.setValues("TrashCana",
        //     getView().getKeyValues(), getView().getValues()
        // )
        //
        // TrashCana trashCana = XPersistence.getManager().find( TrashCana.class, getView().getValue("id") )
        // trashCana.crearTrash()
        // getView().refresh()
        // addMessage("formulario_trash_creado")
        // hideAction = true
        // resetDescriptionsCache()
        //     getView().clear()
        //     nextAction = "CRUD.save"
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

