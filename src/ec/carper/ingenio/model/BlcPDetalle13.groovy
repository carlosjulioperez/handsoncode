package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="orden;indicador;unidad")
class BlcPDetalle13 extends Identifiable{
   
    @ManyToOne
    BlcP blcP

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Indicador indicador
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad
    
}
