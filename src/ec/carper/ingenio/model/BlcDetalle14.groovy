package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class BlcDetalle14 extends Identifiable{
   
    @ManyToOne
    Blc blc

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Material material    

    BigDecimal azucarReductor

    BigDecimal azucaresTotRed
    
    BigDecimal sacarosaTeoRed
}
