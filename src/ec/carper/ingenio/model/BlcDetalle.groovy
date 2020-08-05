package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
@View(members="orden;material;valor,unidad")
class BlcDetalle extends Identifiable{
   
    @ManyToOne
    Blc blc
   
    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Material material
    
    @DisplaySize(5)
    BigDecimal valor 
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad
    
}
