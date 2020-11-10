package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

// https://sourceforge.net/p/openxava/discussion/437013/thread/39699fc4/?limit=25#b43b/c2a7
class IngenioSaveAction extends ViewBaseAction implements IChainAction, IChangeModeAction{

    private String nextAction = null // Para guardar la siguiente acción a ejecutar

    void execute() throws Exception{
        def modulo = getModelName()
        def map = getView().getKeyValues()
        // println (">>>>>>>>>>>>>>>>>>>>>>> " + modulo)
        // println ("*********************** " + map)

        // containsMetaProperty, containsMetaReference
        if (!getView().getMetaModel().containsMetaReference("diaTrabajo")) {
            nextAction = "CRUD.save"
            return
        }else{
            if (map){ //actualizar objeto
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

                    // Estos métodos se ejecutan antes de save()
                    instance.metaClass.methods.each { method ->
                        if (method.name == 'actualizar'){
                            method.invoke(instance)
                            // No se deben limpiar los campos antes de grabar
                            //getView().refresh()
                            addMessage("registro_actualizado")
                            // println "**************************************************"
                            // println ">>> Ejecutando ${modulo}.actualizar()... "
                            // println "**************************************************"
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

    String getNextMode() {
        return IChangeModeAction.LIST;
    }
}

