package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class MasasDetalle5 extends MasasDetalle{
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Masas masas

}
