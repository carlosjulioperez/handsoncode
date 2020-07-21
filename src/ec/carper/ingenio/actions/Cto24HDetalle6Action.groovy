package ec.carper.ingenio.actions

import ec.carper.ingenio.util.Calculo

import org.openxava.actions.*

class Cto24HDetalle6Action extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def bxOc  = (BigDecimal)getView().getValue("bxOc")
        def bxDig = (BigDecimal)getView().getValue("bxDig")

        getView().setValue("porc", (bxOc && bxDig) ? Calculo.instance.getPorc(bxOc, bxDig, 2) : 0 )

    }
}
