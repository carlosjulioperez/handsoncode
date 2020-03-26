package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
class DiaTrabajo extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate diaTrabajo
    
    @Column(length=50) @Required
    String descripcion
    
    @Required
    boolean cerrado

}
