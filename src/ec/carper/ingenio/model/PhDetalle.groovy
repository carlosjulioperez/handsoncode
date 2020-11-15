package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora;
    j1Extracto, jDiluido;
    jEncalado, jClaro;
    jFiltrado, mCruda;
    mClarificada;
    horaSTJClaro, horaTJClaro;
    tJClaro
""")
class PhDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Ph ph
    
    @Stereotype("TIME") @Column(length=5) @OnChange(PhDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DisplaySize(6)
    BigDecimal j1Extracto  
    
    @DisplaySize(6)
    BigDecimal jDiluido    
    
    @DisplaySize(6)
    BigDecimal jEncalado   
    
    @DisplaySize(6)
    BigDecimal jClaro      
    
    @DisplaySize(6)
    BigDecimal jFiltrado   
    
    @DisplaySize(6)
    BigDecimal mCruda      
    
    @DisplaySize(6)
    BigDecimal mClarificada
    
    @Stereotype("TIME") @Column(length=5) @OnChange(PhDetalleAction.class)
    String horaSTJClaro

    @Stereotype("DATETIME") @ReadOnly
    java.sql.Timestamp horaTJClaro

    @ReadOnly @DisplaySize(6)
    BigDecimal tJClaro     
}
