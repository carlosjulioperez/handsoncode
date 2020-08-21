package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Indicador extends Identifiable{

    @Column(length=50) @Required
    String descripcion
    
    @Column(length=20)
    String campo 

}
