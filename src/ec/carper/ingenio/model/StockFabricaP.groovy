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
    titLinEva{
        titLinEva1 { detalle13 }
        titLinEva2 { detalle14 }
        titLinEva3 { detalle15 }
        titLinEva4 { detalle16 }
        titLinEva5 { detalle17 }
    }
    titTqMCru { detalle18 }
    titCalMel { 
        titCalMel1 { detalle19 }
        titCalMel2 { detalle20 }
    }
    titClaMel { detalle21 }
    titVasRea { detalle22 }
    titCri{
        titTac1 { 
            titMasA { detalle23 }
        }
        titTac2 { 
            titMasA { detalle24 }
            titMasB { detalle25 }
        }
        titTac3 { 
            titMasB { detalle26 }
            titMasC { detalle27 }
        }
        titTac4 { 
            titMasC { detalle28 }
        }
    }
    titTanAlm{
        titTanAlm01 { detalle29 }
        titTanAlm02 { detalle30 }
        titTanAlm03 { detalle31 }
        titTanAlm04 { detalle32 }
        titTanAlm05 { detalle33 }
        titTanAlm06 { detalle34 }
        titTanAlm07 { detalle35 }
        titTanAlm08 { detalle36 }
        titTanAlm09 { detalle37 }
        titTanAlm10 { detalle38 }
        titTanAlm11 { detalle39 }
    }
    titSemVac{
        titSemVac1 { detalle40 }
        titSemVac2 { detalle41 }
        titSemVac3 { detalle42 }
        titSemVac4 { detalle43 }
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

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle13> detalle13

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle14> detalle14

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle15> detalle15

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle16> detalle16

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle17> detalle17

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle18> detalle18

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle19> detalle19

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle20> detalle20

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle21> detalle21

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle22> detalle22

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle23> detalle23

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle24> detalle24

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle25> detalle25

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle26> detalle26

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle27> detalle27

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle28> detalle28

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle29> detalle29

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle30> detalle30

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle31> detalle31

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle32> detalle32

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle33> detalle33

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle34> detalle34

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle35> detalle35

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle36> detalle36

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle37> detalle37

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle38> detalle38

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle39> detalle39

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle40> detalle40

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle41> detalle41

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle42> detalle42

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle43> detalle43
}
