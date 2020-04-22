package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo.descripcion""")
//@View(members=  """diaTrabajo;detalle1""")
class Trash extends DiaTrabajoEditable {
}

