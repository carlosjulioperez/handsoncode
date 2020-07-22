package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class Cto24HDetalle8Action extends OnChangePropertyBaseAction{
   
    void execute() throws Exception{

        def detalle8 = (Collection<Cto24HDetalle8>)getView().getRoot().getValue("detalle8")

        def filaNo  = (Integer)getView().getValue("filaNo")
        def brixRef = (BigDecimal)getView().getValue("brixRef")

        if (filaNo == 1)
            getView().setValue("brixEle", brixRef ?: 0 )
        else{
            if (brixRef){
                def beAct = Calculo.instance.redondear(brixRef*4, 2)
                getView().setValue("brixEle", beAct )

                if ( beAct ){
                    def beAnt = detalle8[filaNo-2] ? (detalle8[filaNo-2].brixEle ?:0) : 0 
                    getView().setValue("porc", Calculo.instance.porcCon(beAnt, beAct) )
                    // println detalle8
                    // println "beAct: ${beAct}, beAnt: ${beAnt}" 
                    // println("\n>>> View values:\n" + getView().getValues());
                }
            }
        }
    }
    
    
}
