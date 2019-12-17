package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
class Paro extends Identifiable{

    @Version
    private Integer version;

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    @Column(length=100) @Required
    String observaciones 

    @ElementCollection
    @ListProperties("""
        area,descripcion,fechaInicio,fechaFin,
        calParo[paro.sumaParo]
    """)
    Collection<ParoDetalle>detalles
    
    String totalParo
    
    String getSumaParo(){
        if ( detalles.size() > 0 ) {
            def timeList = []
            detalles.each { timeList << it.calParo }
            def duration = Util.instance.getDuration(timeList)
            def valor = Util.instance.toTimeString(duration)
            println( valor )
            return valor
        }else return ""
    }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcularTotalParo()
    }

    @PreUpdate
    void recalcularTotalParo() {
        //println ("++++++++++ Total de paro: " + totalParo )
        setTotalParo(getSumaParo())

    }
}
