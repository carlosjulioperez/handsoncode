package ec.carper.ingenio.model

import java.math.BigDecimal
import java.util.List
import javax.persistence.Entity
import javax.persistence.Query
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Entity
class BrixDensidadTitSus extends Identifiable {

    @Required
    BigDecimal titulacion
    
    @Required
    BigDecimal susRed

    BigDecimal getSusRed (BigDecimal titulacion){
        BigDecimal valor = 0
        Query query = getManager().createQuery("select o.susRed from BrixDensidadTitSus o where o.titulacion <= :titulacion order by o.titulacion desc")
        query.setParameter("titulacion", titulacion)

        List records = query.getResultList()
        valor = records ? records[0]: 0
        return valor
    }
}
