package ec.carper.ingenio.actions

import ec.carper.ingenio.util.Calculo

import org.openxava.actions.*

class Cto24HDetalle2Action extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{

        def bv600 = (BigDecimal)getView().getValue("bv600")
        def bv50  = (BigDecimal)getView().getValue("bv50")
        def sc4   = (BigDecimal)getView().getValue("sc4")
        def sc8   = (BigDecimal)getView().getValue("sc8")
        def pf    = (BigDecimal)getView().getValue("pf")
        def pj    = (BigDecimal)getView().getValue("pj")
        def bs600 = (BigDecimal)getView().getValue("bs600")
        def bs50  = (BigDecimal)getView().getValue("bs50")

        getView().setValue( "masa1", (bv600 && bv50 && sc4 && sc8 && pf) ? (bv600 + bv50 + sc4 + sc8 + pf): 0 )

        def masa1 = (BigDecimal)getView().getValue("masa1")
        getView().setValue( "masa2", (masa1 && pj) ? (masa1 + pj): 0 )
        
        getView().setValue( "masa3", (bs600 && bs50) ? (bs600 + bs50) : 0 )
        
        def masa2 = (BigDecimal)getView().getValue("masa2")
        def masa3 = (BigDecimal)getView().getValue("masa3")
        getView().setValue( "porcInso", (masa1 && masa2 && masa3) ? Calculo.instance.redondear((((masa3-masa1)/(masa2-masa1))*100), 2): 0 )
    }
    
}
