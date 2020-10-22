package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    titDatDia { detalle1 }
    titVarPri {
        cana { 
            titMetLabCan { detalle21 }
            titMetBal { detalle22 }
        }
        bagazo { detalle3 }
        mielFinaMelaza { detalle4 }
        jugoDiluido { detalle5 }
        jugoClaro { detalle6 }
        jugoPrimeraExtraccion { detalle7 }
        jugoResidual { detalle8 }
        cachaza { detalle9 }
        azucarGranel { 
            azucarGranel { detalle101 }
            grasshoper { detalle102 }
        }
    }
    titCalFab { detalle12 }
    titConSerInsFab { detalle13 }
    titAnaRutEspFab {
        titSeccion1 { detalle14 }
        titSeccion2 { detalle15 }
        titSeccion3 { detalle16 }
    }
""")
class BlcP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle1> detalle1

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle21> detalle21

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle22> detalle22

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle3> detalle3

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle4> detalle4

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle5> detalle5

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle6> detalle6

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle7> detalle7

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle8> detalle8

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle9> detalle9

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle101> detalle101

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle102> detalle102

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle12> detalle12

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle13> detalle13

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle14> detalle14

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle15> detalle15

    @OneToMany (mappedBy="blcP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<BlcPDetalle16> detalle16

}

