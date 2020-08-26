package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*;

class StockFabricaDetalleAction extends OnChangePropertyBaseAction{
    
    def padreId = ""
    def modulo  = ""
    def campoFk = ""
    
    void execute() throws Exception{
        
        // StockFabrica.StockFabricaDetalle1
        // https://stackoverflow.com/questions/14833008/java-string-split-with-dot
        modulo           = getModelName().split("\\.")[1]
        campoFk          = "stockFabrica.id"
        def map          = getView().getKeyValues() // id del padre
        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        
        // println ">>> modulo: ${modulo}"
        // println ">>> map   : ${map}"
        // println ">>> diaTrabajoId : $q}"
        
        if (map.id){
            // Este campo padre es el mismo de todos los detalles
            padreId = SqlUtil.instance.getCampoPorId(map.id, modulo, campoFk)
            def lista = SqlUtil.instance.getRegistros(padreId, modulo, campoFk)
		        
            //println("\n>>> View values:\n" + getView().getValues());
            
            def indicadorId = (String)getView().getValue("indicador.id")
            def indicador   = SqlUtil.instance.getIndicador(indicadorId)
            def valor       = (BigDecimal)getView().getValue("valor")

            switch (modulo){
            case "StockFabricaDetalle1":
                // Leer indicadores predefinidos y consultar datos.
                // def (a, b, c) = [10, 20, 'foo']
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdSac")
                def p    = new BrixDensidadWp().getP(brix)
                def h1   = getValor("H1")
                def h2   = getValor("H2")
                def h3   = getValor("H3")

                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // Ahora realizar los cálculos
                def porc = valor 
                // =+(K5*K4*K3)*W5/100
                def vt = porc ? Calculo.instance.redondear((h3*h2*h1)*porc/100, 2): 0
                //=((K6*X6/1000)*(Q5/100))
                def tonSacJDil = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                setValor("VT", vt)
                setValor("TonSacJDil", tonSacJDil)
                break
            
            case "StockFabricaDetalle2":
                // A penas cambia el valor (onChange) se actualiza en la tabla
                if (indicador.campo == "porcN" || indicador.campo == "porcV")
                    setValor(indicador.campo, valor)

                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcSac")
                def p    = new BrixDensidadWp().getP(brix)
                def h1   = getValor("H1")
                def h2   = getValor("H2")
                def h3   = getValor("H3")
                def o1   = getValor("o1")
                def h    = getValor("H")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                // Lee los campos de la tabla
                def porcN = getValor("porcN") 
                def porcV = getValor("porcV") 
                // println ">>> porcN: ${porcN}"
                // println ">>> porcV: ${porcV}"

                // =+(AQ4*AQ3*AQ2)*AV5/100
                def v1 = porcN ? Calculo.instance.redondear((h3*h2*h1)*porcN/100, 2): 0
                // =(3,1416*((AQ6/2)*(AQ6/2))*AQ7)*AV6/100
                def v2 = porcV ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h)*porcV/100, 2): 0
                def vt = v1 + v2
                // =(AQ9*AV7/1000)*(AJ4/100)
                def tonSacJCla = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                    
                setValor("V1"         , v1)
                setValor("V2"         , v2)
                setValor("VT"         , vt)
                setValor("TonSacJCla" , tonSacJCla)
                break
            
            case "StockFabricaDetalle3":
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jnBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jnSac")
                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                def h2   = getValor("H2")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // Ahora realizar los cálculos
                def porc = valor 
                // =(3,1416*((J10/2)*(J10/2))*J11)*O13/100
                def vt = porc ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100, 2): 0
                // =+((J12*O14)/1000)*(J14/100)
                def tonSacJSulf = Calculo.instance.redondear((vt*p/1000) * (sac/100), 2)
                setValor ("Vt", vt)
                setValor ("TonSacJSulf", tonSacJSulf)
                
                break
            
            case "StockFabricaDetalle4":
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jfBri")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jfSac")
                def p    = new BrixDensidadWp().getP(brix)
                def h1   = getValor("H1")
                def h2   = getValor("H2")
                def h3   = getValor("H3")

                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                        
                // Ahora realizar los cálculos
                def porc = valor 
                // =+(AB11*AB12*AB13)*AG14/100
                def vt = porc ? Calculo.instance.redondear((h3*h2*h1)*porc/100, 2): 0
                // =+((AB14*AG15)/1000)*(AB16/100)
                def tonSacJFiltr = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                setValor("Vt", vt)
                setValor("TonSacJFiltr", tonSacJFiltr)
                break
            
            case "StockFabricaDetalle5":
            case "StockFabricaDetalle18":
            case "StockFabricaDetalle19":
            case "StockFabricaDetalle20":
            case "StockFabricaDetalle21":
            case "StockFabricaDetalle22":
                
                def porc = valor 
                def (tmpTab, tmpBri, tmpSac, tmpTon, vTot) = ['', '', '', '', 0]

                switch(modulo){
                case "StockFabricaDetalle5":
                    tmpTab = "Jugo"; tmpBri = "jcBri"; tmpSac = "jcSac"; tmpTon = "TonSacClar";
                    // =259,29*AZ17/100
                    vTot = porc ? Calculo.instance.redondear(259.29*porc/100, 2): 0
                    break   
                case "StockFabricaDetalle18":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacTqMelCru";
                    // =8,1*AY51/100
                    vTot = porc ? Calculo.instance.redondear(8.1*porc/100, 2): 0
                    break
                case "StockFabricaDetalle19":
                case "StockFabricaDetalle20":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacCal";
                    // =0,55*N64/100
                    vTot = porc ? Calculo.instance.redondear(0.55*porc/100, 2): 0
                    break
                case "StockFabricaDetalle21":
                    tmpTab = "Meladura"; tmpBri = "mclBri2"; tmpSac = "mclSac"; tmpTon = "TonSacClaMel";
                    // =7,4*AN63/100
                    vTot = porc ? Calculo.instance.redondear(7.4*porc/100, 2): 0
                    break
                case "StockFabricaDetalle22":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacVasR";
                    // =1,12*AY63/100
                    vTot = porc ? Calculo.instance.redondear(1.12*porc/100, 2): 0
                    break
                }
                
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpSac)
                def p    = new BrixDensidadWp().getP(brix)
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // =+BA12*(BA18/1000)*(BA14/100)
                def tonSac = Calculo.instance.redondear((vTot*p/1000) * (sac/100),2)
                setValor("VTot", vTot)
                setValor(tmpTon, tonSac)
                break
            
            case "StockFabricaDetalle6":
            case "StockFabricaDetalle7":
                //println ">>> modulo: ${modulo}"
                def (tmpTab, tmpBri, tmpSac, tmpTon) = ['', '', '', '']
                if (modulo=="StockFabricaDetalle6"){
                    tmpTab = "Jugo"
                    tmpBri = "jnBri"
                    tmpSac = "jnSac"
                    tmpTon = "TonSacJSulf"
                }else{
                    tmpTab = "Meladura"
                    tmpBri = "mcrBri2"
                    tmpSac = "mcrSac"
                    tmpTon = "TonSacMel"
                }

                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpSac)

                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                def o2   = getValor("o2")
                def h1   = getValor("H1")
                def h3   = getValor("H3")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def porc = valor 
                def va = Calculo.instance.redondear(3.1416*((o1/2)*(o1/2))*h1, 2)
                // =(J24*3,1416/3)*(((J22/2)*(J22/2))+((J23/2)*(J23/2)+((J22/2)*(J23/2))))
                def vc = Calculo.instance.redondear( (h3*3.1416/3)*(((o1/2)*(o1/2))+((o2/2)*(o2/2)+((o1/2)*(o2/2)))), 2)
                // =(+J25+J21)*I31/100 
                def vTot = porc ? Calculo.instance.redondear((vc+va)*porc/100, 2): 0
                def tonSac = Calculo.instance.redondear((vTot*p/1000) * (sac/100),2)
                
                setValor("Va", va)
                setValor("VC", vc)
                setValor("VTot", vTot)
                setValor(tmpTon, tonSac)
                break
            
            case "StockFabricaDetalle8":
            case "StockFabricaDetalle9":
            case "StockFabricaDetalle10":
            case "StockFabricaDetalle11":
            case "StockFabricaDetalle12":
                def (tmpBri, tmpSac) = ['', '']
                if (modulo == "StockFabricaDetalle8"){
                    tmpBri = "jcBri"; tmpSac = "jcSac";
                }else{
                    tmpBri = "jdBri"; tmpSac = "jdSac";
                }
                
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", tmpSac)
                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                def h2   = getValor("H2")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def porc = valor 
                // =(3,1416*((AF20/2)*(AF20/2))*AF21)*AE27/100
                def vt = porc ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100, 2): 0
                // =+((AF22*AE28)/1000)*(AF24/100)
                def tonSac = Calculo.instance.redondear((vt*p/1000) * (sac/100), 2)
                setValor("Vt", vt)
                setValor("TonSacJC", tonSac)
                break

            case "StockFabricaDetalle13":
            case "StockFabricaDetalle14":
            case "StockFabricaDetalle15":
            case "StockFabricaDetalle16":
            case "StockFabricaDetalle17":
                def (brix, sac) = [0, 0]

                switch(modulo){
                case "StockFabricaDetalle13":
                    brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcBri")
                    sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jcSac")
                    break   
                
                case "StockFabricaDetalle14":
                case "StockFabricaDetalle15":
                    brix = getValor("Brix")

                    def d1 = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle13", campoFk, "Brix")
                    def d2 = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle13", campoFk, "Sac")
                    def v1 = d1 ? d1.valor?:0 : 0
                    def v2 = d2 ? d2.valor?:0 : 0

                    sac = v1>0 ? Calculo.instance.redondear(v2*brix/v1, 2): 0
                    //println "v1: ${v1}, v2: ${v2}, Sac: ${sac}"

                    setValor("Sac", sac)
                    break
                
                case "StockFabricaDetalle16":
                case "StockFabricaDetalle17":
                    brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Meladura", "mcrBri2")
                    sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Meladura", "mcrSac")
                    break
                } 
                
                def p  = new BrixDensidadWp().getP(brix)
                
                def o1 = getValor("o1")
                def o2 = getValor("o2")
                def h2 = getValor("H2")
                def h3 = getValor("H3")
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def h1 = valor 
                // =3,1416*((J41/2)*(J41/2))*J42
                def va = h1 ? Calculo.instance.redondear(3.1416*((o1/2)*(o1/2))*h1, 3): 0

                //println ">>>va: ${va}, h1: ${h1}, o1: ${o1}"

                // =3,1416*((J44/2)*(J44/2))*J45
                def vb = Calculo.instance.redondear(3.1416*((o1/2)*(o1/2))*h2, 3)
                // =(J49*3,1416/3)*(((J47/2)*(J47/2))+((J48/2)*(J48/2)+((J47/2)*(J48/2))))
                def vc = Calculo.instance.redondear( (h3*3.1416/3)*(((o1/2)*(o1/2))+((o2/2)*(o2/2)+((o1/2)*(o2/2)))), 3)
                def vTot = va + vb + vc
                // =+((J51*I56)/1000)*(J53/100)
                def tonSac = Calculo.instance.redondear((vTot*p/1000) * (sac/100),2)
                setValor("Va", va)
                setValor("Vb", vb)
                setValor("VC", vc)
                setValor("VTot", vTot)
                setValor("TonSacMel", tonSac)
                
                break
            }
        }
    }

    def getValor(def campo){
        def d = SqlUtil.instance.getDetallePorIndicador(padreId, modulo, campoFk, campo)
        return d.valor
    }

    void setValor(def campo, def nuevoValor){
        //println ">>> ${padreId} ${modulo} ${campoFk} ${campo}"
        def d = SqlUtil.instance.getDetallePorIndicador(padreId, modulo, campoFk, campo)
        //println ">>> Indicador: ${d.indicador.descripcion}, valor: ${d.valor}"
        d.setValor(nuevoValor)
        getManager().persist(d)
    }
}
