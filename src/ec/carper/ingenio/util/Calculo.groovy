package ec.carper.ingenio.util

import groovy.time.TimeCategory

@Singleton
class Calculo{

    // --------------------------------------------------
    // Usado en: 
    // JugoDetalleAction
    // MasasDetalle1Action, MasasDetalle2Action, MasasDetalle3Action, 
    // MeladuraDetalleAction OK
    // BagazoDetalleAction

    // =(D7*0,26)/(0,9971883+0,00385310413*C7+0,0000132218495*C7*C7+0,00000004655189*C7*C7*C7)
    BigDecimal getSac(BigDecimal bri, BigDecimal pol, int factor, int escala){
        return (((pol*0.26)/(0.9971883+0.00385310413*bri+0.0000132218495*bri*bri+0.00000004655189*bri*bri*bri))*factor).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    // =(a/b)*100 o =(a*100)/b
    BigDecimal getPorc(BigDecimal valor1, BigDecimal valor2, int escala){
        // println ">>> valor1: " + valor1
        // println ">>> valor2: " + valor2
        return ((valor1/valor2)*100).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }
    // =+(a/100)*b
    BigDecimal getPorc2(BigDecimal valor1, BigDecimal valor2, int escala){
        // println ">>> valor1: " + valor1
        // println ">>> valor2: " + valor2
        return ((valor1/100)*valor2).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal getBrix(BigDecimal brixExtracto, BigDecimal wH2O, BigDecimal wMaterial, BigDecimal porcHumedad, int escala){
        return (brixExtracto*(wH2O-0.25*wMaterial+0.0125*wMaterial*porcHumedad)/wMaterial/(1-0.0125*brixExtracto)).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal getPorcFibra(BigDecimal porcHumedad, BigDecimal brix){
        return (100 - porcHumedad - brix)
    }

    BigDecimal getPorcSacarosa(BigDecimal polReal, BigDecimal wCana, BigDecimal wH2O , BigDecimal porcFibra, int escala){
        return (polReal*(wCana + wH2O - 0.0125*porcFibra*wCana)/wCana).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal getPromedio(def lista, int escala){
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(escala, BigDecimal.ROUND_HALF_UP) : 0
    }

    // =+(1000*D7)/(F7*G7)
    BigDecimal getCedilla(BigDecimal bri, BigDecimal rho, int escala){
        return ((bri*rho)/100000).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    // =+((1000*E7)/(F7*G7))-I7
    BigDecimal getColor(BigDecimal abs, BigDecimal celda, BigDecimal cedilla, int escala){
        return ((1000*abs)/(celda*cedilla)).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal redondear(BigDecimal numero, int escala){
        return numero.setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal porcCon(def valor1, def valor2){
        return redondear((valor2-valor1)/valor2*100, 2)
    }
    
    BigDecimal getFraccionTiempo(def hora){
        def valor = 0
        try{
            def date = Date.parse("HH:mm:ss",hora) 
            valor = redondear(date.hours + date.minutes/60, 2)
        }catch(Exception e){}
        return valor 
    }
}
