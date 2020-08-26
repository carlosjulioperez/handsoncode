package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class StockFabricaDetalle33 extends StockFabricaDetalle{
    @ManyToOne
    StockFabrica stockFabrica
}
