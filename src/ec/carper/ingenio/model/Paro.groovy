package ec.carper.ingenio.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*;
import org.openxava.model.*

@Entity
class Paro extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    @Column(length=100) @Required
    String observaciones 

    @ElementCollection
    @ListProperties("""inicioParo,finParo,calTotalParo,area,descripcion""")
    Collection<ParoDetalle>detalles
}
