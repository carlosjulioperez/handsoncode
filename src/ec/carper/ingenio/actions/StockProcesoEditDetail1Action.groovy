package ec.carper.ingenio.actions

import org.openxava.actions.*
import org.openxava.view.View 

class StockProcesoEditDetail1Action extends EditElementInCollectionAction{
    
    void execute() throws Exception{
        super.execute()

        View view = getCollectionElementView()
		// println("\n>>> View values:\n" + view.getValues())

        Boolean modificable = (Boolean)view.getValue("modificable")
        view.setEditable("tonBrix", modificable)
        view.setEditable("tonSac",  modificable)
        view.setEditable("pureza",  modificable)

    }
}
