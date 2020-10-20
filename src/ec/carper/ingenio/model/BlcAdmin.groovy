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
    titCalFab { detalle12 }
    titConSerInsFab { detalle13 }
""")
class BlcAdmin extends Formulario {
    
    @Column(length=10) @ReadOnly
    String descripcion 
    
    @DisplaySize(20)
    String getDescripcionDiaTrabajo(){
        return diaTrabajo.descripcion
    }

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @CollectionView("Simple")
    @ListProperties("orden,material.descripcion,valor,unidad.descripcion")
    @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle1>detalle1
    
    @CollectionView("Simple")
    @ListProperties("orden,indicador.descripcion;unidades,unidad.descripcion,modificable")
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle12> detalle12

    @CollectionView("Simple")
    @ListProperties("orden,indicador.descripcion;unidades,unidad.descripcion")
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle13> detalle13
}
