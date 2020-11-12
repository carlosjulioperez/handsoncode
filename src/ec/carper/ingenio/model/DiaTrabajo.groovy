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
        // println ">>> DiaTrabajo.valor = ${this}"
        def valor = ""
        try{
            valor = numeroDia+" "+fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }catch(Exception e){}
        return valor 
    }
    
    @Column(length=2) @Required
    int numeroDia
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    TurnoTrabajo turnoTrabajo
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    Zafra zafra

    @Required
    boolean cerrado

}
