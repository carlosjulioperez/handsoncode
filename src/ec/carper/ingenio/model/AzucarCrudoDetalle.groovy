package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora;
    briCorr, bri;
    absFiltrada, absSinFiltrar;
    celda, rho;
    cedilla, briEle;
    color, turb
""")
class AzucarCrudoDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    AzucarCrudo azucarCrudo

    @Stereotype("TIME") @Column(length=5) @OnChange(AzucarCrudoDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @ReadOnly
    BigDecimal briCorr

    @OnChange(AzucarCrudoDetalleAction.class) @DisplaySize(6)
    BigDecimal bri

    @OnChange(AzucarCrudoDetalleAction.class) @DisplaySize(6)
    @Digits(integer=2, fraction=3)
    BigDecimal absFiltrada

    @OnChange(AzucarCrudoDetalleAction.class) @DisplaySize(6)
    @Digits(integer=2, fraction=3)
    BigDecimal absSinFiltrar

    @OnChange(AzucarCrudoDetalleAction.class) @DisplaySize(6)
    BigDecimal celda

    @ReadOnly @DisplaySize(6)
    @Digits(integer=4, fraction=3)
    BigDecimal rho

    @ReadOnly @DisplaySize(6)
    @Digits(integer=2, fraction=6)
    BigDecimal cedilla

    @ReadOnly @DisplaySize(6)
    BigDecimal briEle

    @ReadOnly @DisplaySize(6)
    BigDecimal color

    @ReadOnly @DisplaySize(6)
    BigDecimal turb
    
}
