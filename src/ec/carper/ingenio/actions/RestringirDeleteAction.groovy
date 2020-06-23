package ec.carper.ingenio.actions

import org.openxava.actions.*
 
class RestringirDeleteAction extends ViewBaseAction {
 
    void execute() throws Exception {
        addMessage("accion_restringida")
        getView().clear()
    }
}
