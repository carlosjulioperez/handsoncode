package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Paro extends Identifiable{

    @Column(length=100) @Required
    String descripcion
    
    // https://github.com/mariuszs/openxava/blob/master/source/src/test/java/org/openxava/test/model/Clerk.java
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp inicioParo

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp finParo

}
