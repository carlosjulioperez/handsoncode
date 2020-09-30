package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="orden,granel;k5,k2,k1;g500,g250,arroba")
class BlcDetalle11 extends Identifiable{
    
    @ManyToOne
    Blc blc
    
    @Column(length=2)
    int orden
   
    @DisplaySize(5)
    BigDecimal granel
   
    @DisplaySize(5)
    BigDecimal k5
   
    @DisplaySize(5)
    BigDecimal k2
   
    @DisplaySize(5)
    BigDecimal k1
   
    @DisplaySize(5)
    BigDecimal g500
   
    @DisplaySize(5)
    BigDecimal g250
   
    @DisplaySize(5)
    BigDecimal arroba
    
}
