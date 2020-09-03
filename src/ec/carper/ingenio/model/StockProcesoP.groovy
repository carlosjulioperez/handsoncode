package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    detalle
""")
class StockProcesoP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="stockProcesoP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockProcesoPDetalle> detalle
}
