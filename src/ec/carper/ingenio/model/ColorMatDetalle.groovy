package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    bri1,absFiltrada1,absSinFiltrar1,celda1,cedilla1,rho1,color1,turb1;
    bri2,absFiltrada2,absSinFiltrar2,celda2,cedilla2,rho2,color2,turb2;
    bri3,absFiltrada3,absSinFiltrar3,celda3,cedilla3,rho3,color3,turb3;
    bri4,absFiltrada4,absSinFiltrar4,celda4,cedilla4,rho4,color4,turb4;
    bri5,absFiltrada5,absSinFiltrar5,celda5,cedilla5,rho5,color5,turb5;
    bri6,absFiltrada6,absSinFiltrar6,celda6,cedilla6,rho6,color6,turb6;
    bri7,absFiltrada7,absSinFiltrar7,celda7,cedilla7,rho7,color7,turb7;
    bri8,absFiltrada8,absSinFiltrar8,celda8,cedilla8,rho8,color8,turb8
""")
class ColorMatDetalle extends Identifiable {
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    ColorMat colorMat

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri1
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada1
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar1
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda1
    @ReadOnly
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla1
    @ReadOnly
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho1
    @ReadOnly
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color1
    @ReadOnly
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb1

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri2                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada2                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar2                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda2                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla2                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho2                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color2                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb2

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri3                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada3                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar3                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda3                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla3                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho3                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color3                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb3

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri4                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada4                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar4                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda4                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla4                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho4                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color4                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb4

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri5                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada5                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar5                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda5                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla5                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho5                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color5                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb5

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri6                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada6                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar6                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda6                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla6                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho6                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color6                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb6

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri7                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada7                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar7                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda7                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla7                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho7                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color7                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb7

    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri8                               
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absFiltrada8                       
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(2)
    BigDecimal absSinFiltrar8                     
    @OnChange(ColorMatDetalleAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda8                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla8                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho8                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color8                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb8

}
