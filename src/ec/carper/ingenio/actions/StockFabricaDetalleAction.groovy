package ec.carper.ingenio.actions

import ec.carper.ingenio.util.Calculo

import org.openxava.actions.*

class StockFabricaDetalleAction extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{
        
        // https://sourceforge.net/p/openxava/discussion/419690/thread/f89a2800/
        // View view = getCollectionElementView()
        // view.setEditable("rate", fase); // false instead of true

        def valor   = (BigDecimal)getView().getValue("valor")
        getView().setEditable("valor", false); // false instead of true
        
        // getView().setValue("porcCenizas", (pMtra && pCrisol && pCriCen) ? 
        //     Calculo.instance.redondear( ((pCriCen - pCrisol) / pMtra)*100, 2) : 0 )

    }
}
