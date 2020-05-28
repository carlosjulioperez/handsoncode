package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@MappedSuperclass
@View(members="""hora;bri,pol,sac,pur,bri2""")
class MasasDetalle extends Identifiable {

    @Stereotype("DATETIME") @Required
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
