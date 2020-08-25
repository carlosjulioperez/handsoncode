package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
@View(members="orden,indicador,valor,unidad,modificable")
class StockFabricaPDetalle extends Identifiable{
   
    @ManyToOne
    StockFabricaP stockFabricaP 

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Indicador indicador
    
    @Digits(integer=6, fraction=3) @DisplaySize(6)
    BigDecimal valor 
    
    boolean modificable
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Unidad unidad
    
}
