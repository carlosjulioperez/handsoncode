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
    mcrBri, mcrPol, mcrSac, mcrPur, mcrBri2;
    mclBri, mclPol, mclSac, mclPur, mclBri2
""")
class MeladuraDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Meladura meladura

    @Stereotype("TIME") @Column(length=5) @OnChange(MeladuraDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(MeladuraDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mcrBri
    @OnChange(MeladuraDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mcrPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcrSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcrPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcrBri2
    
    @OnChange(MeladuraDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mclBri
    @OnChange(MeladuraDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mclPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mclSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mclPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mclBri2
    
}
