package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@MappedSuperclass
@View(members="""#
    bri,absFiltrada;
    absSinFiltrar,celda;
    cedilla,rho;
    color,turb
""")
class ColorMatDetalle extends Identifiable {

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=10, fraction=2)
    BigDecimal bri

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=10, fraction=3)
    BigDecimal absFiltrada

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=10, fraction=3)
    BigDecimal absSinFiltrar

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=10, fraction=2)
    BigDecimal celda

    @ReadOnly
    @Digits(integer=10, fraction=6)
    BigDecimal cedilla
    
    @ReadOnly
    @Digits(integer=10, fraction=6)
    BigDecimal rho
    
    @ReadOnly
    @Digits(integer=10, fraction=2)
    BigDecimal color

    @ReadOnly
    @Digits(integer=10, fraction=2)
    BigDecimal turb

}
