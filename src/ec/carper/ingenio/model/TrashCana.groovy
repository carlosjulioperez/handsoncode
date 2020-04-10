package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
@View(members=  """diaTrabajo;detalle1;detalle2""")
class TrashCana extends DiaTrabajoEditable{
    
    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana,netaCana,calTrashCana,calPorcTrash
    """)
    Collection<TrashCanaDetalle1>detalle1

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    // @ListProperties("""
    //     hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
    //     cantidadCana,netaCana,calTrashCana,calPorcTrash
    // """)
    Collection<TrashCanaDetalle2>detalle2
}
