package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class BlcDetalle102 extends BlcDetalle{
    @ManyToOne
    Blc blc
}
