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

            cdA = SqlUtil.instance.getValMatBlcAcu("canaDia"    , diaFin)
            amA = SqlUtil.instance.getValMatBlcAcu("aguaM"      , diaFin)
            jdA = SqlUtil.instance.getValMatBlcAcu("jDiluidoBr" , diaFin)
            bcA = SqlUtil.instance.getValMatBlcAcu("bagazoC"    , diaFin)
            cA  = SqlUtil.instance.getValMatBlcAcu("cachaza"    , diaFin)
            mfA = SqlUtil.instance.getValMatBlcAcu("mielFM"     , diaFin)
            abA = SqlUtil.instance.getValMatBlcAcu("azucarB"    , diaFin)
            cnA = SqlUtil.instance.getValMatBlcAcu("canaNeta"   , diaFin)
            jnA = SqlUtil.instance.getValMatBlcAcu("jugoNeto"   , diaFin)
            mA  = SqlUtil.instance.getValMatBlcAcu("meladura"   , diaFin)
            hA  = SqlUtil.instance.getValMatBlcAcu("hojaCana"   , diaFin)
            sdA = SqlUtil.instance.getValMatBlcAcu("sacosD"     , diaFin)
            
            // Para los valores ingresados por el usuario se usa getView().setValue().
            // Caso contrario, se modifica directamente en la tabla con set() y get()
            switch (material.campo){
            case "canaDia":
                cdV = valor
                getView().setValue("acumulado", cdV+cdA)
                break
            }

            def campo = "aguaM"
            def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", campo)
            if (d) {
                amV = d.peso
                setValores(campo, amV, 0, amV+amA)
            }

            campo = "jDiluidoBr"
            d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", campo)
            if (d) {
                jdV = d.volumen2
                jdC = d.peso
                setValores(campo, jdV, jdC, jdV+jdA)
            }

            // modelo
            //setValores(material.campo, cdV, 0, cdV + cdA)

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
