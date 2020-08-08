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
    wH2O, wBagazo;
    polReal, brixExtracto;
    polExtracto, tamizVacioM0;
    muestraHumM1, muestraSecaM2;
    porcHumedad, brix;
    porcFibra, porcSacarosa;
    horaSPorcSacJR, horaPorcSacJR;
    porcSacJR, gradosAguaMac
""")
class BagazoDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Bagazo bagazo
    
    @Stereotype("TIME") @Column(length=5) @OnChange(BagazoDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora

    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal wH2O

    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal wBagazo

    @ReadOnly @DisplaySize(6)
    BigDecimal polReal

    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal brixExtracto
    
    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal polExtracto

    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal tamizVacioM0

    @ReadOnly @DisplaySize(6)
    BigDecimal muestraHumM1
    
    @OnChange(BagazoDetalleAction.class) @DisplaySize(6)
    BigDecimal muestraSecaM2

    @ReadOnly @DisplaySize(6)
    BigDecimal porcHumedad

    @ReadOnly @DisplaySize(6)
    BigDecimal brix
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcFibra
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcSacarosa
    
    @Stereotype("TIME") @Column(length=5) @OnChange(BagazoDetalleAction.class)
    String horaSPorcSacJR

    @Stereotype("DATETIME") @ReadOnly
    java.sql.Timestamp horaPorcSacJR

    @ReadOnly @DisplaySize(6)
    BigDecimal porcSacJR
    
    @DisplaySize(6)
    BigDecimal gradosAguaMac

}
