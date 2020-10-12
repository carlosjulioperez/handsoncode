package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    detalle { detalle }
""")
class BlcCenicanaP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcCenicanaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcCenicanaPDetalle> detalle

}

