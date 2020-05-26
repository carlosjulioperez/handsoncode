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

}
