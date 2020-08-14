package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class BlcDetalle7 extends BlcDetalle{
    @ManyToOne
    Blc blc
}
