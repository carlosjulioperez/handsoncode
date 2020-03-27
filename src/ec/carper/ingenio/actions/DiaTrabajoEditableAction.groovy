package ec.carper.ingenio.actions

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import ec.carper.ingenio.model.*
import static org.openxava.jpa.XPersistence.*

class DiaTrabajoEditableAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{

        //def cerrado = getView().getMetaModel().containsMetaReference("diaTrabajo")

        // containsMetaProperty, containsMetaReference
        if (!getView().getMetaModel().containsMetaReference("diaTrabajo")) {
            nextAction = "CRUD.save" // 'CRUD.delete' se ejecutará cuando esta
            return // acción finalice
        }

        // println ("****************************************************************************************************")
        // println (cerrado)

        // Validar CRUD.save
        def modulo = getModelName()
        println (">>>>>>>>>>>>>>>>>>>>>>> "+modulo)

        println ("*********************** "+getView().getKeyValues())
        Query query = getManager().createQuery("select count(*) from ${modulo}")
        def numero = (Integer)query.getSingleResult()
        //System.out.println(numero)

        // if ( numero >0 ){
        //     addMessage ("msgDiaTrabajoCerrado")
        //     
        //     resetDescriptionsCache()
        //     getView().clear()
        // }
        nextAction = "CRUD.save"
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

