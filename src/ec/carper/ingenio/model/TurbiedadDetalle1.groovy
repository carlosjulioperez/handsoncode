package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.model.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    hora, abs900Nm, turJClaro
""")
class TurbiedadDetalle1 extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Turbiedad turbiedad

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(TurbiedadDetalle1Action.class)
    @Digits(integer=4, fraction=3) 
    BigDecimal abs900Nm
    
    @ReadOnly
    BigDecimal turJClaro
    
    BigDecimal getValorTurJClaro(String diaTrabajoId, java.sql.Timestamp hora){
        BigDecimal valor = 0

        Query query = getManager().createQuery("SELECT turJClaro FROM TurbiedadDetalle1 WHERE turbiedad.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        List records = query.resultList
        valor = records ? records[0]: 0
        return valor
    }
    
} 
