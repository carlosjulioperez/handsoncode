package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Material extends Identifiable{

    @Column(length=100) @Required
    String descripcion
    
    @Column(length=30)
    String campo 

}
