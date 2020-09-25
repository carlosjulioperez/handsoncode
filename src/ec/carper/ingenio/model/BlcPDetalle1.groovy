package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="unidad2;modificable", extendsView="super.DEFAULT")
class BlcPDetalle1 extends BlcPDetalle{
   
    @ManyToOne
    BlcP blcP
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad2
    
    boolean modificable

}
