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
    hora;
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

    @ReadOnly
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Modulo modulo

    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Turno turno
    
    @ReadOnly
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Variedad variedad

    @ReadOnly
    BigDecimal cantidadCana
    
    @ReadOnly
    BigDecimal netaCana

    @OnChange(TrashDetalleAction.class)
    BigDecimal cogollos
    @ReadOnly
    BigDecimal calPorcCogollos

    @OnChange(TrashDetalleAction.class)
    BigDecimal hojas
    @ReadOnly
    BigDecimal calPorcHojas

    @OnChange(TrashDetalleAction.class)
    BigDecimal cepa
    @ReadOnly
    BigDecimal calPorcCepa

    @OnChange(TrashDetalleAction.class)
    BigDecimal canaSeca
    @ReadOnly
    BigDecimal calPorcCanaSeca

    @OnChange(TrashDetalleAction.class)
    BigDecimal suelo
    @ReadOnly
    BigDecimal calPorcSuelo

    @OnChange(TrashDetalleAction.class)
    BigDecimal otros
    @ReadOnly
    BigDecimal calPorcOtros

    @OnChange(TrashDetalleAction.class)
    BigDecimal canaInfectada
    @ReadOnly
    BigDecimal calPorcCanaInfectada

//==============

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal calTrashCana
    
    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal calPorcTrash
    
}
