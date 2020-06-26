package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaSD, horaDesde; 
    horaSH, horaHasta;
    polReal, brixExtracto;
    polExtracto, porcHumedad;
    brix, porcFibra;
    porcSacarosa, pureza;
""")
class CanaDetalle2 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cana cana
    
    @Stereotype("TIME") @OnChange(CanaDetalle2Action.class) @Required
    String horaSD

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp horaDesde

    @Stereotype("TIME") @OnChange(CanaDetalle2Action.class) @Required
    String horaSH

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp horaHasta

    @ReadOnly @DisplaySize(6)
    BigDecimal polReal
    
    @OnChange(CanaDetalle2Action.class) @DisplaySize(6)
    BigDecimal brixExtracto
    
    @OnChange(CanaDetalle2Action.class) @DisplaySize(6)
    BigDecimal polExtracto
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcHumedad

    @ReadOnly @DisplaySize(6)
    BigDecimal brix
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcFibra
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcSacarosa
    
    @ReadOnly @DisplaySize(6)
    BigDecimal pureza
}
