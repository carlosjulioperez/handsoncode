package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class BlcDetalle101 extends BlcDetalle{
    @ManyToOne
    Blc blc
}
