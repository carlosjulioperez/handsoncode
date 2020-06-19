package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate
import java.time.format.*

@Entity
class DiaTrabajo extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    @Hidden
    String getDescripcion(){
        return numeroDia+" "+fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
    
    @Column(length=2) @Required
    int numeroDia
    
    @Required
    boolean cerrado

}
