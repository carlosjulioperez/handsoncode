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
    color, turb;
    pol, humedad
""")
class GrasshoperDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Grasshoper grasshoper

    @Stereotype("TIME") @Column(length=5) @OnChange(GrasshoperDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @ReadOnly @DisplaySize(6)
    BigDecimal briCorr

    @OnChange(GrasshoperDetalleAction.class) @DisplaySize(6)
    BigDecimal bri

    @OnChange(GrasshoperDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(6)
    BigDecimal absFiltrada

    @OnChange(GrasshoperDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(6)
    BigDecimal absSinFiltrar

    @OnChange(GrasshoperDetalleAction.class) @DisplaySize(6)
    BigDecimal celda

    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal rho

    @ReadOnly
    @Digits(integer=2, fraction=6) @DisplaySize(6)
    BigDecimal cedilla

    @ReadOnly @DisplaySize(6)
    BigDecimal briEle

    @ReadOnly @DisplaySize(6)
    BigDecimal color

    @ReadOnly @DisplaySize(6)
    BigDecimal turb
    
    @DisplaySize(6)
    BigDecimal pol
    
    @DisplaySize(6)
    BigDecimal humedad
}
