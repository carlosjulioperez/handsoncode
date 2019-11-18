package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
class InicioZafra extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fechaInicio
    
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    LocalDate fechaFin

    @Required
    int numeroZafra

}
