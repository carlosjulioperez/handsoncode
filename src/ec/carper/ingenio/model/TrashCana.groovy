package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo.descripcion,promCantCana,promNetaCana,promTrashCana,promPorcTrash,promPorcAzuRed""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
class TrashCana extends DiaTrabajoEditable {
    
    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana[trashCana.promCantCana],netaCana[trashCana.promNetaCana],calTrashCana[trashCana.promTrashCana], calPorcTrash[trashCana.promPorcTrash]
    """)
    Collection<TrashCanaDetalle1>detalle1
    
    //Promedio para detalle1
    BigDecimal getAvgDetalle1(String propiedad, int escala){
        def lista = []
        detalle1.each {
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            // println ">>>>>>>>>>> " + valor
            if (valor > 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(escala, BigDecimal.ROUND_HALF_UP) : 0
    }

    BigDecimal getPromCantCana(){
        def valor = getAvgDetalle1("cantidadCana", 2)
        setAvgCantCana(valor)
        return valor 
    }

    BigDecimal getPromNetaCana(){
        def valor = getAvgDetalle1("netaCana", 2)
        setAvgNetaCana(valor)
        return valor 
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromTrashCana(){
        def valor = getAvgDetalle1("calTrashCana", 3)
        setAvgTrashCana(valor)
        return valor 
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcTrash(){
        def valor = getAvgDetalle1("calPorcTrash", 3)
        setAvgPorcTrash(valor)
        return valor
    }
    
    BigDecimal avgCantCana

    BigDecimal avgNetaCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgTrashCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcTrash

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    @ListProperties("""hora,mlReductores,calTab7SusRed,calPorcAzuRed[trashCana.promPorcAzuRed]""")
    Collection<TrashCanaDetalle2>detalle2

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcAzuRed

    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcAzuRed(){
        def lista = []
        detalle2.each {
            if (it.calPorcAzuRed > 0) lista << it.calPorcAzuRed  
        }
        def valor = lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(3, BigDecimal.ROUND_HALF_UP) : 0
        setAvgPorcAzuRed(valor)
        return valor
    }

}
