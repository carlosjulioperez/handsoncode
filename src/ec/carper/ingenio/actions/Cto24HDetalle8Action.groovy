package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class Cto24HDetalle8Action extends OnChangePropertyBaseAction{
   
    void execute() throws Exception{

        // Búsqueda: MapFacade.getValues

        // https://sourceforge.net/p/openxava/discussion/419690/thread/1566c3d7/?limit=25#4916
        // https://sourceforge.net/p/openxava/discussion/437013/thread/dece564b/
        // https://sourceforge.net/p/openxava/discussion/419690/thread/cff1188a/?limit=25#3bef/dea8/5630

        def filaNo         = (Integer)getView().getValue("filaNo")
        BigDecimal brixRef = (BigDecimal)getView().getValue("brixRef")
        def d8             = getView().getRoot().getValue("detalle8")
        //println ">>> d8: ${d8}"

        if (brixRef && d8){

            if (filaNo == 1){
                getView().setValue("brixEle", brixRef)
                getView().setValue("porc", new BigDecimal(0))
            }else{
                def beAct = Calculo.instance.redondear(brixRef*4, 2)
                getView().setValue("brixEle", beAct )

                // println ">>> d8 : ${d8}"
                if ( beAct ){
                    def beAnt = d8[filaNo-2] ? (d8[filaNo-2].brixEle ?:0) : 0 
                    getView().setValue("porc", Calculo.instance.porcCon(beAnt, beAct) )
                }
            }
        }
    }
}
