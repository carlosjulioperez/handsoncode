package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
// @View(members="orden;material;unidad")
class BlcCenicanaPDetalle extends Identifiable{
   
    @ManyToOne
    BlcCenicanaP blcCenicanaP

    @Column(length=3)
    int orden
    
    @Column(length=4)
    String peso 
    
    @Column(length=4)
    String porcC
    
    @Column(length=40) @Required
    String descripcion
    
}
