package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    hora, j1Extracto;
    jDiluido, jEncalado;
    jClaro, jFiltrado;
    mCruda, mClarificada;
    horaTJClaro, tJClaro
""")
class PhDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Ph ph
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    BigDecimal j1Extracto  
    BigDecimal jDiluido    
    BigDecimal jEncalado   
    BigDecimal jClaro      
    BigDecimal jFiltrado   
    BigDecimal mCruda      
    BigDecimal mClarificada
    
    @Stereotype("DATETIME")
    @OnChange(PhDetalleAction.class)
    java.sql.Timestamp horaTJClaro

    @ReadOnly
    BigDecimal tJClaro     
}
