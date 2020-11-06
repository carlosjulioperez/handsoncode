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

            def cdA = SqlUtil.instance.getValMatBlcAcu("canaDia"    , diaFin)
            def amA = SqlUtil.instance.getValMatBlcAcu("aguaM"      , diaFin)
            def jdA = SqlUtil.instance.getValMatBlcAcu("jDiluidoBr" , diaFin)
            def bcA = SqlUtil.instance.getValMatBlcAcu("bagazoC"    , diaFin)
            def cA  = SqlUtil.instance.getValMatBlcAcu("cachaza"    , diaFin)
            def mfA = SqlUtil.instance.getValMatBlcAcu("mielFM"     , diaFin)
            def abA = SqlUtil.instance.getValMatBlcAcu("azucarB"    , diaFin)
            def cnA = SqlUtil.instance.getValMatBlcAcu("canaNeta"   , diaFin)
            def jnA = SqlUtil.instance.getValMatBlcAcu("jugoNeto"   , diaFin)
            def mA  = SqlUtil.instance.getValMatBlcAcu("meladura"   , diaFin)
            def hA  = SqlUtil.instance.getValMatBlcAcu("hojaCana"   , diaFin)
            def sdA = SqlUtil.instance.getValMatBlcAcu("sacosD"     , diaFin)
            
            // Para los valores ingresados por el usuario se usa getView().setValue().
            // Caso contrario, se modifica directamente en la tabla con set() y get()
            switch (material.campo){
            case "canaDia":
                getView().setValue("acumulado", valor+cdA)
                break
            
            case "hojaCana":
                getView().setValue("acumulado", valor+hA)
                break
            
            case "bagazoD":
                def cdV = getValor("canaDia", 1)
                def bdC = cdV ? Calculo.instance.redondear(valor*100/cdV, 2): 0
                getView().setValue("cantidad", bdC)
                break

            case "cachaza":
                def cdV = getValor("canaDia", 1)
                def cC  = cdV ? Calculo.instance.redondear(valor*100/cdV, 2): 0
                getView().setValue("cantidad", cC)
                getView().setValue("acumulado", valor+cA)
                break
            }

            def campo = "aguaM"
            def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", campo)
            if (d) {
                def amV = d.peso
                setValores(campo, amV, 0, amV+amA)
            }

            campo = "jDiluidoBr"
            d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle2", campo)
            if (d) {
                def jdV = d.volumen2
                def jdC = d.peso
                setValores(campo, jdV, jdC, jdC+jdA)
            }
            
            def cdV = getValor("canaDia", 1)
            def hV  = getValor("hojaCana", 1)
            def cnV = cdV - hV
            setValores("canaNeta", cnV, 0, cnV+cnA)
        
            def amV = getValor("aguaM", 1)
            def jdC = getValor("jDiluidoBr", 2)
            def bcV = cnV + amV - jdC
            def bcC = cdV ? Calculo.instance.redondear(bcV/cdV*100, 2): 0
            setValores("bagazoC", bcV, bcC, bcV+bcA)

            def solInsol = SqlUtil.instance.getValorCampo(diaTrabajoId, "Cto24H", "porcInso")
            def jnV      = Calculo.instance.redondear(jdC - (jdC*solInsol/100), 2)
            def jnC      = cdV ? Calculo.instance.redondear(jnV*100/cdV, 2): 0
            setValores("jugoNeto", jnV, jnC, jnV+jnA)
        
            // =(F10-(F10*D49/100))-((F10-(F10*D49/100))*(1-(H53/G200)))
            def jcBri   = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcBri")
            def mcrBri2 = SqlUtil.instance.getValorCampo(diaTrabajoId, "Meladura", "mcrBri2")
            def mV      = mcrBri2 ? Calculo.instance.redondear( (jdC-(jdC*solInsol/100))-((jdC-(jdC*solInsol/100))*(1-(jcBri/mcrBri2))) , 2): 0
            def mC      = cdV ? Calculo.instance.redondear(mV*100/cdV, 2): 0
            setValores("meladura", mV, mC, mV+mA)
        
            d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonAzuDis")
            def sdC = d.valor?:0
            def sdV = Calculo.instance.redondear(sdC*20,2)
            setValores("sacosD", sdV, sdC, sdV+sdA)
        
            d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonMelProTotDia")
            def mfV = d.valor?:0
            def mfC = cdV ? Calculo.instance.redondear(mfV*100/cdV, 2): 0
            setValores("mielFM", mfV, mfC, mfV+mfA)
        
            def abV = SqlUtil.instance.getValorCampo(diaTrabajoId, "Blc" , "qqTotalesDia")
            def abC = Calculo.instance.redondear(abV/20, 2)
            setValores("azucarB", abV, abC, abV+abA)
            
        }
    }
    
    void setValores(String campo, def val, def can, def acu){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "blc", "BlcDetalle1", campo)
        d.setValor(val)
        d.setCantidad(can)
        d.setAcumulado(acu)
        getManager().persist(d)
    }
    
    def getValor(def campo, def col){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "blc", "BlcDetalle1", campo)
        return col == 1 ? (d.valor?:0): (d.cantidad?:0)
    }

}
