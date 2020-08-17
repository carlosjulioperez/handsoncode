package ec.carper.ingenio.actions

import org.openxava.actions.*
import org.openxava.view.View 

class StockFabricaEditDetailAction extends EditElementInCollectionAction{
    
    void execute() throws Exception{
        super.execute()

        // https://sourceforge.net/p/openxava/discussion/419690/thread/f89a2800/
        // https://sourceforge.net/p/openxava/discussion/437013/thread/94bc9208/
        // src/org/openxava/test/actions/EditInvoiceDetailAction.java
        
        View view = getCollectionElementView()
		//println("\n>>> View values:\n" + view.getValues())

        Boolean modificable = (Boolean)view.getValue("modificable")
        view.setEditable("valor", modificable)

    }
}
