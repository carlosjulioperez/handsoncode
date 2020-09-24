package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class StockProcesoDetalle2Action extends OnChangePropertyBaseAction{

    def diaTrabajoId = ""

    // No obtengo los valores, solo disparo la acción para realizar los cálculos
    void execute() throws Exception{

        diaTrabajoId     = (String)getView().getRoot().getValue("diaTrabajo.id")
        def materialId   = (String)getView().getValue("material.id")
        def material     = SqlUtil.instance.getMaterial(materialId)
        
        if (material.campo){
            // Obtener el material y dependiendo del campo hacer las validaciones
            
            def volumen1 = getView().getValue("volumen1")
            def eq       = getView().getValue("eq")
          
            def (porcBrix, porcSac, pureza) = [0, 0, 0]
            if (material.campo=="jDiluidoBr"){
                porcBrix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdBri")
                porcSac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdSac")
            }else{
                porcBrix = 0
                porcSac  = 0
            }

            // Calculos
            def factor   = new FactorVolumen().getValor(0, eq+1)
            def volumen2 = factor ? Calculo.instance.redondear(volumen1/factor, 3): 0
            def densidad = new BrixDensidadWp().getP(porcBrix)
            def peso     = Calculo.instance.redondear(densidad*volumen2/1000, 3)
            def tonBrix  = Calculo.instance.redondear(peso*porcBrix/100, 3)
            def tonSac   = Calculo.instance.redondear(peso*porcSac/100, 3)

            if (material.campo=="jDiluidoBr")
                pureza   = porcBrix ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0

            getView().setValue("volumen1" , volumen1?:null)
            getView().setValue("volumen2" , volumen2?:null)
            getView().setValue("peso"     , peso?:null)
            getView().setValue("porcBrix" , porcBrix?:null)
            getView().setValue("tonBrix"  , tonBrix?:null)
            getView().setValue("porcSac"  , porcSac?:null)
            getView().setValue("tonSac"   , tonSac?:null)
            getView().setValue("pureza"   , pureza?:null)
            getView().setValue("densidad" , densidad?:null)
            getView().setValue("factor"   , factor?:null)
        
        }
    }
}
