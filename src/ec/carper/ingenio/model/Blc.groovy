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
    @Depends("fecha") //Propiedad calculada
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

    @Depends("jugoDiluido, brixJDil") //Propiedad calculada
    BigDecimal getCalJugoDiluidoQty(){
        return (jugoDiluido && brixJDil) ? 
            ( jugoDiluido * getCalRhoJugoDiluido() ) / 1000 : 0
    }
    
    //************************************************************
    @Getter @Setter
    BigDecimal bagazoCalculado

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana")
    BigDecimal getCalBagazoCalculado(){
        return (aguaMaceracion && jugoDiluido && canaDia && hojaCana) ? 
            aguaMaceracion + getCalCanaNeta() - getCalJugoDiluidoQty(): 0
    }

    @Getter @Setter
    BigDecimal bagazoCalculadoQty

    @Getter @Setter
    BigDecimal bagazoDirecto

    @Getter @Setter
    BigDecimal bagazoDirectoQty

    @Depends("bagazoDirecto, canaDia")
    BigDecimal getCalBagazoDirectoQty(){
        return (bagazoDirecto && canaDia) ? 
            ( bagazoDirecto * 100 ) / canaDia : 0
    }

    @Getter @Setter
    BigDecimal canaNeta

    @Depends("canaDia, hojaCana")
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
    
    void recalculateSemana(){
        setSemana(getCalSemana())
    }

    void recalculateJugoDiluidoQty(){
        setJugoDiluidoQty(getCalJugoDiluidoQty())
    }

    void recalculateRhoJugoDiluido(){
        setRhoJugoDiluido(getCalRhoJugoDiluido())
    }

    void recalculateBagazoDirectoQty(){
        setBagazoDirectoQty(getCalBagazoDirectoQty())
    }
    
    void recalculateCanaNeta(){
        setCanaNeta(getCalCanaNeta())
    }
    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************
    
    @PrePersist //Al grabar la primera vez
    void onPersist(){
        recalculateSemana()
        recalculateJugoDiluidoQty()
        recalculateRhoJugoDiluido()
        recalculateCanaNeta()
    }

    @PreUpdate //Cada vez que se modifica
    void onUpdate(){
        recalculateSemana()
        recalculateJugoDiluidoQty()
        recalculateRhoJugoDiluido()
        recalculateCanaNeta()
    }
}
