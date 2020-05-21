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
    jdAbsorbancia, jdMlMuestra, jdMgP, jdFosfatos;
    jcAbsorbancia, jcMlMuestra, jcMgP, jcFosfatos
""")
class FosfatosDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Fosfatos fosfatos

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(FosfatosDetalleAction.class)
    @Digits(integer=3, fraction=3)
    BigDecimal jdAbsorbancia
    
    @OnChange(FosfatosDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal jdMlMuestra
    
    @ReadOnly
    @Digits(integer=3, fraction=3)
    BigDecimal jdMgP
    
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal jdFosfatos
    
    @OnChange(FosfatosDetalleAction.class)
    @Digits(integer=3, fraction=3)
    BigDecimal jcAbsorbancia
    
    @OnChange(FosfatosDetalleAction.class)
    @Digits(integer=3, fraction=2)
    BigDecimal jcMlMuestra
    
    @ReadOnly
    @Digits(integer=3, fraction=3)
    BigDecimal jcMgP
    
    @ReadOnly
    @Digits(integer=3, fraction=2)
    BigDecimal jcFosfatos
    
}
