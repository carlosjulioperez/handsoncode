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
    horaS, hora;
    ini, fin, tot;
    brixJDil, p, tonJugo
""")
class FlujoJugoDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    FlujoJugo flujoJugo
    
    @Stereotype("TIME") @Column(length=5) @OnChange(FlujoJugoDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DisplaySize(6)
    Integer ini
    
    @DisplaySize(6)
    Integer fin 
    
    @DisplaySize(6) @ReadOnly
    Integer tot
    
    @DisplaySize(6) @ReadOnly
    BigDecimal brixJDil
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal p

    @Digits(integer=12, fraction=6) @DisplaySize(6) @ReadOnly
    BigDecimal tonJugo
    
}
