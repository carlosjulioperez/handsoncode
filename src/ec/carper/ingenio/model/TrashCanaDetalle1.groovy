package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@View(members="""
    hora;
    modulo,turno,variedad;
    cantidadCana;netaCana;calTrashCana;calPorcTrash 
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

    BigDecimal cantidadCana
    BigDecimal netaCana
    
    @Depends("cantidadCana,netaCana") //Propiedad calculada
    BigDecimal getCalTrashCana(){
        return (cantidadCana && netaCana) ? cantidadCana - netaCana : 0
    }
    
    @Digits(integer=4, fraction=3)
    @Depends("cantidadCana,calTrashCana") //Propiedad calculada
    BigDecimal getCalPorcTrash(){
        return (cantidadCana && calTrashCana) ? ((calTrashCana / cantidadCana)*100).setScale(3, BigDecimal.ROUND_HALF_UP): 0
    }

}
