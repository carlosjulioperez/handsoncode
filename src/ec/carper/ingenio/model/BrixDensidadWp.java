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

}
