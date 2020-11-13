package ec.carper.ingenio.model

import ec.carper.ingenio.util.*

import java.text.SimpleDateFormat
import java.time.format.*
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits

import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

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
@View(members="""
    diaTrabajo, wH2OTmp, wCanaTmp;
    titAnaCan { detalle1 }
    titTalCog { detalle2 }
""")
class Cana extends Formulario {
    
    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados

    @DisplaySize(6)
    BigDecimal wH2OTmp
    
    @DisplaySize(6)
    BigDecimal wCanaTmp
    //**************************************************

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
    
    @OneToMany (mappedBy="cana", cascade=CascadeType.ALL) @XOrderBy("hora")
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
    
    @OneToMany (mappedBy="cana", cascade=CascadeType.ALL) @XOrderBy("horaDesde")
    @ListProperties("""
        horaDesde, horaHasta,
        polReal,
        brixExtracto,
        polExtracto,
        porcHumedad,
        brix,
        porcFibra,
        porcSacarosa,
        pureza
    """)
    Collection<CanaDetalle2>detalle2

    void actualizar() throws ValidationException{
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

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    void crearItemsPorHora() throws ValidationException{
        try{
            this.itemsPorHoraCreados = true
            getManager().persist(this)
            crearItems(this)
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

    void crearItems(Cana cana) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)

            while(hora < horaF ) {
                def det = new CanaDetalle1(cana: cana, horaS: Util.instance.getHoraS(hora), hora: hora, wH2O: cana.wH2OTmp, wCana: cana.wCanaTmp)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

}
