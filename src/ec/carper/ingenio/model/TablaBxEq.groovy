package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Entity
class TablaBxEq extends Identifiable {

    @Required @ReadOnly
    BigDecimal bx
    
    @Required @ReadOnly
    BigDecimal eq

    BigDecimal getEq (BigDecimal bx){
        Query query = getManager().createQuery("select o.eq from TablaBxEq o where o.bx <= :bx order by o.bx desc")
        query.setParameter("bx", bx)
        query.setMaxResults(1)

        return query.resultList[0]?: 0
    }
}
