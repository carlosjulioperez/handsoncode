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
    briCorr,bri,absFiltrada,absSinFiltrar,celda;
    rho,cedilla,briEle,color,turb;
    pol,humedad,numSaco,polReproc
""")
class AzucarGranelDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    AzucarGranel azucarGranel

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @ReadOnly
    BigDecimal briCorr

    @OnChange(AzucarGranelDetalleAction.class)
    BigDecimal bri

    @OnChange(AzucarGranelDetalleAction.class)
    @Digits(integer=2, fraction=3)
    BigDecimal absFiltrada

    @OnChange(AzucarGranelDetalleAction.class)
    @Digits(integer=2, fraction=3)
    BigDecimal absSinFiltrar

    @OnChange(AzucarGranelDetalleAction.class)
    BigDecimal celda

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal rho

    @ReadOnly
    @Digits(integer=2, fraction=6)
    BigDecimal cedilla

    @ReadOnly
    BigDecimal briEle

    @ReadOnly
    BigDecimal color

    @ReadOnly
    BigDecimal turb
    
    BigDecimal pol
    
    BigDecimal humedad
    
    BigDecimal numSaco
    
    BigDecimal polReproc
    
}
