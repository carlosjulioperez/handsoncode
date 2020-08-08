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
    maBri, maPol, maSac, maPur, maBri2;
    mbBri, mbPol, mbSac, mbPur, mbBri2;
    mcBri, mcPol, mcSac, mcPur, mcBri2
""")
class MielesNutschDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    MielesNutsch mielesNutsch

    @Stereotype("TIME") @Column(length=5) @OnChange(MielesNutschDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(MielesNutschDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal maBri
    @OnChange(MielesNutschDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal maPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal maSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal maPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal maBri2
    
    @OnChange(MielesNutschDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mbBri
    @OnChange(MielesNutschDetalleAction.class)
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
    
    @OnChange(MielesNutschDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mcBri
    @OnChange(MielesNutschDetalleAction.class)
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
    
}
