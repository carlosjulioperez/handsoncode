package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="orden,material")
class StockProcesoPDetalle2 extends Identifiable{
   
    @ManyToOne
    StockProcesoP stockProcesoP 

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Material material
    
}
