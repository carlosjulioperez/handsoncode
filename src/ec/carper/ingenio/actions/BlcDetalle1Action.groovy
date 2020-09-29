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

            switch (material.campo){
            case "canaDia":
                def cdA = SqlUtil.instance.getValMatBlcAcu(materialId, diaFin)
                setValores(material.campo, valor, 0, valor + cdA)
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
