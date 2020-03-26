package ec.carper.ingenio.actions

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import ec.carper.ingenio.model.*
import static org.openxava.jpa.XPersistence.*

class EditableAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{
        //TODO Ejemplo para ejecutar queries desde Groovy
        def modulo = getModelName()
        println (">>>>>>>>>>>>>>>>>>>>>>> "+modulo)
        Query query = getManager().createQuery("select count(*) from ${modulo}")
        def numero = (Integer)query.getSingleResult()
        //System.out.println(numero)

        if ( numero >0 ){
            addMessage ("msgSoloUnRegistro")
            
            resetDescriptionsCache()
            getView().clear()
        }else 
            nextAction = "CRUD.new"
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

