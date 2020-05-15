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
    horaDesde, horaHasta;
    polReal, brixExtracto;
    polExtracto, porcHumedad;
    brix, porcFibra;
    porcSacarosa, pureza;
""")
class CanaDetalle2 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cana cana
    
    @OnChange(CanaDetalle2Action.class)
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp horaDesde

    @OnChange(CanaDetalle2Action.class)
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp horaHasta

    @ReadOnly
    BigDecimal polReal
    
    @OnChange(CanaDetalle2Action.class)
    BigDecimal brixExtracto
    
    @OnChange(CanaDetalle2Action.class)
    BigDecimal polExtracto
    
    @ReadOnly
    BigDecimal porcHumedad

    @ReadOnly
    BigDecimal brix
    
    @ReadOnly
    BigDecimal porcFibra
    
    @ReadOnly
    BigDecimal porcSacarosa
    
    @ReadOnly
    BigDecimal pureza
}
