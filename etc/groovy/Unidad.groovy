package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Unidad {

	@Id @Column(length=3) @ReadOnly
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	short id

    @Column(length=10) @Required
    String descripcion

}
