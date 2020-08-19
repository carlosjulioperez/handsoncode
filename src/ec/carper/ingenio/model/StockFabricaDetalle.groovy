package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
@View(members="#orden,indicador,unidad;valor,modificable")
class StockFabricaDetalle extends Identifiable{
   
    @ManyToOne
    StockFabrica stockFabrica

    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Indicador indicador
    
    @OnChange(StockFabricaDetalleAction.class) @DisplaySize(5)
    BigDecimal valor 
 
    @ReadOnly
    boolean modificable
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Unidad unidad
    
}
