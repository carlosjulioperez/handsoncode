package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class BlcPlantilla{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @ElementCollection
    @ListProperties("""material,valor,unidad,cantidad,unidad2""")
    Collection<BlcDetalle1>detalle1
}

