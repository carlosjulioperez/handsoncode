package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import java.time.LocalDate
import javax.persistence.*
import org.apache.commons.logging.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Embeddable
class ParoDetalle{

    private static Log log = LogFactory.getLog(ParoDetalle.class)

    // https://github.com/mariuszs/openxava/blob/master/source/src/test/java/org/openxava/test/model/Clerk.java
    
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fechaInicio

    @Column(length=8) @Stereotype("TIME") @Required
    String horaInicio 

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fechaFin

    @Column(length=8) @Stereotype("TIME") @Required
    String horaFin

    @Depends("fechaInicio,horaInicio,fechaFin,horaFin") //Propiedad calculada
    @Column(length=8) @Required
    String getCalTotalParo(){
        if (fechaInicio!=null && horaInicio!=null && fechaFin!=null && horaFin!=null) {
            def starString = fechaInicio.toString() + " " + horaInicio
            def endString  = fechaFin.toString() + " " + horaFin
            
            log.warn ("Start: ${starString}, end: ${endString} ")

            return Util.instance.getDurationAsString(starString, endString)
        }
        else return ""
    }
    
    String totalParo

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Area area

    @Column(length=100) @Required
    String descripcion

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcularTotalParo()
    }

    public void recalcularTotalParo() {
        setTotalParo(getCalTotalParo())
    }

}

