package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class BlcDetalle13 extends Identifiable{
   
    @ManyToOne
    Blc blc

    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Indicador indicador
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal unidades
 
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal acumulado
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal totalZafra
    
}
