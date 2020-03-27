package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
class DiaTrabajoEditable extends Identifiable{

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify 
    DiaTrabajo diaTrabajo

    // @PrePersist
    // void execute() throws Exception{
    //     //TODO Ejemplo para ejecutar queries desde Groovy
    //     def modulo = getClass().getSimpleName()
    //     println ( ">>>>>>>>>>>" + modulo )
    //     Query query = getManager().createQuery("select count(*) from ${modulo}")
    //     def numero = (Integer)query.getSingleResult()
    //     //System.out.println(numero)
    //
    //     if ( numero >0 ){
    //         return
    //         // addMessage ("msgSoloUnRegistro")
    //         //
    //         // resetDescriptionsCache()
    //         // getView().clear()
    //     }
    // }
}
