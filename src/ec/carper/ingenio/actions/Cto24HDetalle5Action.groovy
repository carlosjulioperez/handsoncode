package ec.carper.ingenio.actions

import ec.carper.ingenio.util.Calculo

import org.openxava.actions.*

class Cto24HDetalle5Action extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def pMtra   = (BigDecimal)getView().getValue("pMtra")
        def pCrisol = (BigDecimal)getView().getValue("pCrisol")
        def pCriCen = (BigDecimal)getView().getValue("pCriCen")

        //TODO validar división por cero
        
        getView().setValue("porcCenizas", (pMtra && pCrisol && pCriCen) ? 
            Calculo.instance.redondear( ((pCriCen - pCrisol) / pMtra)*100, 2) : 0 )

    }
}
