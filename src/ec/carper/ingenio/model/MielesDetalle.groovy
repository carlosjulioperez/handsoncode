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
    mfBri, mfPol, mfSac, mfPur, mfBri2;
    mrBri, mrPol, mrSac, mrPur, mrBri2;
    mpBri, mpPol, mpSac, mpPur, mpBri2
""")
class MielesDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Mieles mieles

    @Stereotype("TIME") @Column(length=5) @OnChange(MielesDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal maBri
    @OnChange(MielesDetalleAction.class)
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
    
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mbBri
    @OnChange(MielesDetalleAction.class)
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
    
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mfBri
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mfPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mfSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mfPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mfBri2
    
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mrBri
    @OnChange(MielesDetalleAction.class)
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
    
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mpBri
    @OnChange(MielesDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal mpPol
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mpSac
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mpPur
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal mpBri2
    
}
