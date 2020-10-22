package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class BlcPDetalle16 extends Identifiable{
   
    @ManyToOne
    BlcP blcP

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Material material    
    
}
