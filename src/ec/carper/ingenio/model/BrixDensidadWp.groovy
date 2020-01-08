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
class BrixDensidadWp extends Identifiable {

    //@Digits(integer=2, fraction=2)
    @Required
    BigDecimal w
    
    //@Digits(integer=4,fraction=3)
    @Required
    BigDecimal p

    BigDecimal getP (BigDecimal w){
        BigDecimal valor = 0
        Query query = getManager().createQuery("select o.p from BrixDensidadWp o where o.w <= :w order by o.w desc")
        query.setParameter("w", w)

        List records = query.getResultList()
        println records
        valor = records ? records[0]: 0
        //return  records.isEmpty() ? BigDecimal.ZERO : (BigDecimal) records.get(0)
        return valor
    }


}