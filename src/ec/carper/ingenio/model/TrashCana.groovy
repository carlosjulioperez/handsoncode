package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo,promTrashCana,promPorcTrash""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
class TrashCana extends DiaTrabajoEditable{
    
    @Version
    private Integer version;

    //TODO
    //file:///home/carper/local/utils/ox/openxava-6.2.1/doc/docs/basic-business-logic_es.html

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
            println ">>>>>>>>>>> " + valor
            if (valor > 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(3, BigDecimal.ROUND_HALF_UP) : 0
    }

    BigDecimal getPromTrashCana(){
        return getAvgDetalle1("calTrashCana")
    }

    BigDecimal getPromPorcTrash(){
        return getAvgDetalle1("calPorcTrash")
    }

    BigDecimal avgTrashCana
    BigDecimal avgPorcTrash

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL)
    // @ListProperties("""
    //     hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
    //     cantidadCana,netaCana,calTrashCana,calPorcTrash
    // """)
    Collection<TrashCanaDetalle2>detalle2

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        sincronizarPropiedadesPersistentes()
    }
    
    @PreUpdate
    void sincronizarPropiedadesPersistentes(){
        setAvgTrashCana(promTrashCana)
        setAvgPorcTrash(promPorcTrash)
    }

}
