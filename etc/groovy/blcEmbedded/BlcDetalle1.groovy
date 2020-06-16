package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*


@Embeddable
@Views([
    @View( members="material,valor,unidad,cantidad,unidad2" ),
    @View( name="A", extendsView="DEFAULT" )
])
class BlcDetalle1{
   
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
