package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
class Formulario extends Identifiable{

    @ManyToOne(fetch=FetchType.LAZY)
    // @DescriptionsList(descriptionProperties="descripcion", condition="\${cerrado}='false'")
    // @DescriptionsList(condition="\${cerrado}='false'")
    // @DescriptionsList(order="\${fecha}")
    @DescriptionsList(condition="\${activo}='true'", order="\${fecha}")
    @OnChange(FormularioDiaTrabajoAction.class)
    @NoCreate @NoModify @Required    
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

    BigDecimal getSuma(def detalle, String propiedad){
        def lista = []
        detalle.each {
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            if (valor != null) lista << valor
        }
        return lista.size()>0 ? lista.sum() : 0
    }

}
