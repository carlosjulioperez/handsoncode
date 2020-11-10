package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

class FormularioCrearItemsPorHoraAction extends ViewBaseAction implements IHideActionAction{

    private boolean hideAction = false
    def modulo = ""

    void execute() throws Exception{
        
        modulo  = getModelName()
        def map = getView().getKeyValues()
        // Si existe Modulo.crearItemsPorHora(), ejecutarlo
        def instance = new groovy.lang.GroovyClassLoader().loadClass( 
                "ec.carper.ingenio.model.${modulo}", true, false )?.newInstance()

        instance = XPersistence.getManager().find( instance.class, getView().getValue("id") )
        if (instance.itemsPorHoraCreados){
            addError("items_por_hora_ya_creados")
            return
        }

        instance.metaClass.methods.each { method ->
            if (method.name == 'crearItemsPorHora'){
                method.invoke(instance)
                
                getView().refresh()
                addMessage("items_por_hora_creados")
                hideAction = true
            }
        } 
    }

    String getActionToHide(){
        return hideAction ? "${modulo}.crearItemsPorHora": ""
    }

}
