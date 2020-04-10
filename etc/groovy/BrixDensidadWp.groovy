package ec.carper.ingenio.model

import java.math.BigDecimal

import javax.persistence.*
import javax.persistence.Query
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Entity
class BrixDensidadWp extends Identifiable {

    @Required
    BigDecimal w
    
    @Digits(integer=4,fraction=3) @Required
    BigDecimal p

    BigDecimal getP(BigDecimal w) throws Exception{
        Query query = getManager()
            .createQuery("select o.p from BrixDensidadWp o where o.w <= :w order by o.w desc")
        query.setParameter("w", w)
        
        def lista = query.getResultList()
        return (BigDecimal) (lista.isEmpty() ? 0 : lista[0])
    }
}
