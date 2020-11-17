package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

class StockProcesoDetalle1Action extends OnChangePropertyBaseAction{

    def diaTrabajoId = ""

    def suma (desde, hasta, objPadre, txtDet, indicador){
        def suma = 0
        (desde..hasta).each{
            suma += SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, "${txtDet}${it}", indicador)
        }
        return suma
    }

    // No obtengo los valores, solo disparo la acción para realizar los cálculos
    void execute() throws Exception{

        diaTrabajoId     = (String)getView().getRoot().getValue("diaTrabajo.id")
        def materialId   = (String)getView().getValue("material.id")
        def material     = SqlUtil.instance.getMaterial(materialId)
        
        if (material.campo){
            // Obtener el material y dependiendo del campo hacer las validaciones
            def temp = getView().getValue("temp")
            def eq   = getView().getValue("eq")

            if (temp==null || eq==null ){
                getView().setValue("volumen1" , null)
                getView().setValue("volumen2" , null)
                getView().setValue("peso"     , null)
                getView().setValue("porcBrix" , null)
                getView().setValue("tonBrix"  , null)
                getView().setValue("porcSac"  , null)
                getView().setValue("tonSac"   , null)
                getView().setValue("pureza"   , null)
                getView().setValue("densidad" , null)
                getView().setValue("factor"   , null)
            }else {
        
                def (num, volumen1) = [1, 0]
                switch (material.campo){
                    case "tanJugDil" : num=1  ; break ;
                    case "tanJugCla" : num=2  ; break ;
                    case "tanJugEnc" : num=3  ; break ;
                    case "tanJugFil" : num=4  ; break ;
                    case "claJug"    : num=5  ; break ;
                    case "torSulJug" : num=6  ; break ;
                    case "calJugCla" : num=8  ; break ;
                    case "calJug"    : num=9  ; break ;
                    case "eva1"      : num=13 ; break ;
                    case "eva2"      : num=14 ; break ;
                    case "eva3"      : num=15 ; break ;
                    case "eva4"      : num=16 ; break ;
                    case "eva5"      : num=17 ; break ;
                    case "tanMelCru" : num=18 ; break ;
                    case "calMel"    : num=19 ; break ;
                    case "claMel"    : num=21 ; break ;
                    case "vasRea"    : num=22 ; break ;
                    case "tac1"      : num=23 ; break ;
                    case "tac2"      : num=24 ; break ;
                    case "tac3"      : num=26 ; break ;
                    case "tac4"      : num=28 ; break ;
                    case "tanMel1"   : num=30 ; break ;
                    case "tanMel2"   : num=31 ; break ;
                    case "tanMel3"   : num=29 ; break ;
                    case "tanFun1"   : num=32 ; break ;
                    case "tanFun2"   : num=33 ; break ;
                    case "tanMie1"   : num=34 ; break ;
                    case "tanMie2"   : num=35 ; break ;
                    case "tanMie3"   : num=36 ; break ;
                    case "tanMie4"   : num=37 ; break ;
                    case "tanPreMie" : num=38 ; break ;
                    case "tanMieClM" : num=39 ; break ;
                    case "sem1"      : num=40 ; break ;
                    case "sem2"      : num=41 ; break ;
                    case "sem3"      : num=42 ; break ;
                    case "sem4"      : num=43 ; break ;
                    case "recMag2"   : num=44 ; break ;
                    case "recMas1"   : num=45 ; break ;
                    case "recMas2"   : num=46 ; break ;
                    case "recMas3"   : num=47 ; break ;
                    case "recMas4"   : num=48 ; break ;
                    case "recMas5"   : num=49 ; break ;
                    case "recMas6"   : num=50 ; break ;
                    case "aliCen1"   : num=51 ; break ;
                    case "aliCen2"   : num=52 ; break ;
                    case "aliCen3"   : num=53 ; break ;
                    case "aliCen4"   : num=54 ; break ;
                    case "recMag1"   : num=55 ; break ;
                    case "recMag3"   : num=56 ; break ;
                    case "recMag4"   : num=57 ; break ;
                    case "recMie1"   : num=59 ; break ;
                    case "recMie2"   : num=60 ; break ;
                    case "recMie3"   : num=61 ; break ;
                    case "recMie4"   : num=61 ; break ; //Duplicado de recMie3
                    case "funVie1"   : num=63 ; break ;
                    case "funNue2"   : num=64 ; break ;
                    case "criVer"    : num=65 ; break ;
                    case "tol50K1"   : num=66 ; break ;
                    case "tol50K2"   : num=67 ; break ;
                    case "tolFam"    : num=68 ; break ;
                }

                def l = [5, 6, 13,14,15,16,17,18,19,21,22, 29,30,31,32,33,34,35,36,37,38,39, 51,52,53,54,55,56,57, 59,60,61, 63,64] // Para no usar los engorrosos "if"
                def ind1 = l.find {it==num} ? "VTot" : "Vt"

                def objPadre = "stockFabrica"
                def txtDet   = "StockFabricaDetalle"
                def detalle  = "${txtDet}${num}"

                def factor   = new FactorVolumen().getValor(temp, eq+1)

                switch(num){
                    case 9:
                        volumen1 = suma(9, 12, objPadre, txtDet, ind1); break;
                    case 19:
                        volumen1 = suma(19, 20, objPadre, txtDet, ind1); break;
                    case 24:
                        volumen1 = suma(24, 25, objPadre, txtDet, ind1); break;
                    case 26:
                        volumen1 = suma(26, 27, objPadre, txtDet, ind1); break;
                    case 28:
                        volumen1 = suma(28, 29, objPadre, txtDet, ind1); break;
                    default:
                        volumen1 = SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, detalle, ind1); break;
                }
                
                def (porcBrix, densidad) = [0, 0]
                if (material.campo=="tol50K1" || material.campo=="tol50K2" || material.campo=="tolFam"){
                    porcBrix = 100 - SqlUtil.instance.getValorCampo(diaTrabajoId, "AzucarGranel", "humedad")
                    densidad = 906.15199 
                    factor   = 1
                }else{
                    porcBrix = SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, detalle, "Brix")
                    densidad = new BrixDensidadWp().getP(porcBrix)
                }
                
                def volumen2 = factor ? Calculo.instance.redondear(volumen1/factor, 3): 0
                def peso     = Calculo.instance.redondear(densidad*volumen2/1000, 3)
                def tonBrix  = Calculo.instance.redondear(peso*porcBrix/100, 3)
                
                // Caso especial (E60)
                if (material.campo=="recMie4")
                    detalle  = "${txtDet}${num+1}"
                
                def porcSac = 0
                if (material.campo=="tol50K1" || material.campo=="tol50K2" || material.campo=="tolFam")
                    porcSac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "AzucarGranel", "pol")
                else    
                    porcSac  = SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, detalle, "Sac")
                
                def tonSac   = Calculo.instance.redondear(peso*porcSac/100, 3)
                def pureza   = porcBrix ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0
                
                // Caso especial (J61, K61, L61)
                if (material.campo=="funVie1"){
                    def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle1", "recMie4")
                    if (d){
                        tonBrix = d.tonBrix ?:0
                        porcSac = d.porcSac ?:0
                        tonSac  = d.tonSac ?:0
                        pureza  = (porcSac && porcBrix) ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0
                    }
                }
                
                // Caso especial (F54 en adelante)
                if (material.campo=="recMag3"){
                    def d = SqlUtil.instance.getDetallePorDTM(diaTrabajoId, "stockProceso", "StockProcesoDetalle1", "recMag1")
                    if (d){
                        volumen2 = d.volumen1
                        peso     = d.peso
                        porcBrix = d.porcBrix
                        eq       = d.eq
                        porcSac  = d.porcSac
                        densidad = d.densidad
                        factor   = new FactorVolumen().getValor(temp, eq+1)
                    }
                }

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
}
