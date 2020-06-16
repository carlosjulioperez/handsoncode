package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    hora;
    modulo,turno,variedad;
    cantidadCana,netaCana;calTrashCana,calPorcTrash 
""")
class TrashCanaDetalle1 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TrashCana trashCana  

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Modulo modulo

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Turno turno
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Variedad variedad

    @OnChange(TrashCanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal cantidadCana
    
    @OnChange(TrashCanaDetalle1Action.class)
    @Digits(integer=4, fraction=1) @DisplaySize(6)
    BigDecimal netaCana

    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal calTrashCana
    
    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal calPorcTrash
    
}
