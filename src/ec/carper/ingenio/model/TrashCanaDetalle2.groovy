package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    hora;
    mlReductores;
    calTab7SusRed;
    calPorcAzuRed;
""")
class TrashCanaDetalle2 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TrashCana trashCana  

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(TrashCanaDetalle2Action.class)
    BigDecimal mlReductores
    
    @ReadOnly
    BigDecimal calTab7SusRed
    
    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal calPorcAzuRed

    BigDecimal getPorcAzuRed(DiaTrabajo diaTrabajo, java.sql.Timestamp hora){
        BigDecimal valor = 0
        Query query = getManager().createQuery("select o.calPorcAzuRed from TrashCanaDetalle2 o where o.trashCana.diaTrabajo= :diaTrabajo and o.hora <= :hora order by o.hora desc")
        query.setParameter("diaTrabajo" , diaTrabajo)
        query.setParameter("hora"       , hora)

        List records = query.getResultList()
        valor = records ? records[0]: 0
        return valor
    }

}
