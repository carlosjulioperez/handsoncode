package ec.carper.ingenio.util

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

    // =(E6/G6)*100
    BigDecimal getPur(BigDecimal sac, BigDecimal bri, int escala){
        return ((sac/bri)*100).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal getBrix(BigDecimal brixExtracto, BigDecimal wH2O, BigDecimal wCana, BigDecimal porcHumedad, int escala){
        return (brixExtracto*(wH2O-0.25*wCana+0.0125*wCana*porcHumedad)/wCana/(1-0.0125*brixExtracto)).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

    BigDecimal getPorcFibra(BigDecimal porcHumedad, BigDecimal brix){
        return (100 - porcHumedad - brix)
    }

    BigDecimal getPorcSacarosa(BigDecimal polReal, BigDecimal wCana, BigDecimal wH2O , BigDecimal porcFibra, int escala){
        return (polReal*(wCana + wH2O - 0.0125*porcFibra*wCana)/wCana).setScale(escala, BigDecimal.ROUND_HALF_UP)
    }

}
