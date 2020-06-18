package ec.carper.ingenio.actions

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import ec.carper.ingenio.model.*
import static org.openxava.jpa.XPersistence.*

class DiaTrabajoEditableAction extends ViewBaseAction implements IChainAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{
        // containsMetaProperty, containsMetaReference
        if (!getView().getMetaModel().containsMetaReference("diaTrabajo")) {
            nextAction = "CRUD.save"
            return
        }else{

            // Validar CRUD.save
            def modulo = getModelName()
            def map = getView().getKeyValues()
            // println (">>>>>>>>>>>>>>>>>>>>>>> " + modulo)
            // println ("*********************** " + map)

            if (map){ //actualizar objeto
                //Query query = getManager().createQuery("select diaTrabajo.cerrado from ${modulo} o where id= :id ")
                boolean cerrado = (boolean) getManager()
                    .createQuery("""
                        SELECT d.cerrado 
                        FROM ${modulo} o, DiaTrabajo d
                        WHERE o.id = :id AND o.diaTrabajo.id = d.id
                    """)
                    .setParameter("id", map.id)
                    .getSingleResult()

                if ( cerrado ){
                    addMessage ("dia_trabajo_cerrado_administrador")

                    resetDescriptionsCache()
                    getView().clear()
                    getView().setEditable(false); // Dejamos la vista como no editable
                }else{
                    // Si existe Modulo.actualizar(), ejecutarlo
                    def instance = new groovy.lang.GroovyClassLoader().loadClass( 
                        "ec.carper.ingenio.model.${modulo}", true, false )?.newInstance()

                    instance = XPersistence.getManager().find( instance.class, getView().getValue("id") )

                    instance.metaClass.methods.each { method ->
                        if (method.name == 'actualizar'){
                            method.invoke(instance)
                            getView().refresh()
                            addMessage("registro_actualizado")
                            println ">>> Ejecutando ${modulo}.actualizar()... "
                        }
                    } 
                    nextAction = "CRUD.save"
                }
            }else
                nextAction = "CRUD.save" //nuevo objeto
        }
    }
    
    // Obligatorio por causa de 'IChainAction'
    String getNextAction() throws Exception {
        return nextAction // Si es nulo no se encadena con ninguna acción
    }
}

