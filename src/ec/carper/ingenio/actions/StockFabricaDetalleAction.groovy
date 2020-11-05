package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*;

class StockFabricaDetalleAction extends OnChangePropertyBaseAction{
    
    def modulo       = ""
    def diaTrabajoId = ""
    def campoFk      = "stockFabrica.diaTrabajo.id"
    
    void execute() throws Exception{
        
        // StockFabrica.StockFabricaDetalle1
        // https://stackoverflow.com/questions/14833008/java-string-split-with-dot
        modulo           = getModelName().split("\\.")[1]
        def map          = getView().getKeyValues() // id del padre
        diaTrabajoId     = (String)getView().getRoot().getValue("diaTrabajo.id")
        
        // println ">>> modulo: ${modulo}"
        // println ">>> map   : ${map}"
        // println ">>> diaTrabajoId : $q}"
        
        if (diaTrabajoId){
            // Este campo padre es el mismo de todos los detalles
            // diaTrabajoId = SqlUtil.instance.getCampoPorId(map.id, modulo, campoFk)
            def lista = SqlUtil.instance.getRegistros(diaTrabajoId, modulo, campoFk)
		        
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
                def vt = porc ? Calculo.instance.redondear((h3*h2*h1)*porc/100, 3): 0
                //=((K6*X6/1000)*(Q5/100))
                def tonSacJDil = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                setValor("Vt", vt)
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
                def v1 = porcN ? Calculo.instance.redondear((h3*h2*h1)*porcN/100, 3): 0
                // =(3,1416*((AQ6/2)*(AQ6/2))*AQ7)*AV6/100
                def v2 = porcV ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h)*porcV/100, 3): 0
                def vt = v1 + v2
                // =(AQ9*AV7/1000)*(AJ4/100)
                def tonSacJCla = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                    
                setValor("V1"         , v1)
                setValor("V2"         , v2)
                setValor("Vt"         , vt)
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
                def vt = porc ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100, 3): 0
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
                def vt = porc ? Calculo.instance.redondear((h3*h2*h1)*porc/100, 3): 0
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
            case "StockFabricaDetalle29":
            case "StockFabricaDetalle30":
            case "StockFabricaDetalle31":
            case "StockFabricaDetalle32":
            case "StockFabricaDetalle33":
            case "StockFabricaDetalle34":
            case "StockFabricaDetalle35":
            case "StockFabricaDetalle36":
            case "StockFabricaDetalle37":
            case "StockFabricaDetalle38":
            case "StockFabricaDetalle39":
            case "StockFabricaDetalle51":
            case "StockFabricaDetalle52":
            case "StockFabricaDetalle53":
            case "StockFabricaDetalle54":
            case "StockFabricaDetalle55":
            case "StockFabricaDetalle56":
            case "StockFabricaDetalle57":
            case "StockFabricaDetalle58":
            case "StockFabricaDetalle59":
            case "StockFabricaDetalle60":
            case "StockFabricaDetalle61":
            case "StockFabricaDetalle62":
            case "StockFabricaDetalle63":
            case "StockFabricaDetalle64":
            case "StockFabricaDetalle69":
                
                def porc = valor 
                def (tmpTab, tmpBri, tmpSac, tmpTon, factor) = ['', '', '', '', 0]
                def (brix, sac) = [0, 0]

                switch(modulo){
                case "StockFabricaDetalle5":
                    tmpTab = "Jugo"; tmpBri = "jcBri"; tmpSac = "jcSac"; tmpTon = "TonSacClar"; factor = 259.29; break;
                
                case "StockFabricaDetalle18":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacTqMelCru"; factor = 8.1; break;
                
                case "StockFabricaDetalle19":
                case "StockFabricaDetalle20":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacCal"; factor = 0.55; break;
                
                case "StockFabricaDetalle21":
                    tmpTab = "Meladura"; tmpBri = "mclBri2"; tmpSac = "mclSac"; tmpTon = "TonSacClaMel"; factor = 7.4; break;
                
                case "StockFabricaDetalle22":
                    tmpTab = "Meladura"; tmpBri = "mcrBri2"; tmpSac = "mcrSac"; tmpTon = "TonSacVasR"; factor = 1.12; break;

                case "StockFabricaDetalle29":
                    tmpTab = "Meladura"; tmpBri = "mclBri2"; tmpSac = "mclSac"; tmpTon = "TonSacTkMel"; factor = 30; break;
                
                case "StockFabricaDetalle30":
                case "StockFabricaDetalle31":
                    tmpTab = "Meladura"; tmpBri = "mclBri2"; tmpSac = "mclSac"; tmpTon = "TonSacTkMel"; factor = 20.4; break;
                
                case "StockFabricaDetalle32":
                    tmpTab = "TqFundidor"; tmpBri = "bri2"; tmpSac = "sac"; tmpTon = "TonSacFun"; factor = 20.4; break;
                case "StockFabricaDetalle33":
                    tmpTab = "TqFundidor"; tmpBri = "bri2"; tmpSac = "sac"; tmpTon = "TonSacFun"; factor = 10.2; break;

                case "StockFabricaDetalle34":
                    tmpTab = "Mieles"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon = "TonSacMieB"; factor = 20.4; break;
                case "StockFabricaDetalle35":
                    tmpTab = "Mieles"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon = "TonSacMieB"; factor = 10.2; break;
                
                case "StockFabricaDetalle36":
                    tmpTab = "Mieles"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon = "TonSacMieB"; factor = 20.4; break;
                case "StockFabricaDetalle37":
                    tmpTab = "Mieles"; tmpBri = "mrBri2"; tmpSac = "mrSac"; tmpTon = "TonSacMieB"; factor = 10.2; break;

                case "StockFabricaDetalle38":
                    tmpTab = "Mieles"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon = "TonSacMieB"; factor = 20; break;
                case "StockFabricaDetalle39":
                    tmpTab = "Mieles"; tmpBri = "mrBri2"; tmpSac = "mrSac"; tmpTon = "TonSacMieB"; factor = 19; break;
                
                // Alimentadores de la centrifugas
                case "StockFabricaDetalle51":
                    tmpTab = "Masas"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon = "TonSacJC"; factor = 5; break;
                
                case "StockFabricaDetalle52":
                    tmpTab = "Masas"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon = "TonSacJC"; factor = 1.2; break;
                
                case "StockFabricaDetalle53":
                    tmpTab = "Magmas"; tmpBri = "mcBri2"; tmpSac = "mcSac"; tmpTon = "TonSacJC"; factor = 1.2; break;
                
                case "StockFabricaDetalle54":
                    tmpTab = "Masas"; tmpBri = "mcBri2"; tmpSac = "mcSac"; tmpTon = "TonSacJC"; factor = 2; break;

                // Recibidores de Magmas - Planta Baja
                case "StockFabricaDetalle55":
                    tmpTab = "Magmas"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon = "TonSacMagB"; factor = 2.62; break;
                
                case "StockFabricaDetalle56":
                    tmpTab = "Magmas"; tmpBri = "mrBri2"; tmpSac = "mrSac"; tmpTon = "TonSacMagR"; factor = 2.4; break;
                
                case "StockFabricaDetalle57":
                    tmpTab = "Magmas"; tmpBri = "mcBri2"; tmpSac = "mcSac"; tmpTon = "TonSacMagC"; factor = 2.7; break;
                
                case "StockFabricaDetalle58":
                    tmpTon = "TonSacTer"; factor = 2.85; break;
                
                case "StockFabricaDetalle59":
                case "StockFabricaDetalle60":
                    tmpTab = "Mieles"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon = "TonSacMieA"; factor = 2; break;
                
                case "StockFabricaDetalle61":
                    tmpTab = "Mieles"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon = "TonSacMieB"; factor = 2; break;
                
                case "StockFabricaDetalle62":
                    tmpTon = "TonSacMieCRep"; factor = 2; break;
                
                case "StockFabricaDetalle63":
                    tmpTab = "TqFundidor"; tmpBri = "bri2"; tmpSac = "sac"; tmpTon = "TonSacEnFun"; factor = 8.59; break;
                
                case "StockFabricaDetalle64":
                    tmpTab = "TqFundidor"; tmpBri = "bri2"; tmpSac = "sac"; tmpTon = "TonSacEnFun"; factor = 6.06; break;
                
                case "StockFabricaDetalle69":
                    tmpTab = "Mieles"; tmpBri = "mfBri2"; tmpSac = "mfSac"; tmpTon = "TonSacJC"; factor = 1.56; break;
                
                }
                
                // Constantes para Brix y Sac 
                switch(modulo){
                case "StockFabricaDetalle58":
                case "StockFabricaDetalle62":
                    brix = getValor("Brix") 
                    sac  = getValor("Sac") 
                    break
                default:
                    brix = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpBri)
                    sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpSac)
                    break
                }

                def p    = new BrixDensidadWp().getP(brix)
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def vTot = porc ? Calculo.instance.redondear(factor*porc/100, 3): 0
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
                def va = Calculo.instance.redondear(3.1416*((o1/2)*(o1/2))*h1, 3)
                // =(J24*3,1416/3)*(((J22/2)*(J22/2))+((J23/2)*(J23/2)+((J22/2)*(J23/2))))
                def vc = Calculo.instance.redondear( (h3*3.1416/3)*(((o1/2)*(o1/2))+((o2/2)*(o2/2)+((o1/2)*(o2/2)))), 3)
                // =(+J25+J21)*I31/100 
                def vTot = porc ? Calculo.instance.redondear((vc+va)*porc/100, 3): 0
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
            case "StockFabricaDetalle65":

                def (tmpTab, tmpBri, tmpSac, tmpTon) = ['Jugo', 'jdBri', 'jdSac', 'TonSacJC']
                
                switch(modulo){
                case "StockFabricaDetalle8":
                    tmpBri = "jcBri"; tmpSac = "jcSac"; break;

                case "StockFabricaDetalle65":
                    tmpTab="Masas"; tmpBri = "mcBri2"; tmpSac = "mcSac"; tmpTon="TonSacMasCV"; break;
                }
                
                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpSac)
                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                def h2   = getValor("H2")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def porc = valor 
                // =(3,1416*((AF20/2)*(AF20/2))*AF21)*AE27/100
                def vt = porc ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100, 3): 0
                // =+((AF22*AE28)/1000)*(AF24/100)
                def tonSac = Calculo.instance.redondear((vt*p/1000) * (sac/100), 2)
                setValor("Vt", vt)
                setValor(tmpTon, tonSac)
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

                    def d1 = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle13", campoFk, "Brix")
                    def d2 = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle13", campoFk, "Sac")
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
            
            case "StockFabricaDetalle23":
            case "StockFabricaDetalle24":
            case "StockFabricaDetalle25":
            case "StockFabricaDetalle26":
            case "StockFabricaDetalle27":
            case "StockFabricaDetalle28":
                def (tmpBri, tmpSac) = ['', '']

                switch(modulo){
                case "StockFabricaDetalle23":
                case "StockFabricaDetalle24":
                    tmpBri = "maBri2"; tmpSac = "maSac"; break;
                
                case "StockFabricaDetalle25":
                case "StockFabricaDetalle26":
                    tmpBri = "mbBri2"; tmpSac = "mbSac"; break;
                
                case "StockFabricaDetalle27":
                case "StockFabricaDetalle28":
                    tmpBri = "mcBri2"; tmpSac = "mcSac"; break;
                }

                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Masas", tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Masas", tmpSac)
                def p    = new BrixDensidadWp().getP(brix)

                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // Ahora realizar los cálculos
                def porc = valor 
                def factorFt3 = new BigDecimal(new Parametro().obtenerValor("FT3"))
                def vtFt3 = getValor("VtFt3")
                // =+(K69*$R$77)*K75/100
                def vt = porc ? Calculo.instance.redondear((vtFt3*factorFt3)*porc/100, 3): 0
                //=((K6*X6/1000)*(Q5/100))
                def tonSacMas = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                setValor("Vt", vt)
                setValor("TonSacMas", tonSacMas)
                break
            
            case "StockFabricaDetalle40":
            case "StockFabricaDetalle41":
            case "StockFabricaDetalle42":
            case "StockFabricaDetalle43":
                def tmpTon = ""
                
                def brix = getValor("Brix") 
                def sac  = getValor("Sac") 

                switch(modulo){
                case "StockFabricaDetalle40":
                    brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Masas", "mcBri2")
                    sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Masas", "mcSac")
                    tmpTon = "TonSacSemA"
                    break
                case "StockFabricaDetalle41":
                    tmpTon = "TonSacSemB"
                    break
                case "StockFabricaDetalle42":
                case "StockFabricaDetalle43":
                    tmpTon = "TonSacSemC"
                    break
                }

                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                def h2   = getValor("H2")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)
                
                def porc = valor 
                // =(3,1416*((K90/2)*(K90/2))*K91)*J97/100
                def vt = porc ? Calculo.instance.redondear((3.1416*((o1/2)*(o1/2))*h2)*porc/100, 3): 0
                //  =+((K92*J98)/1000)*(K94/100)
                def tonSac = Calculo.instance.redondear((vt*p/1000) * (sac/100), 2)
                setValor("Vt", vt)
                setValor(tmpTon, tonSac)
                break
            
            case "StockFabricaDetalle44":
            case "StockFabricaDetalle45":
            case "StockFabricaDetalle46":
            case "StockFabricaDetalle47":
            case "StockFabricaDetalle48":
            case "StockFabricaDetalle49":
            case "StockFabricaDetalle50":
                def (tmpTab, tmpBri, tmpSac, tmpTon) = ['', '', '', '']

                switch(modulo){
                case "StockFabricaDetalle44":
                    tmpTab="Magmas"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon="TonSacMagB"; break;

                case "StockFabricaDetalle45":
                    tmpTab="Masas"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon="TonSacMasAI"; break;
                case "StockFabricaDetalle46":
                    tmpTab="Masas"; tmpBri = "maBri2"; tmpSac = "maSac"; tmpTon="TonSacMasAII"; break;
                
                case "StockFabricaDetalle47":
                case "StockFabricaDetalle48":
                    tmpTab="Masas"; tmpBri = "mbBri2"; tmpSac = "mbSac"; tmpTon="TonSacMasB"; break;
                
                case "StockFabricaDetalle49":
                case "StockFabricaDetalle50":
                    tmpTab="Masas"; tmpBri = "mcBri2"; tmpSac = "mcSac"; tmpTon="TonSacMasC"; break;
                }

                def h1   = getValor("H1")
                def h2   = getValor("H2")
                def h3   = getValor("H3")
                def r    = getValor("R")

                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpBri)
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, tmpTab, tmpSac)
                def p    = new BrixDensidadWp().getP(brix)

                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // Ahora realizar los cálculos
                def porc = valor 
                
                def vr = Calculo.instance.redondear(h1*h2*h3, 3);
                // =(3,1416*((AP94)*(AP94))*AP92)/2
                def vc = Calculo.instance.redondear( (3.1416*r*r*h3)/2, 3);
                // =(+AP95+AP93)*AO101/100
                def vt = porc ? Calculo.instance.redondear((vc+vr)*porc/100, 3): 0
                // =+((AP96*AO102)/1000)*(AP98/100)
                def tonSac = Calculo.instance.redondear((vt*p/1000) * (sac/100),2)
                
                setValor("Vr", vr)
                setValor("VC", vc)
                setValor("Vt", vt)
                setValor(tmpTon, tonSac)
                break
            
            case "StockFabricaDetalle66":
            case "StockFabricaDetalle67":
            case "StockFabricaDetalle68":
                def porc = valor 
                def factor = modulo=="StockFabricaDetalle68" ? 27.33: 50
                def vt = porc ? Calculo.instance.redondear(factor*porc/100, 3): 0
                def sac = SqlUtil.instance.getValorCampo(diaTrabajoId, "AzucarGranel", "pol")
                
                // =+K132*K133/100
                def tonSac = Calculo.instance.redondear(vt*sac/100, 2)
                
                setValor("Sac", sac)
                setValor("Vt", vt)
                setValor("TonSacAzu", tonSac)
                break
            
            case "StockFabricaDetalle70":
            case "StockFabricaDetalle71":
                def h2   = valor

                def brix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Mieles", "mfBri2")
                def sac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Mieles", "mfSac")
                def p    = new BrixDensidadWp().getP(brix)
                def o1   = getValor("o1")
                
                setValor("Brix" , brix)
                setValor("Sac"  , sac)
                setValor("p"    , p)

                // Ahora realizar los cálculos
                // =3,1416*((U142/2)*(U142/2))*U143
                def vt = h2 ? Calculo.instance.redondear(3.1416*(o1/2)*(o1/2)*h2, 3): 0
                // =+((U144*T149)/1000)*(U146/100)
                def tonSac = Calculo.instance.redondear((vt*p/1000) * (sac/100), 2)
                // =+U146/U145*100
                def pza = brix ? Calculo.instance.redondear(sac/brix*100, 2): 0
                def tomMf = Calculo.instance.redondear(vt*p/1000 ,2) 
                setValor ("Vt", vt)
                setValor ("TonSacMasC", tonSac)
                setValor ("Pza", pza)
                setValor ("TonMF", tomMf)
                break

            }

            // Validación para actualizar los totales
            // Totales Ton Sac
            int numero = modulo.findAll(/\d+/)*.toInteger()[0]?:0
            println ">>> numero: ${numero}"

            if ( numero>=6 && numero<=7 )
                setTotalValor(72, "tonSacTorSul", getSumaValores(6, 7, ["TonSacJSulf", "TonSacMel"]))
            
            if ( numero>=1 && numero<=5 )
                setTotalValor(72, "tonSacTraJug", getSumaValores(1, 5, ["TonSacJDil", "TonSacJCla", "TonSacJSulf", "TonSacJFiltr", "TonSacClar"]))
            
            if ( numero>=8 && numero<=12 ){
                def l = []
                (1..5).each{ l << "TonSacJC" }
                setTotalValor(72, "tonSacCal", getSumaValores(8, 12, l))
            }

            if ( numero>=13 && numero<=17 ){
                def l = []
                (1..5).each{ l << "TonSacMel" }
                setTotalValor(72, "tonSacEva", getSumaValores(13, 17, l))
            }

            if ( numero>=19 && numero<=20 ){
                def l = []
                (1..2).each{ l << "TonSacCal" }
                setTotalValor(72, "tonSacCalMel", getSumaValores(19, 20, l))
            }

            if ( numero==18 || numero==21 || numero==22 ){
                def suma = getTotalValor(72, "tonSacCalMel") +
                    getSumaValores(21, 21, ["TonSacClaMel"]) + 
                    getSumaValores(22, 22, ["TonSacVasR"]) + 
                    getSumaValores(18, 18, ["TonSacTqMelCru"])
                
                setTotalValor(72, "tonSacClaMel", suma)
            }

            if ( numero>=23 && numero<=28 ){
                def l = []
                (1..6).each{ l << "TonSacMas" }
                setTotalValor(72, "tonSacCri", getSumaValores(23, 28, l))
            }
            
            if ( numero>=29 && numero<=39 ){
                def l = []
                (1..3).each{ l << "TonSacTkMel" }
                (1..2).each{ l << "TonSacFun" }
                (1..6).each{ l << "TonSacMieB" }
                setTotalValor(72, "tonSacTqAlm", getSumaValores(29, 39, l))
            }
            
            if ( numero>=40 && numero<=43 )
                setTotalValor(72, "tonSacCriVac", getSumaValores(40, 43, ["TonSacSemA", "TonSacSemB", "TonSacSemC", "TonSacSemC"]))
            
            if ( numero>=44 && numero<=50 )
                setTotalValor(72, "tonSacRecMas", getSumaValores(44, 50, ["TonSacMagB", "TonSacMasAI", "TonSacMasAII", "TonSacMasB", "TonSacMasB", "TonSacMasC", "TonSacMasC"]))
            
            if ( numero>=51 && numero<=54 ){
                def l = []
                (1..4).each{ l << "TonSacJC" }
                setTotalValor(72, "tonSacRecMat", getSumaValores(51, 54, l))
            }

            if ( numero>=55 && numero<=58 )
                setTotalValor(72, "tonSacRecCen", getSumaValores(55, 58, ["TonSacMagB", "TonSacMagR", "TonSacMagC", "TonSacTer"]))

            if ( numero>=59 && numero<=62 )
                setTotalValor(72, "tonSacRecMieCen", getSumaValores(59, 62, ["TonSacMieA", "TonSacMieA", "TonSacMieB", "TonSacMieCRep"]))

            if ( numero>=63 && numero<=65 )
                setTotalValor(72, "tonSacFunCriVer", getSumaValores(63, 65, ["TonSacEnFun", "TonSacEnFun", "TonSacMasCV"]))

            if ( numero>=66 && numero<=68 ){
                def l = []
                (1..3).each{ l << "TonSacAzu" }
                setTotalValor(72, "tonSacSilAzu", getSumaValores(66, 68, l))
            }

            if ( numero>=70 && numero<=71 )
                setTotalValor(72, "tonTot", getSumaValores(70, 71, ["TonMF", "TonMF"]))

            // Totales generales
            if ( numero==73 ){
                if (indicador.campo == "tonMelVen" || indicador.campo == "tonAzuDis")
                    setTotalValor(73, indicador.campo, valor)
                    
                def tonMelVen = getTotalValor(73, "tonMelVen")

                def tonAzuDis = getTotalValor(73, "tonAzuDis")

                // def bg144 = (tonSacTorSul + tonSacTraJug + tonSacCal + tonSacEva + tonSacClaMel + tonSacCri + tonSacTqAlm + tonSacCriVac + tonSacRecMas + tonSacRecMat + tonSacRecCen + tonSacRecMieCen + tonSacFunCriVer + tonSacSilAzu - (fldTonAzuDis?:0) )
                def bg144 = 
                    getTotalValor(72, "tonSacTraJug") +
                    getTotalValor(72, "tonSacTorSul") +
                    getTotalValor(72, "tonSacCal") +
                    getTotalValor(72, "tonSacEva") +
                    getTotalValor(72, "tonSacClaMel") +
                    getTotalValor(72, "tonSacCri") +
                    getTotalValor(72, "tonSacTqAlm") +
                    getTotalValor(72, "tonSacCriVac") +
                    getTotalValor(72, "tonSacRecMas") +
                    getTotalValor(72, "tonSacRecMat") +
                    getTotalValor(72, "tonSacRecCen") +
                    getTotalValor(72, "tonSacRecMieCen") +
                    getTotalValor(72, "tonSacFunCriVer") +
                    getTotalValor(72, "tonSacSilAzu") - tonAzuDis
                //println ">>> tonAzu: ${tonAzu},  suma: ${suma}"
                
                def bg149 = Calculo.instance.redondear((
                    getSumaValores(1 , 28, "Brix") + 
                    getSumaValores(30, 38, "Brix") +  
                    getSumaValores(40, 42, "Brix") +  
                    getSumaValores(44, 47, "Brix") +  
                    getSumaValores(49, 49, "Brix") +  
                    getSumaValores(51, 65, "Brix") +
                    getSumaValores(66, 68, "Bx")) / 63, 3
                )
                
                def bg150 = Calculo.instance.redondear((
                    getSumaValores(1 , 28, "Sac") + 
                    getSumaValores(30, 38, "Sac") +  
                    getSumaValores(40, 42, "Sac") +  
                    getSumaValores(44, 47, "Sac") +  
                    getSumaValores(49, 49, "Sac") +  
                    getSumaValores(51, 68, "Sac")) / 63, 3
                )
                
                def bg151 = Calculo.instance.redondear(bg149 ? bg150/bg149*100: 0, 3)
                def bg152 = new BrixDensidadWp().getP(bg149)
                
                def bg147 = Calculo.instance.redondear((
                    getSumaValores( 1 , 4  , "Vt") +
                    getSumaValores( 8 , 12 , "Vt") +
                    getSumaValores(23 , 28 , "Vt") +
                    getSumaValores(40 , 42 , "Vt") +
                    getSumaValores(44 , 47 , "Vt") +
                    getSumaValores(49 , 49 , "Vt") +
                    getSumaValores(65 , 68 , "Vt") +
                    getSumaValores(5  , 7  , "VTot") +
                    getSumaValores(13 , 22 , "VTot") +
                    getSumaValores(30 , 38 , "VTot") +
                    getSumaValores(51 , 64 , "VTot") ) * (bg152/1000), 3
                )
                
                def bg153 = Calculo.instance.redondear(bg147*bg149/100, 3)
                def bg154 = Calculo.instance.redondear(bg147*bg150/100, 3)

                def q133 = getSumaValores(67 , 67  , "Sac")
                def u150 = getSumaValores(70 , 70  , "Pza")
                
                def bg155 = Calculo.instance.redondear((q133*(bg151-u150))/(bg151*(q133-u150))*100, 3)
                
                def bg156 = Calculo.instance.redondear(bg154*bg155/100, 3)
                def bg157 = q133 ? Calculo.instance.redondear((bg156/q133)*100, 3): 0
                def bg158 = Calculo.instance.redondear(bg154-bg156, 3)
                
                def u146 = getSumaValores(70 , 70  , "Sac")
                def bg159 = u146 ? Calculo.instance.redondear(bg158/u146*100, 3): 0
                
                // 2020-09-30
                def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle70", campoFk, "Vt")
                def u144 = d.valor?:0

                d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle70", campoFk, "p")
                def t149 = d.valor?:0
                
                d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle71", campoFk, "Vt")
                def u155 = d.valor?:0

                d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle71", campoFk, "p")
                def t160 = d.valor?:0

                // =((U144*T149)/1000)+(U155*T160)/1000
                def ax132 = Calculo.instance.redondear(((u144*t149)/1000)+(u155*t160)/1000, 3)
                
                def diaTrabajo = SqlUtil.instance.getDiaTrabajo(diaTrabajoId)
                def diaFin = diaTrabajo.numeroDia - 1
                def ax135 = SqlUtil.instance.getValMatBlcAcu("mielFM", diaFin)

                def ax129 = tonMelVen //7.86
                def ax140 = ax132 -ax135 + ax129
                
                setTotalValor(73 , "tonMelProTotZaf"    , ax132)
                setTotalValor(73 , "tonMelProTotDiaAnt" , ax135)
                setTotalValor(73 , "tonMelProTotDia"    , ax140)
                // --

                setTotalValor(73 , "tonSacTot"          , bg144)
                setTotalValor(73 , "pesMatTotDia"       , bg147)
                setTotalValor(73 , "proSolBriTotDia"    , bg149)
                setTotalValor(73 , "proSacPolTotDia"    , bg150)
                setTotalValor(73 , "proPzaTotDia"       , bg151)
                setTotalValor(73 , "denKgm"             , bg152)
                setTotalValor(73 , "pesSolDia"          , bg153)
                setTotalValor(73 , "pesPolDia"          , bg154)
                setTotalValor(73 , "sjmMatPro"          , bg155)
                setTotalValor(73 , "sacRecAz"           , bg156)
                setTotalValor(73 , "azuRec"             , bg157)
                setTotalValor(73 , "sacMieFin"          , bg158)
                setTotalValor(73 , "mieFinRec"          , bg159)

                // println ""
                // println ">>> bg147: ${bg147}"
                // println ">>> bg149: ${bg149}"
                // println ">>> bg150: ${bg150}"
                // println ">>> bg151: ${bg151}"
                // println ">>> bg152: ${bg152}"
                // println ">>> bg153: ${bg153}"
                // println ">>> bg154: ${bg154}"
                // println ">>> bg155: ${bg155}"
                // println ">>> bg156: ${bg156}"
                // println ">>> bg157: ${bg157}"
                // println ">>> bg158: ${bg158}"
                // println ">>> bg159: ${bg159}"
           }
        }
    }

    def getValor(def campo){
        def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, modulo, campoFk, campo)
        return d.valor
    }

    void setValor(def campo, def nuevoValor){
        //println ">>> ${diaTrabajoId} ${modulo} ${campoFk} ${campo}"
        def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, modulo, campoFk, campo)
        //println ">>> Indicador: ${d.indicador.descripcion}, valor: ${d.valor}"
        d.setValor(nuevoValor)
        getManager().persist(d)
    }
    
    // Totales, 72:Tom, 73:Generales **************************************************
    def getTotalValor(def num, def campo){
        def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle${num}", campoFk, campo)
        return d.valor?:0
    }
    void setTotalValor(def num, def campo, def nuevoValor){
        def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle${num}", campoFk, campo)
        //println ">>> Indicador: ${d.indicador.descripcion}, valor: ${d.valor}"
        d.setValor(nuevoValor)
        getManager().persist(d)
    }
    // ********************************************************************************

    // Vienen de la cabecera
    def getSumaValores(def desde, def hasta, def campos){
        def valor = 0
        if (diaTrabajoId){
            def i = 0
            (desde..hasta).each{
                def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle${it}", campoFk, campos[i++])
                if (d)
                    valor += d.valor ?: 0
            }
        }
        return valor
    }
    
    def getSumaValores(def desde, def hasta, String indicador){
        def (suma, i) = [0, 0]
        if (diaTrabajoId){
            (desde..hasta).each{
                def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, "StockFabricaDetalle${it}", campoFk, indicador)
                suma += d ? d.valor: 0
                // if (indicador=="Vt" || indicador=="VTot")
                //     println "${it}, ${d.valor}"
            }
        }
        return suma
    }
}
