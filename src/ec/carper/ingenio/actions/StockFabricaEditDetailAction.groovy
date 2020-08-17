package ec.carper.ingenio.actions

import org.openxava.actions.*

class StockFabricaEditDetailAction extends EditElementInCollectionAction{
    
    void execute() throws Exception{
        super.execute()

        // https://sourceforge.net/p/openxava/discussion/419690/thread/f89a2800/
        // src/org/openxava/test/actions/EditInvoiceDetailAction.java
        
        println ">>> valor a false"
        getCollectionElementView().setEditable("valor", false) 
    }
}
