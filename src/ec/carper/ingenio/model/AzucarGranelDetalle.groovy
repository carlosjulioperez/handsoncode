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
    pol, humedad;
    numSaco, polReproc
""")
class AzucarGranelDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    AzucarGranel azucarGranel

    @Stereotype("TIME") @Column(length=5) @OnChange(AzucarGranelDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DisplaySize(6) //@ReadOnly
    BigDecimal briCorr

    @OnChange(AzucarGranelDetalleAction.class) @DisplaySize(6)
    BigDecimal bri

    @OnChange(AzucarGranelDetalleAction.class) @DisplaySize(6)
    @Digits(integer=2, fraction=3)
    BigDecimal absFiltrada

    @OnChange(AzucarGranelDetalleAction.class) @DisplaySize(6)
    @Digits(integer=2, fraction=3)
    BigDecimal absSinFiltrar

    @OnChange(AzucarGranelDetalleAction.class) @DisplaySize(6)
    BigDecimal celda

    @DisplaySize(6) //@ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal rho

    @DisplaySize(6) //@ReadOnly
    @Digits(integer=2, fraction=6)
    BigDecimal cedilla

    @DisplaySize(6) //@ReadOnly
    BigDecimal briEle

    @DisplaySize(6) //@ReadOnly
    BigDecimal color

    @DisplaySize(6) //@ReadOnly
    BigDecimal turb
    
    @DisplaySize(6)
    BigDecimal pol
    
    @DisplaySize(6)
    BigDecimal humedad
    
    @DisplaySize(6)
    BigDecimal numSaco
    
    @DisplaySize(6)
    BigDecimal polReproc
    
}
