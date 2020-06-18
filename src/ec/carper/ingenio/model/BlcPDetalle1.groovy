package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="orden,material;unidad,unidad2")
class BlcPDetalle1 extends Identifiable{
   
    @ManyToOne
    BlcP blcP
   
    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Material material
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad2
    
}
