package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*;

class StockFabricaDetalleAction extends OnChangePropertyBaseAction{
    
    void execute() throws Exception{
        
        // StockFabrica.StockFabricaDetalle1
        // https://stackoverflow.com/questions/14833008/java-string-split-with-dot
        def modulo       = getModelName().split("\\.")[1]
        def map          = getView().getKeyValues() // id del padre
        def campoFk      = "stockFabrica.id"
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        
        // println ">>> modulo: ${modulo}"
        // println ">>> map   : ${map}"
        // println ">>> diaTrabajoId : $q}"
        
        if (map.id){
            switch (modulo){
            case "StockFabricaDetalle1":
                def stockFabricaId = SqlUtil.instance.getCampoPorId(map.id, modulo, campoFk)
                def lista = SqlUtil.instance.getRegistros(stockFabricaId, modulo, campoFk)
                
                // Leer indicadores predefinidos y consultar datos.
                // def (a, b, c) = [10, 20, 'foo']
                def (h1, h2, h3) = [0, 0, 0]
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdSac")
                def p    = new BrixDensidadWp().getP(brix)

                lista.each{
                    def campo = it.indicador.campo ?: ""
                    switch (campo){
                        case "H1": h1 = it.valor; break;
                        case "H2": h2 = it.valor; break;
                        case "H3": h3 = it.valor; break;
                        
                        case "Brix": it.valor = brix; getManager().persist(it); break;
                        case "Sac" : it.valor = sac ; getManager().persist(it); break;
                        case "p"   : it.valor = p   ; getManager().persist(it); break;
                    }
                }
                
                // Ahora realizar los cálculos
                // =+(K5*K4*K3)*W5/100
                def porc = (BigDecimal)getView().getValue("valor")
                def (vt, tonSacJDil) = [0, 0]
                if (porc){
                    vt = Calculo.instance.redondear((h3*h2*h1)*porc/100, 2)
                    //=((K6*X6/1000)*(Q5/100))
                    tonSacJDil = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                }
                lista.each{
                    def campo = it.indicador.campo ?: ""
                    switch (campo){
                        case "VT"         : it.valor=vt         ; getManager().persist(it) ; break ;
                        case "TonSacJDil" : it.valor=tonSacJDil ; getManager().persist(it) ; break ;
                    }
                }

                break
            }
        }
    }
}
