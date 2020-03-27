package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

//@View(extendsView="""super.DEFAULT""")
@Entity
class TrashCana extends DiaTrabajoEditable{
    
    @Column(length=50) @Required
    String descripcion

}
