package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    horaS, hora;
    bri, pol, sac, pur, bri2
""")
class TqFundidorDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TqFundidor tqFundidor

    @Stereotype("TIME") @OnChange(PhDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(TqFundidorDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal bri
    @OnChange(TqFundidorDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal pol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal sac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal pur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal bri2
    
}
