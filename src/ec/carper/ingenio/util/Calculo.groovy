package ec.carper.ingenio.util

@Singleton
class Calculo{

    // --------------------------------------------------
    // Usado en: 
    // JugoDetalleAction
    // MasasDetalle1Action, MasasDetalle2Action, MasasDetalle3Action, 
    BigDecimal getSac(BigDecimal bri, BigDecimal pol){
        return (pol*0.26)/(0.9971883+0.00385310413*bri+0.0000132218495*bri*bri+0.00000004655189*bri*bri*bri) 
    }

    BigDecimal getPur(BigDecimal sac, BigDecimal bri){
        return (sac/bri)*100
    }

}
