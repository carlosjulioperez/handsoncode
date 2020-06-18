package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="codigo,descripcion;detalle1")
class BlcP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL)
    Collection<BlcPDetalle1>detalle1
}

