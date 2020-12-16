package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import org.openxava.actions.*

// TODO: Ejemplo de clase para invocar los cálculos para controles desde otra clase.
class StockProcesoDetalle1Action extends OnChangePropertyBaseAction{
    
    // https://javadevnotes.com/groovy-map-examples
    def mapMaterial = [
        "tanJugDil" : 1  ,
        "tanJugCla" : 2  ,
        "tanJugEnc" : 3  ,
        "tanJugFil" : 4  ,
        "claJug"    : 5  ,
        "torSulJug" : 6  ,
        "calJugCla" : 8  ,
        "calJug"    : 9  ,
        "eva1"      : 13 ,
        "eva2"      : 14 ,
        "eva3"      : 15 ,
        "eva4"      : 16 ,
        "eva5"      : 17 ,
        "tanMelCru" : 18 ,
        "calMel"    : 19 ,
        "claMel"    : 21 ,
        "vasRea"    : 22 ,
        "tac1"      : 23 ,
        "tac2"      : 24 ,
        "tac3"      : 26 ,
        "tac4"      : 28 ,
        "tanMel1"   : 30 ,
        "tanMel2"   : 31 ,
        "tanMel3"   : 29 ,
        "tanFun1"   : 32 ,
        "tanFun2"   : 33 ,
        "tanMie1"   : 34 ,
        "tanMie2"   : 35 ,
        "tanMie3"   : 36 ,
        "tanMie4"   : 37 ,
        "tanPreMie" : 38 ,
        "tanMieClM" : 39 ,
        "sem1"      : 40 ,
        "sem2"      : 41 ,
        "sem3"      : 42 ,
        "sem4"      : 43 ,
        "recMag2"   : 44 ,
        "recMas1"   : 45 ,
        "recMas2"   : 46 ,
        "recMas3"   : 47 ,
        "recMas4"   : 48 ,
        "recMas5"   : 49 ,
        "recMas6"   : 50 ,
        "aliCen1"   : 51 ,
        "aliCen2"   : 52 ,
        "aliCen3"   : 53 ,
        "aliCen4"   : 54 ,
        "recMag1"   : 55 ,
        "recMag3"   : 56 ,
        "recMag4"   : 57 ,
        "recMie1"   : 59 ,
        "recMie2"   : 60 ,
        "recMie3"   : 61 ,
        "recMie4"   : 61 , //Duplicado de recMie3
        "funVie1"   : 63 ,
        "funNue2"   : 64 ,
        "criVer"    : 65 ,
        "tol50K1"   : 66 ,
        "tol50K2"   : 67 ,
        "tolFam"    : 68
    ]

    def suma (diaTrabajoId, desde, hasta, objPadre, txtDet, indicador){
        def suma = 0
        (desde..hasta).each{
            suma += SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, "${txtDet}${it}", indicador)
        } 
        return suma
    }

    def getMapa(diaTrabajoId, materialId, int temp){
        def retorno = [
           "eq"       : 0 ,
           "volumen1" : 0 ,
           "volumen2" : 0 ,
           "peso"     : 0 ,
           "porcBrix" : 0 ,
           "tonBrix"  : 0 ,
           "porcSac"  : 0 ,
           "tonSac"   : 0 ,
           "pureza"   : 0 ,
           "densidad" : 0 ,
           "factor"   : 0
        ]
        
        if  (temp==null)
            return retorno
        else{
            def material = SqlUtil.instance.getMaterial(materialId)
            if (material.campo){
                def volumen1 = 0
                def num      = mapMaterial[material.campo]

                def l = [5, 6, 13,14,15,16,17,18,19,21,22, 29,30,31,32,33,34,35,36,37,38,39, 51,52,53,54,55,56,57, 59,60,61, 63,64] // Para no usar los engorrosos "if"
                def ind1 = l.find {it==num} ? "VTot" : "Vt"

                def objPadre = "stockFabrica"
                def txtDet   = "StockFabricaDetalle"
                def detalle  = "${txtDet}${num}"

                switch(num){
                    case 9:
                        volumen1 = suma(diaTrabajoId, 9, 12, objPadre, txtDet, ind1); break;
                    case 19:
                        volumen1 = suma(diaTrabajoId, 19, 20, objPadre, txtDet, ind1); break;
                    case 24:
                        volumen1 = suma(diaTrabajoId, 24, 25, objPadre, txtDet, ind1); break;
                    case 26:
                        volumen1 = suma(diaTrabajoId, 26, 27, objPadre, txtDet, ind1); break;
                    case 28:
                        volumen1 = suma(diaTrabajoId, 28, 29, objPadre, txtDet, ind1); break;
                    default:
                        volumen1 = SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, detalle, ind1); break;
                }
                
                def (porcBrix, densidad, factor, eq) = [0, 0, 0, 0]
                if (material.campo=="tol50K1" || material.campo=="tol50K2" || material.campo=="tolFam"){
                    porcBrix = 100 - SqlUtil.instance.getValorCampo(diaTrabajoId, "AzucarGranel", "humedad")
                    densidad = 906.152
                    factor   = 1
                }else{
                    porcBrix = SqlUtil.instance.getDetValorPorDTI(diaTrabajoId, objPadre, detalle, "Brix")
                    // TODO: Validar
                    eq       = (int)new TablaBxEq().getEq(porcBrix+1)
                    factor   = new FactorVolumen().getValor(temp, eq+1)
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
                // myMap.'firstName' = 'John'
                retorno."eq"       = eq       ?: 0
                retorno."volumen1" = volumen1 ?: 0
                retorno."volumen2" = volumen2 ?: 0
                retorno."peso"     = peso     ?: 0
                retorno."porcBrix" = porcBrix ?: 0
                retorno."tonBrix"  = tonBrix  ?: 0
                retorno."porcSac"  = porcSac  ?: 0
                retorno."tonSac"   = tonSac   ?: 0
                retorno."pureza"   = pureza   ?: 0
                retorno."densidad" = densidad ?: 0
                retorno."factor"   = factor   ?: 0
            }
        }
        
        return retorno
    }

    // No obtengo los valores, solo disparo la acción para realizar los cálculos
    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        def materialId   = (String)getView().getValue("material.id")
        def temp         = getView().getValue("temp")
        // def eq           = getView().getValue("eq")
                
        def mapa = getMapa(diaTrabajoId, materialId, temp)
        mapa.each{ getView().setValue(it.key, it.value ?: null) } 

        // Asignar valores en los campos de texto
        // getView().setValue("volumen1" , volumen1?:null)
        // getView().setValue("volumen2" , volumen2?:null)
        // getView().setValue("peso"     , peso?:null)
        // getView().setValue("porcBrix" , porcBrix?:null)
        // getView().setValue("tonBrix"  , tonBrix?:null)
        // getView().setValue("porcSac"  , porcSac?:null)
        // getView().setValue("tonSac"   , tonSac?:null)
        // getView().setValue("pureza"   , pureza?:null)
        // getView().setValue("densidad" , densidad?:null)
        // getView().setValue("factor"   , factor?:null)
    }
}
