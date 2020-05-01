package ec.carper.ingenio.actions

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import ec.carper.ingenio.model.*
import static org.openxava.jpa.XPersistence.*

class TrashDetalleGrabarPromediosAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{

        // Validar CRUD.save
        def modulo = getModelName()
        def map = getView().getKeyValues()
        // println (">>>>>>>>>>>>>>>>>>>>>>> " + modulo)
        // println ("*********************** " + map)

        Query query = getManager().createQuery("select diaTrabajo.cerrado from ${modulo} o where id= :id ")
        query.setParameter("id", map.id) 
        def cerrado = (boolean) query.getSingleResult()

        nextAction = "CRUD.save"
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

