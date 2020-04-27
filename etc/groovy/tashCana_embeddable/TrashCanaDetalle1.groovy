package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Embeddable
@View(members="""
    hora;
    modulo,turno,variedad;
    cantidadCana;netaCana;calTrashCana;calPorcTrash 
""")
class TrashCanaDetalle1 {
    
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

    @Digits(integer=4, fraction=3)
    BigDecimal valTrashCana
    
    @Digits(integer=4, fraction=3)
    BigDecimal valPorcTrash
    
    @Digits(integer=4, fraction=3)
    @Depends("cantidadCana,netaCana") //Propiedad calculada
    BigDecimal getCalTrashCana(){
        def valor = (cantidadCana && netaCana) ? cantidadCana - netaCana : 0
        setValTrashCana(valor)
        return valor
    }
    
    @Digits(integer=4, fraction=3)
    @Depends("cantidadCana,calTrashCana") //Propiedad calculada
    BigDecimal getCalPorcTrash(){
        def valor = (cantidadCana && calTrashCana) ? ((calTrashCana / cantidadCana)*100).setScale(3, BigDecimal.ROUND_HALF_UP): 0
        setValPorcTrash(valor)
        return valor
    }

}
