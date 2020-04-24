package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import ec.carper.ingenio.util.Util
import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo.descripcion,avgCantCana,avgCogollos,avgNetaCana,avgTrashCana,avgPorcTrash""")
@View(members=  """diaTrabajo;detalle""")
class Trash extends DiaTrabajoEditable {

    @ReadOnly
    BigDecimal avgCantCana

    @ReadOnly
    BigDecimal avgNetaCana

    BigDecimal avgCogollos

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal avgTrashCana

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcTrash

    @OneToMany (mappedBy="trash", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana[trash.avgCantCana],netaCana[trash.avgNetaCana],
        cogollos[trash.promCogollos], calPorcCogollos,
        valTrashCana[trash.avgTrashCana], valPorcTrash[trash.avgPorcTrash]
    """)
    
    //@NewAction("Ingenio.add")
    Collection<TrashDetalle>detalle

    BigDecimal getPromCogollos(){
        // def valor = Util.instance.getAvgDetalle(detalle, "cogollos", 2)
        // setAvgCogollos(valor)
        // return valor 
        def valor = super.getPromedio(detalle, "cogollos", 2)
        return valor
    }

}

