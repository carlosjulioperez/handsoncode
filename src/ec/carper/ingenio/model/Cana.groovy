package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*

import java.time.LocalDate

@Entity
@Tab(properties="""
    diaTrabajo.descripcion,
    wH2O,
    wCana,
    polReal,
    brixExtracto,
    polExtracto,
    tamizVacioM0,
    muestraHumM1,
    muestraSecaM2,
    porcHumedad,
    brix,
    porcFibra,
    porcSacarosa,
    pureza,
    nSac,
    aR,
    porcArNsac
""")
@View(members=  """diaTrabajo;detalle""")
class Cana extends DiaTrabajoEditable {

    BigDecimal wH2O
    BigDecimal wCana
    BigDecimal polReal
    BigDecimal brixExtracto
    BigDecimal polExtracto
    BigDecimal tamizVacioM0
    BigDecimal muestraHumM1
    BigDecimal muestraSecaM2
    BigDecimal porcHumedad
    BigDecimal brix
    BigDecimal porcFibra
    BigDecimal porcSacarosa
    BigDecimal pureza
    BigDecimal nSac
    BigDecimal aR
    BigDecimal porcArNsac

    @OneToMany (mappedBy="cana", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        wH2O           [cana.promWH2O],
        wCana          [cana.promWCana],
        polReal        [cana.promPolReal],
        brixExtracto   [cana.promBrixExtracto],
        polExtracto    [cana.promPolExtracto],
        tamizVacioM0   [cana.promTamizVacioM0],
        muestraHumM1   [cana.promMuestraHumM1],
        muestraSecaM2  [cana.promMuestraSecaM2],
        porcHumedad    [cana.promPorcHumedad],
        brix           [cana.promBrix],
        porcFibra      [cana.promPorcFibra],
        porcSacarosa   [cana.promPorcSacarosa],
        pureza         [cana.promPureza],
        nSac           [cana.nSac],
        aR             [cana.aR],
        porcArNsac     [cana.porcArNsac]
    """)
    Collection<CanaDetalle>detalle

    BigDecimal getPromWH2O(){
        return super.getPromedio(detalle, "wH2O", 2)
    }
    BigDecimal getPromWCana(){
        return super.getPromedio(detalle, "wCana", 2)
    }
    BigDecimal getPromPolReal(){
        return super.getPromedio(detalle, "polReal", 2)
    }
    BigDecimal getPromBrixExtracto(){
        return super.getPromedio(detalle, "brixExtracto", 2)
    }
    BigDecimal getPromPolExtracto(){
        return super.getPromedio(detalle, "polExtracto", 2)
    }
    BigDecimal getPromTamizVacioM0(){
        return super.getPromedio(detalle, "tamizVacioM0", 2)
    }
    BigDecimal getPromMuestraHumM1(){
        return super.getPromedio(detalle, "muestraHumM1", 2)
    }
    BigDecimal getPromMuestraSecaM2(){
        return super.getPromedio(detalle, "muestraSecaM2", 2)
    }
    BigDecimal getPromPorcHumedad(){
        return super.getPromedio(detalle, "porcHumedad", 2)
    }
    BigDecimal getPromBrix(){
        return super.getPromedio(detalle, "brix", 2)
    }
    BigDecimal getPromPorcFibra(){
        return super.getPromedio(detalle, "porcFibra", 2)
    }
    BigDecimal getPromPorcSacarosa(){
        return super.getPromedio(detalle, "porcSacarosa", 2)
    }
    BigDecimal getPromPureza(){
        return super.getPromedio(detalle, "pureza", 2)
    }
    BigDecimal getPromNSac(){
        return super.getPromedio(detalle, "nSac", 2)
    }
    BigDecimal getPromAR(){
        return super.getPromedio(detalle, "aR", 2)
    }
    BigDecimal getPromPorcArNsac(){
        return super.getPromedio(detalle, "porcArNsac", 2)
    }

    void save() throws ValidationException{
        try{

            wH2O          = promWH2O
            wCana         = promWCana
            polReal       = promPolReal
            brixExtracto  = promBrixExtracto
            polExtracto   = promPolExtracto
            tamizVacioM0  = promTamizVacioM0
            muestraHumM1  = promMuestraHumM1
            muestraSecaM2 = promMuestraSecaM2
            porcHumedad   = promPorcHumedad
            brix          = promBrix
            porcFibra     = promPorcFibra
            porcSacarosa  = promPorcSacarosa
            pureza        = promPureza
            nSac          = promNSac
            aR            = promAR
            porcArNsac    = promPorcArNsac
            
            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
