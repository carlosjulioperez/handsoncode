package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo.descripcion,promTrashCana,promPorcTrash""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
class TrashCana extends DiaTrabajoEditable {

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana,netaCana,calTrashCana[trashCana.promTrashCana], calPorcTrash[trashCana.promPorcTrash]
    """)
    Collection<TrashCanaDetalle1>detalle1
    
    //Promedio para detalle1
    BigDecimal getAvgDetalle1(String propiedad){
        def lista = []
        detalle1.each {
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            // println ">>>>>>>>>>> " + valor
            if (valor > 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(3, BigDecimal.ROUND_HALF_UP) : 0
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromTrashCana(){
        def valor = getAvgDetalle1("calTrashCana")
        setAvgTrashCana(valor)
        return valor 
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcTrash(){
        def valor = getAvgDetalle1("calPorcTrash")
        setAvgPorcTrash(valor)
        return valor
    }
    
    @Digits(integer=4, fraction=3)
    BigDecimal avgTrashCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcTrash

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    @ListProperties("""hora,mlReductores,calTab7SusRed,calPorcAzuRed""")
    Collection<TrashCanaDetalle2>detalle2

}
