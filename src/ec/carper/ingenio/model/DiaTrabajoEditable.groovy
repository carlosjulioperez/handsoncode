package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
class DiaTrabajoEditable extends Identifiable{

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify 
    DiaTrabajo diaTrabajo

    BigDecimal getPromedio(def detalle, String propiedad, int escala){
        def lista = []
        detalle.each {
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            // println ">>>>>>>>>>> " + valor
            if (valor >= 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(escala, BigDecimal.ROUND_HALF_UP) : 0
    }

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
    //         // addMessage ("solo_crear_un_registro")
    //         //
    //         // resetDescriptionsCache()
    //         // getView().clear()
    //     }
    // }
}
