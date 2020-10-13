package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class BlcCenicanaDetalle extends Identifiable{
   
    @ManyToOne
    BlcCenicana blcCenicana

    @Column(length=3)
    int orden
    
    @Column(length=4)
    String peso 
    
    @Column(length=4)
    String porcC
    
    @Column(length=40)
    String descripcion
    
    BigDecimal valor 
}
