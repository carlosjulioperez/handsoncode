package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

class BlcDetalle1Action extends OnChangePropertyBaseAction{

    def diaTrabajoId = ""

    void execute() throws Exception{

        diaTrabajoId   = (String)getView().getRoot().getValue("diaTrabajo.id")
        def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)

        def materialId = (String)getView().getValue("material.id")
        def material   = SqlUtil.instance.getMaterial(materialId)
        
        if (material.campo){
            def valor  = (BigDecimal)getView().getValue("valor")
            def diaFin = diaTrabajo.numeroDia - 1

            def (cdV, cdA, amV, amA, jdV, jdC, jdA, bcV, bcC, bcA, bdV, bdC, cV, cC, cA)     = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
            def (mfV, mfC, mfA, abV, abC, abA, cnV, cnA, jnV, jnC, jnA, mV , mC, mA, hV, hA) = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
            def (sdV, sdC, sdA) = [0,0,0]

            // Calculos generales 
            def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "aguaM")
            if (d) amV = d.peso

            d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "jDiluidoBr")
            if (d) {
                jdV = d.volumen2
                jdC = d.peso
            }
            
            
            
            
            
            
            
            
            switch (material.campo){
            case "canaDia":
                cdV = valor
                cdA = SqlUtil.instance.getValMatBlcAcu(materialId, diaFin)
                
                getView().setValue("acumulado",  cdA ? Calculo.instance.redondear(bri*2, 2): null)
                setValores(material.campo, cdV, 0, cdV + cdA)

                //getView().setValue("briEle",  bri ? Calculo.instance.redondear(bri*2, 2): null)
                break

            // case "aguaM":
            //     def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", material.campo)
            //     if (d) {
            //         def amA = SqlUtil.instance.getValMatBlcAcu(materialId, diaFin)
            //         setValores(material.campo, d.peso, 0, amA)
            //     }
            //     break
            }

        }
    }
    
    void setValores(String campo, def val, def can, def acu){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "blc", "BlcDetalle1", campo)
        d.setValor(val)
        d.setCantidad(can)
        d.setAcumulado(acu)
        getManager().persist(d)
    }
    
    // void setValores(String campo, BigDecimal valor, BigDecimal cantidad, BigDecimal acumulado){
    //     def det = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "blc", "BlcDetalle1", campo)
    //     if (det){
    //         println ">>>>>> IN" + det.material.campo
    //         if (valor)     { det.valor     = valor     } 
    //         if (cantidad)  { det.cantidad  = cantidad  }
    //         // if (acumulado) { det.acumulado = acumulado }
    //         det.acumulado = 100
    //         println ">>> acumulado: ${det.acumulado}"
    //         getManager().persist(det)
    //     }
    // }

}
