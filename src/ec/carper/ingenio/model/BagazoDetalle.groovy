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
    hora, wH2O;
    wBagazo, polReal;
    brixExtracto, polExtracto;
    tamizVacioM0, muestraHumM1;
    muestraSecaM2, porcHumedad;
    brix, porcFibra;
    porcSacarosa;
    horaPorcSacJR, porcSacJR;
    gradosAguaMac
""")
class BagazoDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Bagazo bagazo
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    @OnChange(BagazoDetalleAction.class)
    BigDecimal wH2O

    @OnChange(BagazoDetalleAction.class)
    BigDecimal wBagazo

    @ReadOnly
    BigDecimal polReal

    @OnChange(BagazoDetalleAction.class)
    BigDecimal brixExtracto
    
    @OnChange(BagazoDetalleAction.class)
    BigDecimal polExtracto

    @OnChange(BagazoDetalleAction.class)
    BigDecimal tamizVacioM0

    @ReadOnly
    BigDecimal muestraHumM1
    
    @OnChange(BagazoDetalleAction.class)
    BigDecimal muestraSecaM2

    @ReadOnly
    BigDecimal porcHumedad

    @ReadOnly
    BigDecimal brix
    
    @ReadOnly
    BigDecimal porcFibra
    
    @ReadOnly
    BigDecimal porcSacarosa
    
    @Stereotype("DATETIME")
    @OnChange(BagazoDetalleAction.class)
    java.sql.Timestamp horaPorcSacJR

    @ReadOnly
    BigDecimal porcSacJR
    
    BigDecimal gradosAguaMac

}
