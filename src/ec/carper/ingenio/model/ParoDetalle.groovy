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
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp fechaInicio

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp fechaFin

    @Column(length=8)
    @Depends("fechaInicio,fechaFin") //Propiedad calculada
    String getCalParo(){
        if (fechaInicio!=null && fechaFin!=null ) {
            long startTime = fechaInicio.getTime()
            long endTime = fechaFin.getTime()
            
            //log.warn ("Start: ${startTime}, end: ${endTime} ")

            return Util.instance.getDurationAsString(startTime, endTime)
        }
        else return ""
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Area area

    @Column(length=100) @Required
    String descripcion

}
