package ec.carper.ingenio.model

import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.WeekFields

import java.util.Locale

import javax.persistence.*
import lombok.Getter
import lombok.Setter
import org.openxava.actions.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@View(members="""
    fecha,calSemana;
    tabDatosDia{#
       canaDia;
       aguaMaceracion;
       jugoDiluido, calJugoDiluidoQty;
       calBagazoCalculado, calBagazoCalculadoQty;
       bagazoDirecto, calBagazoDirectoQty;
       calCanaNeta;
       hojaCana;
    }
    tabTiempos {
    }
    tabVariablesPrimarias{
       tabJugoDiluido{
           calRhoJugoDiluido, brixJDil;
       }
    }
    """
)
//Reporte de fábrica
class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required @Getter @Setter
    LocalDate fecha

    @Getter @Setter
    int semana

    /**
     * https://sourceforge.net/p/openxava/discussion/437013/thread/83b3114767/?limit=25
     * https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8
     *
     */
    @Depends("fecha") //Propiedad calculada 1
    int getCalSemana(){

        // Locale currentLocale = Locale.getDefault()
        // System.out.println(currentLocale.getLanguage())
        // System.out.println(currentLocale.getCountry())

        LocalDate localDate = (LocalDate) fecha
        WeekFields weekFields = WeekFields.of(Locale.getDefault())           
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear())
        //System.out.println(weekNumber)
        return weekNumber
    }

    // DATOS DIA
    @Getter @Setter
    BigDecimal canaDia
    
    @Required @Getter @Setter
    BigDecimal aguaMaceracion
    
    @Required @Getter @Setter
    BigDecimal jugoDiluido
    
    @Getter @Setter
    BigDecimal jugoDiluidoQty

    @Depends("jugoDiluido, brixJDil") //Propiedad calculada 2
    BigDecimal getCalJugoDiluidoQty(){
        return ( jugoDiluido?:0 * getCalRhoJugoDiluido()?:0 ) / 1000
    }
    
    //************************************************************
    @Getter @Setter
    BigDecimal bagazoCalculado

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana") //Propiedad calculada 3
    BigDecimal getCalBagazoCalculado(){ 
        // return (aguaMaceracion && jugoDiluido && canaDia && hojaCana) ? 
        //     getCalCanaNeta() + aguaMaceracion - getCalJugoDiluidoQty(): 0
        return getCalCanaNeta()?:0 + aguaMaceracion - getCalJugoDiluidoQty()?:0
    }

    @Getter @Setter
    BigDecimal bagazoCalculadoQty

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana") //Propiedad calculada 4
    BigDecimal getCalBagazoCalculadoQty(){ 
        return (aguaMaceracion && jugoDiluido && canaDia && hojaCana) ? 
            getCalBagazoCalculado() / canaDia * 100 : 0
    }

    @Getter @Setter
    BigDecimal bagazoDirecto

    @Getter @Setter
    BigDecimal bagazoDirectoQty

    @Depends("bagazoDirecto, canaDia") //Propiedad calculada 5
    BigDecimal getCalBagazoDirectoQty(){
        return (bagazoDirecto && canaDia) ? 
            ( bagazoDirecto * 100 ) / canaDia : 0
    }

    @Getter @Setter
    BigDecimal canaNeta

    @Depends("canaDia, hojaCana") //Propiedad calculada
    BigDecimal getCalCanaNeta(){
        return (canaDia && hojaCana) ? canaDia - hojaCana: 0
    }

    @Getter @Setter
    BigDecimal hojaCana

    //************************************************************

    // VARIABLES PRIMARIAS
    @Required @Getter @Setter
    BigDecimal rhoJugoDiluido

    @Depends("brixJDil") //Propiedad calculada
    BigDecimal getCalRhoJugoDiluido(){
        return new BrixDensidadWp().getP(getBrixJDil())
    }
    
    @Required @Getter @Setter
    BigDecimal brixJDil
    
    //**********************************************************************
    // Cálculos y formúlas
    //**********************************************************************
    
    void recalculateSemana(){ //1
        setSemana(getCalSemana())
    }
    void recalculateJugoDiluidoQty(){ //2
        setJugoDiluidoQty(getCalJugoDiluidoQty())
    }
    void recalculateBagazoCalculado(){ //3
        setBagazoCalculado(getCalBagazoCalculado())
    }
    void recalculateBagazoCalculadoQty(){ //4
        setBagazoCalculadoQty(getCalBagazoCalculadoQty())
    }
    void recalculateBagazoDirectoQty(){ //5
        setBagazoDirectoQty(getCalBagazoDirectoQty())
    }
    

    void recalculateCanaNeta(){
        setCanaNeta(getCalCanaNeta())
    }
    void recalculateRhoJugoDiluido(){ //
        setRhoJugoDiluido(getCalRhoJugoDiluido())
    }

    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************
    
    @PrePersist //Al grabar la primera vez
    void onPersist(){
        recalculateSemana() //1
        recalculateJugoDiluidoQty() //2
        recalculateBagazoCalculado() //3
        recalculateBagazoCalculadoQty //4
        recalculateBagazoDirectoQty() //5
        
        recalculateCanaNeta()
        recalculateRhoJugoDiluido()
    }

    @PreUpdate //Cada vez que se modifica
    void onUpdate(){
        recalculateSemana() //1
        recalculateJugoDiluidoQty() //2
        recalculateBagazoCalculado() //3
        recalculateBagazoCalculadoQty //4
        recalculateBagazoDirectoQty() //5

        recalculateCanaNeta()
        recalculateRhoJugoDiluido()
    }
}
