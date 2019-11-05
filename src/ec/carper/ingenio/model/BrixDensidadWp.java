package ec.carper.ingenio.model;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.*;
import org.openxava.model.*;

@Entity
public class BrixDensidadWp extends Identifiable{

    @Digits(integer=2,fraction=2) @Required @Getter @Setter
    private BigDecimal w;
    
    @Digits(integer=4,fraction=3) @Required @Getter @Setter
    private BigDecimal p;

    public BigDecimal getP(BigDecimal w){
        Query query = getManager().
            createQuery("select o.p from BrixDensidadWp o where o.w <= :w order by o.w desc");
        query.setParameter("w", w);
        
        List records = query.getResultList();
        if (records.isEmpty())
            return new BigDecimal(0);
        else
            return (BigDecimal) records.get(0);
    }
}
