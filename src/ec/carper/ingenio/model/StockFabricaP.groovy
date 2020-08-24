package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    titTqJDil { detalle1 }
    titTqJCla { detalle2 }
    titTqJEnc { detalle3 }
    titTqJFil { detalle4 }
    titClaJug { detalle5 }
    titTorSul {
        titTorSulJug{ detalle6 }
        titTorSulMel{ detalle7 }
    }
    titCalJug{
        titCalJug1 { detalle8  }
        titCalJug2 { detalle9  }
        titCalJug3 { detalle10 }
        titCalJug4 { detalle11 }
        titCalJug5 { detalle12 }
    }
""")
class StockFabricaP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle1> detalle1

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle2> detalle2

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle3> detalle3

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle4> detalle4

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle5> detalle5

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle6> detalle6

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle7> detalle7

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle8> detalle8

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle9> detalle9

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle10> detalle10

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle11> detalle11

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle12> detalle12
}
