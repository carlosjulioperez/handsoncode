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
            // Este campo padre es el mismo de todos los detalles
            def stockFabricaId = SqlUtil.instance.getCampoPorId(map.id, modulo, campoFk)
            def lista = SqlUtil.instance.getRegistros(stockFabricaId, modulo, campoFk)
		        
            //println("\n>>> View values:\n" + getView().getValues());
            
            def indicadorId = (String)getView().getValue("indicador.id")
            def indicador   = SqlUtil.instance.getIndicador(indicadorId)
            def valor       = (BigDecimal)getView().getValue("valor")

            switch (modulo){
            case "StockFabricaDetalle1":
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
                def porc = valor 
                def (vt, tonSacJDil) = [0, 0]
                if (porc){
                    // =+(K5*K4*K3)*W5/100
                    vt = Calculo.instance.redondear((h3*h2*h1)*porc/100, 2)
                    //=((K6*X6/1000)*(Q5/100))
                    tonSacJDil = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                    
                    lista.each{
                        def campo = it.indicador.campo ?: ""
                        switch (campo){
                            case "VT"         : it.valor=vt         ; getManager().persist(it) ; break ;
                            case "TonSacJDil" : it.valor=tonSacJDil ; getManager().persist(it) ; break ;
                        }
                    }
                }
                break
            
            case "StockFabricaDetalle2":
                def (h1, h2, h3, o1, h) = [0, 0, 0, 0, 0]
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcSac")
                def p    = new BrixDensidadWp().getP(brix)

                lista.each{
                    def campo = it.indicador.campo ?: ""
                    
                    // Solo porcN y porcV son editables    
                    if (indicador.campo == campo){
                        it.valor = valor; getManager().persist(it);
                    }

                    switch (campo){
                        case "H1": h1 = it.valor; break;
                        case "H2": h2 = it.valor; break;
                        case "H3": h3 = it.valor; break;
                        case "o1": o1 = it.valor; break;
                        case "H" : h  = it.valor; break;
                        
                        case "Brix": it.valor = brix; getManager().persist(it); break;
                        case "Sac" : it.valor = sac ; getManager().persist(it); break;
                        case "p"   : it.valor = p   ; getManager().persist(it); break;
                    }
                }
                
                def porcN = SqlUtil.instance.getValorDetallePorIndicador(stockFabricaId, modulo, campoFk, "porcN") 
                def porcV = SqlUtil.instance.getValorDetallePorIndicador(stockFabricaId, modulo, campoFk, "porcV") 

                // println ">>> porcN: ${porcN}"
                // println ">>> porcV: ${porcV}"

                // Calculos
                def (v1, v2, vt, tonSacJCla) = [0, 0, 0, 0]
                if (porcN && porcV){
                    // =+(AQ4*AQ3*AQ2)*AV5/100
                    v1 = Calculo.instance.redondear((h3*h2*h1)*porcN/100, 2)
                    // =(3,1416*((AQ6/2)*(AQ6/2))*AQ7)*AV6/100
                    v2 = Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h)*porcV/100, 2)
                    vt = v1 + v2
                    // =(AQ9*AV7/1000)*(AJ4/100)
                    tonSacJCla = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                    
                    lista.each{
                        def campo = it.indicador.campo ?: ""
                        switch (campo){
                            case "V1"         : it.valor=v1         ; getManager().persist(it) ; break ;
                            case "V2"         : it.valor=v2         ; getManager().persist(it) ; break ;
                            case "VT"         : it.valor=vt         ; getManager().persist(it) ; break ;
                            case "TonSacJCla" : it.valor=tonSacJCla ; getManager().persist(it) ; break ;
                        }
                    }
                }
                break
            
            case "StockFabricaDetalle3":
                def (o1, h2) = [0, 0]
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jnBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jnSac")
                def p    = new BrixDensidadWp().getP(brix)

                lista.each{
                    def campo = it.indicador.campo ?: ""
                    switch (campo){
                        case "o1": o1 = it.valor; break;
                        case "H2": h2 = it.valor; break;
                        
                        case "Brix": it.valor = brix; getManager().persist(it); break;
                        case "Sac" : it.valor = sac ; getManager().persist(it); break;
                        case "p"   : it.valor = p   ; getManager().persist(it); break;
                    }
                }

                // Ahora realizar los cálculos
                def porc = valor 
                def (vt, tonSacJSulf) = [0, 0]
                if (porc){
                    // =+(K5*K4*K3)*W5/100
                    // =(3,1416*((J10/2)*(J10/2))*J11)*O13/100
                    vt = Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100,2)
                    // =+((J12*O14)/1000)*(J14/100)
                    tonSacJSulf = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                    
                    lista.each{
                        def campo = it.indicador.campo ?: ""
                        switch (campo){
                            case "Vt"         : it.valor=vt         ; getManager().persist(it) ; break ;
                            case "TonSacJSulf" : it.valor=tonSacJSulf ; getManager().persist(it) ; break ;
                        }
                    }
                }
                break
            }
        }
    }
}
