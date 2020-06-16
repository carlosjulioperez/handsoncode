package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@Views([
    @View( members="orden,material;valor,unidad;cantidad,unidad2" ),
    @View( name="Edicion", extendsView="DEFAULT" )
])
class BlcDetalle1 extends Identifiable{
    
    @ManyToOne
    BlcPlantilla blcPlantilla 
   
    @ManyToOne
    Blc blc

    @ReadOnly(notForViews="DEFAULT")
    @Column(length=2)
    int orden
   
    @ReadOnly(notForViews="DEFAULT")
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Material material
   
    @ReadOnly(forViews="DEFAULT")
    @DisplaySize(5)
    BigDecimal valor 

    @ReadOnly(notForViews="DEFAULT")
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad
    
    @ReadOnly(forViews="DEFAULT")
    @DisplaySize(5)
    BigDecimal cantidad 

    @ReadOnly(notForViews="DEFAULT")
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad2
    
}