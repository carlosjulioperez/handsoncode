package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
class Paro extends Identifiable{

    // @Version
    // private Integer version; // Añadida propiedad 'version', sin getter, ni setter

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    @Column(length=100) @Required
    String observaciones 

    @ElementCollection
    @ListProperties("""fechaInicio,fechaFin,calParo,area,descripcion""")
    Collection<ParoDetalle>detalles
}
