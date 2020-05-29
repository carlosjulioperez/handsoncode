package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    hora;
    mbBri, mbPol, mbSac, mbPur, mbBri2;
    mcBri, mcPol, mcSac, mcPur, mcBri2;
    mrBri, mrPol, mrSac, mrPur, mrBri2
""")
class MagmasDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Magmas magmas

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mbBri
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mbPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mbSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mbPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mbBri2
    
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mcBri
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mcPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mcBri2
    
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mrBri
    @OnChange(MagmasDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mrPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mrSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mrPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mrBri2
    
}
