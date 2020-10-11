package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@Table(name="blc")
@Tab(properties="diaTrabajo.descripcion, descripcion")
@View(members="""
    descripcionDiaTrabajo, descripcion;
    titDatDia { detalle1 }
""")
class BlcAdmin extends Formulario {
    
    @Column(length=10) @ReadOnly
    String descripcion 
    
    @DisplaySize(10)
    String getDescripcionDiaTrabajo(){
        return diaTrabajo.descripcion
    }

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @CollectionView("Simple")
    @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle1>detalle1
    
}
