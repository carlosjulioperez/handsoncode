package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Embeddable
class BlcDetalle17{
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Area area

    @Column(length=8)
    String totalParo
    
}
