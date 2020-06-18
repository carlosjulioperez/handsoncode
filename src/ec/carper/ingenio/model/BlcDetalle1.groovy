package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="material;valor,unidad;cantidad,unidad2")
class BlcDetalle1 extends Identifiable{
    
    @ManyToOne
    Blc blc
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Material material
   
    @DisplaySize(5)
    BigDecimal valor 

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad
    
    @DisplaySize(5)
    BigDecimal cantidad 

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad2
    
}
