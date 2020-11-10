package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@Tab(properties="codigo, descripcion, diaTrabajoInicio.descripcion")
class Zafra extends Identifiable{
    
    @Column(length=10) @Required
    String codigo 

    @Column(length=50) @Required
    String descripcion

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify @Required
    DiaTrabajo diaTrabajoInicio
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify
    DiaTrabajo diaTrabajoFin

}
