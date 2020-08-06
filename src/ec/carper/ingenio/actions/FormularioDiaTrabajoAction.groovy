package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*;

class FormularioDiaTrabajoAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{
        
        def modulo       = getModelName()
        def map          = getView().getKeyValues()
        def diaTrabajoId = (String)getView().getValue("diaTrabajo.id")
        
        // Determina si el id de la tx vigente es nulo (nuevo registro) para validar que no exista un 
        // registro previamente asignado con el mismo diaTrabajoId
        if (!map.id){
            //println ">>>map.id: ${map.id}"
            //println ">>>diaTrabajoId: ${diaTrabajoId}"
            Query query = getManager().createQuery("select count(*) from ${modulo} where diaTrabajo.id= :dtId")
            query.setParameter("dtId", diaTrabajoId)
            def numero = (Integer)query.getSingleResult()

            if ( numero >0 ){
                addMessage ("grabar_solo_un_registro_por_dia_trabajo")
                resetDescriptionsCache()
                getView().clear()
            }
        }
    }
}
