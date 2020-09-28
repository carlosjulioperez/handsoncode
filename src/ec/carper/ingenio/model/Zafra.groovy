package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*


@Entity
class Zafra extends Identifiable{
    
    @Column(length=10) @Required
    String codigo 

    @Column(length=50) @Required
    String descripcion

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    DiaTrabajo diaTrabajoInicio
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    DiaTrabajo diaTrabajoFin

}
