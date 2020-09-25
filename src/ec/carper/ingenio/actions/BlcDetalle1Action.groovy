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
        def materialId = (String)getView().getValue("material.id")
        def material   = SqlUtil.instance.getMaterial(materialId)
        
        def valor = (BigDecimal)getView().getValue("valor")
        
        // Leer aguaM
        def det = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", "aguaM")
        if (det) setValor("aguaM", det.peso, 0)
        
    }
    
    void setValor(def campo, def valor, def cantidad){
        // println ">>>campo: ${campo}"
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "blc", "BlcDetalle1", campo)
        // println ">>> d: ${d}"
        if (d){
            d.setValor(valor)
            d.setCantidad(cantidad)
            getManager().persist(d)
        }
    }

    // def getValor(def campo){
    //     def d = SqlUtil.instance.getDetallePorIndicador(padreId, modulo, campoFk, campo)
    //     return d.valor
    // }
}
