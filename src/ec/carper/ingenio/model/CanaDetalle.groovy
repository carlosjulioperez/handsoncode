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
    hora, wH2O;
    wCana, polReal;
    brixExtracto, polExtracto;
    tamizVacioM0, muestraHumM1;
    muestraSecaM2, porcHumedad;
    brix, porcFibra;
    porcSacarosa, pureza;
""")
class CanaDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cana cana
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    BigDecimal wH2O
    BigDecimal wCana

    @ReadOnly
    BigDecimal polReal

    @OnChange(CanaDetalleAction.class)
    BigDecimal brixExtracto
    
    @OnChange(CanaDetalleAction.class)
    BigDecimal polExtracto

    @OnChange(CanaDetalleAction.class)
    BigDecimal tamizVacioM0

    @ReadOnly
    BigDecimal muestraHumM1
    
    @OnChange(CanaDetalleAction.class)
    BigDecimal muestraSecaM2

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
