package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Unidad extends Identifiable{

    @Column(length=10) @Required
    String descripcion

}
