package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@Views([
    @View(members="orden,material;valor,unidad;cantidad,unidad2;acumulado,modificable"),
    @View(name="Simple", members="orden,material;valor,unidad")
])
class BlcDetalle1 extends Identifiable{
    
    @ManyToOne
    Blc blc
    
    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Material material
    
    @OnChange(BlcDetalle1Action.class) @DisplaySize(5)
    BigDecimal valor 
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad
    
    @DisplaySize(5) @ReadOnly
    BigDecimal cantidad 

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad2
    
    @DisplaySize(5) @ReadOnly
    BigDecimal acumulado 
    
    @ReadOnly
    boolean modificable
    
}
