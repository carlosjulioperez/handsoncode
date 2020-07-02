package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@MappedSuperclass
@View(members="""horaS,hora;bri,pol,sac,pur,bri2""")
class MasasDetalle extends Identifiable {

    @Stereotype("TIME") @OnChange(MasasDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(MasasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal bri
    @OnChange(MasasDetalleAction.class)
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
