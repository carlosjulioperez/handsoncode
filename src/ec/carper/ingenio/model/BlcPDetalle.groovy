package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
@View(members="orden;material;unidad")
class BlcPDetalle extends Identifiable{
   
    @ManyToOne
    BlcP blcP
   
    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Material material
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad
    
}
