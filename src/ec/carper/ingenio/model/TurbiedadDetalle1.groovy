package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    horaS, hora;
    abs900Nm; turJClaro
""")
class TurbiedadDetalle1 extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Turbiedad turbiedad

    @Stereotype("TIME") @OnChange(TurbiedadDetalle1Action.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(TurbiedadDetalle1Action.class)
    @Digits(integer=4, fraction=3) @DisplaySize(6) 
    BigDecimal abs900Nm
    
    @ReadOnly
    @Digits(integer=4, fraction=2) @DisplaySize(6) 
    BigDecimal turJClaro
    
    BigDecimal getValorTurJClaro(String diaTrabajoId, java.sql.Timestamp hora){

        Query query = getManager().createQuery("SELECT turJClaro FROM TurbiedadDetalle1 WHERE turbiedad.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        return query.resultList[0]?: 0
    }
    
} 
