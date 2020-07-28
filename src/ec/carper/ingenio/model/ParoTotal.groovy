package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Embeddable
class ParoTotal{

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList //@NoCreate @NoModify
    Area area

    @Column(length=8) //@ReadOnly
    String totalParo

}
