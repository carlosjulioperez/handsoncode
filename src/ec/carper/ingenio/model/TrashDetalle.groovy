package ec.carper.ingenio.model

import ec.carper.ingenio.calculators.*

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora;
    modulo,turno,variedad;
    cantidadCana;netaCana;
    cogollos     , calPorcCogollos;
    hojas        , calPorcHojas;
    cepa         , calPorcCepa;
    canaSeca     , calPorcCanaSeca;
    suelo        , calPorcSuelo;
    otros        , calPorcOtros;
    calTrashCana , calPorcTrash;
    canaInfectada, calPorcCanaInfectada
""")
class TrashDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Trash trash

    @Stereotype("TIME") @Column(length=5) @OnChange(TrashDetalleAction.class) @ReadOnly @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora

    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Modulo modulo

    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Turno turno
    
    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Variedad variedad

    @ReadOnly @DisplaySize(6)
    BigDecimal cantidadCana
    
    @ReadOnly @DisplaySize(6)
    BigDecimal netaCana

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    @DefaultValueCalculator(CeroCalculator.class)
    BigDecimal cogollos
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcCogollos

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal hojas
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcHojas

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal cepa
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcCepa

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal canaSeca
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcCanaSeca

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal suelo
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcSuelo

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal otros
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcOtros

    @OnChange(TrashDetalleAction.class) @DisplaySize(6)
    BigDecimal canaInfectada
    @ReadOnly @DisplaySize(6)
    BigDecimal calPorcCanaInfectada

//==============

    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal calTrashCana
    
    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal calPorcTrash
    
}
