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
    cantidadCana;netaCana;
    cogollos, calPorcCogollos;
    valTrashCana;valPorcTrash 
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

    BigDecimal cogollos
    
    BigDecimal valPorcCogollos

    @Depends("cogollos") //Propiedad calculada
    BigDecimal getCalPorcCogollos(){
        def valor = (cogollos) ? (cogollos*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP): 0
        setValPorcCogollos(valor)
        return valor
    }

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal valTrashCana
    
    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal valPorcTrash
    
}
