package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

//import ec.carper.ingenio.util.Util

import java.time.LocalDate

@Entity
@Tab(properties="""
    diaTrabajo.descripcion,
    promCantCana, promNetaCana,
    promCogollos, promPorcCogollos,
    promHojas, promPorcHojas,
    promCepa, promPorcCepa,
    promCanaSeca, promPorcCanaSeca,
    promSuelo, promPorcSuelo,
    promOtros, promPorcOtros,
    promTrashCana, promPorcTrash,
    promCanaInfectada, promPorcCanaInfectada
""")
@View(members=  """diaTrabajo;detalle""")
class Trash extends DiaTrabajoEditable {

    @Version
    private Integer version;

    BigDecimal avgCantCana

    BigDecimal avgNetaCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgTrashCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcTrash

    // Propiedades para sincronizar
    BigDecimal avgCogollos
    BigDecimal avgPorcCogollos
    BigDecimal avgHojas
    BigDecimal avgPorcHojas

    BigDecimal avgCepa 
    BigDecimal avgPorcCepa
    BigDecimal avgCanaSeca
    BigDecimal avgPorcCanaSeca
    BigDecimal avgSuelo
    BigDecimal avgPorcSuelo
    BigDecimal avgOtros
    BigDecimal avgPorcOtros
    BigDecimal avgCanaInfectada
    BigDecimal avgPorcCanaInfectada

    @OneToMany (mappedBy="trash", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana[trash.promCantCana],netaCana[trash.promNetaCana],

        cogollos[trash.promCogollos], calPorcCogollos[trash.promPorcCogollos],
        hojas[trash.promHojas], calPorcHojas[trash.promPorcHojas],
        
        cepa[trash.promCepa], calPorcCepa[trash.promPorcCepa],
        canaSeca[trash.promCanaSeca], calPorcCanaSeca[trash.promPorcCanaSeca],
        suelo[trash.promSuelo], calPorcSuelo[trash.promPorcSuelo],
        otros[trash.promOtros], calPorcOtros[trash.promPorcOtros],
        calTrashCana[trash.promTrashCana], calPorcTrash[trash.promPorcTrash],
        canaInfectada[trash.promCanaInfectada], calPorcCanaInfectada[trash.promPorcCanaInfectada]
    """)
    Collection<TrashDetalle>detalle

    // -------------------
    // Los promedios vinieron de COPIAR TRASH pero es necesario que la funcionalidad se cumpla como en las demás columnas
    // -------------------
    BigDecimal getPromCantCana(){
        return super.getPromedio(detalle, "cantidadCana", 2)
        //return Util.instance.getPromedio(detalle, "cantidadCana", 2)
    }
    BigDecimal getPromNetaCana(){
        return super.getPromedio(detalle, "netaCana", 2)
    }
    @Digits(integer=4, fraction=3)
    BigDecimal getPromTrashCana(){
        return super.getPromedio(detalle, "calTrashCana", 3)
    }
    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcTrash(){
        return super.getPromedio(detalle, "calPorcTrash", 3)
    }
    // -------------------

    BigDecimal getPromCogollos(){
        return super.getPromedio(detalle, "cogollos", 2)
    }
    BigDecimal getPromPorcCogollos(){
        return super.getPromedio(detalle, "calPorcCogollos", 2)
    }

    BigDecimal getPromHojas(){
        return super.getPromedio(detalle, "hojas", 2)
    }
    BigDecimal getPromPorcHojas(){
        return super.getPromedio(detalle, "calPorcHojas", 2)
    }

    BigDecimal getPromCepa(){
        return super.getPromedio(detalle, "cepa", 2)
    }
    BigDecimal getPromPorcCepa(){
        return super.getPromedio(detalle, "calPorcCepa", 2)
    }

    BigDecimal getPromCanaSeca(){
        return super.getPromedio(detalle, "canaSeca", 2)
    }
    BigDecimal getPromPorcCanaSeca(){
        return super.getPromedio(detalle, "calPorcCanaSeca", 2)
    }

    BigDecimal getPromSuelo(){
        return super.getPromedio(detalle, "suelo", 2)
    }
    BigDecimal getPromPorcSuelo(){
        return super.getPromedio(detalle, "calPorcSuelo", 2)
    }

    BigDecimal getPromOtros(){
        return super.getPromedio(detalle, "otros", 2)
    }
    BigDecimal getPromPorcOtros(){
        return super.getPromedio(detalle, "calPorcOtros", 2)
    }

    BigDecimal getPromCanaInfectada(){
        return super.getPromedio(detalle, "canaInfectada", 2)
    }
    BigDecimal getPromPorcCanaInfectada(){
        return super.getPromedio(detalle, "calPorcCanaInfectada", 2)
    }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcular()
    }

    @PreUpdate
    void recalcular() {
        avgCantCana          = promCantCana
        avgNetaCana          = promNetaCana

        avgTrashCana         = promTrashCana
        avgPorcTrash         = promPorcTrash

        avgCogollos          = promCogollos
        avgPorcCogollos      = promPorcCogollos
        avgHojas             = promHojas
        avgPorcHojas         = promPorcHojas

        avgCepa              = promCepa
        avgPorcCepa          = promPorcCepa
        avgCanaSeca          = promCanaSeca
        avgPorcCanaSeca      = promPorcCanaSeca
        avgSuelo             = promSuelo
        avgPorcSuelo         = promPorcSuelo
        avgOtros             = promOtros
        avgPorcOtros         = promPorcOtros
        avgCanaInfectada     = promCanaInfectada
        avgPorcCanaInfectada = promPorcCanaInfectada
    }

}

