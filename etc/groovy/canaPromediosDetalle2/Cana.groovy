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
    porcArNsac,
    polReal2,
    brixExtracto2,
    polExtracto2,
    porcHumedad2,
    brix2,
    porcFibra2,
    porcSacarosa2,
    pureza2
""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
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
    
    BigDecimal polReal2
    BigDecimal brixExtracto2
    BigDecimal polExtracto2
    BigDecimal porcHumedad2
    BigDecimal brix2
    BigDecimal porcFibra2
    BigDecimal porcSacarosa2
    BigDecimal pureza2

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
        nSac           [cana.promNSac],
        aR             [cana.promAR],
        porcArNsac     [cana.promPorcArNsac]
    """)
    Collection<CanaDetalle1>detalle1

    BigDecimal getPromWH2O(){
        return super.getPromedio(detalle1, "wH2O", 2)
    }
    BigDecimal getPromWCana(){
        return super.getPromedio(detalle1, "wCana", 2)
    }
    BigDecimal getPromPolReal(){
        return super.getPromedio(detalle1, "polReal", 2)
    }
    BigDecimal getPromBrixExtracto(){
        return super.getPromedio(detalle1, "brixExtracto", 2)
    }
    BigDecimal getPromPolExtracto(){
        return super.getPromedio(detalle1, "polExtracto", 2)
    }
    BigDecimal getPromTamizVacioM0(){
        return super.getPromedio(detalle1, "tamizVacioM0", 2)
    }
    BigDecimal getPromMuestraHumM1(){
        return super.getPromedio(detalle1, "muestraHumM1", 2)
    }
    BigDecimal getPromMuestraSecaM2(){
        return super.getPromedio(detalle1, "muestraSecaM2", 2)
    }
    BigDecimal getPromPorcHumedad(){
        return super.getPromedio(detalle1, "porcHumedad", 2)
    }
    BigDecimal getPromBrix(){
        return super.getPromedio(detalle1, "brix", 2)
    }
    BigDecimal getPromPorcFibra(){
        return super.getPromedio(detalle1, "porcFibra", 2)
    }
    BigDecimal getPromPorcSacarosa(){
        return super.getPromedio(detalle1, "porcSacarosa", 2)
    }
    BigDecimal getPromPureza(){
        return super.getPromedio(detalle1, "pureza", 2)
    }
    BigDecimal getPromNSac(){
        return super.getPromedio(detalle1, "nSac", 2)
    }
    BigDecimal getPromAR(){
        return super.getPromedio(detalle1, "aR", 2)
    }
    BigDecimal getPromPorcArNsac(){
        return super.getPromedio(detalle1, "porcArNsac", 2)
    }
    
    @OneToMany (mappedBy="cana", cascade=CascadeType.ALL)
    @ListProperties("""
        horaDesde, horaHasta,
        polReal        [cana.promPolReal2],
        brixExtracto   [cana.promBrixExtracto2],
        polExtracto    [cana.promPolExtracto2],
        porcHumedad    [cana.promPorcHumedad2],
        brix           [cana.promBrix2],
        porcFibra      [cana.promPorcFibra2], 
        porcSacarosa   [cana.promPorcSacarosa2],
        pureza         [cana.promPureza2]
    """)
    Collection<CanaDetalle2>detalle2

    BigDecimal getPromPolReal2(){
        return super.getPromedio(detalle2, "polReal", 2)
    }
    BigDecimal getPromBrixExtracto2(){
        return super.getPromedio(detalle2, "brixExtracto", 2)
    }
    BigDecimal getPromPolExtracto2(){
        return super.getPromedio(detalle2, "polExtracto", 2)
    }
    BigDecimal getPromPorcHumedad2(){
        return super.getPromedio(detalle2, "porcHumedad", 2)
    }
    BigDecimal getPromBrix2(){
        return super.getPromedio(detalle2, "brix", 2)
    }
    BigDecimal getPromPorcFibra2(){
        return super.getPromedio(detalle2, "porcFibra", 2)
    }
    BigDecimal getPromPorcSacarosa2(){
        return super.getPromedio(detalle2, "porcSacarosa", 2)
    }
    BigDecimal getPromPureza2(){
        return super.getPromedio(detalle2, "pureza", 2)
    }

    void save() throws ValidationException{
        try{

            this.wH2O          = promWH2O
            this.wCana         = promWCana
            this.polReal       = promPolReal
            this.brixExtracto  = promBrixExtracto
            this.polExtracto   = promPolExtracto
            this.tamizVacioM0  = promTamizVacioM0
            this.muestraHumM1  = promMuestraHumM1
            this.muestraSecaM2 = promMuestraSecaM2
            this.porcHumedad   = promPorcHumedad
            this.brix          = promBrix
            this.porcFibra     = promPorcFibra
            this.porcSacarosa  = promPorcSacarosa
            this.pureza        = promPureza
            this.nSac          = promNSac
            this.aR            = promAR
            this.porcArNsac    = promPorcArNsac
            
            this.polReal2      = promPolReal2
            this.brixExtracto2 = promBrixExtracto2
            this.polExtracto2  = promPolExtracto2
            this.porcHumedad2  = promPorcHumedad2
            this.brix2         = promBrix2
            this.porcFibra2    = promPorcFibra2
            this.porcSacarosa2 = promPorcSacarosa2
            this.pureza2       = promPureza2

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
