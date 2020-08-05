package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="cantidad,unidad2", extendsView="super.DEFAULT")
class BlcDetalle1 extends BlcDetalle{
    
    @ManyToOne
    Blc blc
    
    @DisplaySize(5)
    BigDecimal cantidad 

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad2
    
}
