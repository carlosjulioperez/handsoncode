package ec.carper.ingenio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

import java.util.Locale;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.openxava.actions.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.model.*;

@Entity
@View(members=
    "fecha,calSemana;" +
    "tabDatosDia{#" + 
    "   canaDia;" +
    "   aguaMaceracion;" +
    "   jugoDiluido, calJugoDiluidoQty;" +
    // "   calBagazoCalculado, calBagazoCalculadoQty;" +
    // "   bagazoDirecto, calBagazoDirectoQty;" +
    "}" +
    "tabTiempos { " + 
    "}" +
    "tabVariablesPrimarias{ " + 
    "   tabJugoDiluido{ " +
            "calRhoJugoDiluido,brixJDil;" +
    "   };" +
    "}" 
)
//Reporte de fábrica
public class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required @Getter @Setter
    private LocalDate fecha;

    @Getter @Setter
    private int semana;

    /**
     * https://sourceforge.net/p/openxava/discussion/437013/thread/83b3114767/?limit=25
     * https://stackoverflow.com/questions/26012434/get-week-number-of-localdate-java-8
     *
     */
    @Depends("fecha") //Propiedad calculada
    public int getCalSemana(){

        // Locale currentLocale = Locale.getDefault();
        // System.out.println(currentLocale.getLanguage());
        // System.out.println(currentLocale.getCountry());

        LocalDate localDate = (LocalDate) fecha;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());           
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
        //System.out.println(weekNumber);
        return weekNumber;
    }

    // DATOS DIA
    @Getter @Setter
    private BigDecimal canaDia;
    
    @Required @Getter @Setter
    private BigDecimal aguaMaceracion;
    
    @Required @Getter @Setter
    private BigDecimal jugoDiluido;
    
    @Getter @Setter
    private BigDecimal jugoDiluidoQty;

    @Depends("jugoDiluido, brixJDil") //Propiedad calculada
    public BigDecimal getCalJugoDiluidoQty(){
        //return getJugoDiluido().multiply(getCalRhoJugoDiluido()).divide(new BigDecimal("1000"));
        return (jugoDiluido!=null && brixJDil!=null) ? 
            jugoDiluido.multiply(getCalRhoJugoDiluido()).divide(new BigDecimal("1000")) : 
            BigDecimal.ZERO;
    }
    
    //************************************************************
    @Getter @Setter
    private BigDecimal bagazoCalculado;

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana")
    public BigDecimal getCalBagazoCalculado(){
        return (aguaMaceracion!=null && jugoDiluido!=null && canaDia!=null && hojaCana!=null) ? 
            aguaMaceracion.add(getCalCanaNeta()).subtract(getCalJugoDiluidoQty()): BigDecimal.ZERO;
    }

    @Getter @Setter
    private BigDecimal bagazoCalculadoQty;

    @Getter @Setter
    private BigDecimal bagazoDirecto;

    @Getter @Setter
    private BigDecimal bagazoDirectoQty;

    @Depends("bagazoDirecto, canaDia")
    public BigDecimal getCalBagazoDirectoQty(){
        return (bagazoDirecto!=null && canaDia!=null) ? 
            bagazoDirecto.multiply(new BigDecimal("100")).divide(canaDia) :
            BigDecimal.ZERO;
    }

    @Getter @Setter
    private BigDecimal canaNeta;

    @Depends("canaDia, hojaCana")
    public BigDecimal getCalCanaNeta(){
        return (canaDia!=null && hojaCana!=null) ? canaDia.subtract(hojaCana): BigDecimal.ZERO;
    }

    @Getter @Setter
    private BigDecimal hojaCana;

    //************************************************************

    // VARIABLES PRIMARIAS
    @Required @Getter @Setter
    private BigDecimal rhoJugoDiluido;

    @Depends("brixJDil") //Propiedad calculada
    public BigDecimal getCalRhoJugoDiluido(){
        return new BrixDensidadWp().getP(getBrixJDil());
    }
    
    @Required @Getter @Setter
    private BigDecimal brixJDil;
    
    //**********************************************************************
    // Cálculos y formúlas
    //**********************************************************************
    
    public void recalculateSemana(){
        setSemana(getCalSemana());
    }

    public void recalculateJugoDiluidoQty(){
        setJugoDiluidoQty(getCalJugoDiluidoQty());
    }

    public void recalculateRhoJugoDiluido(){
        setRhoJugoDiluido(getCalRhoJugoDiluido());
    }

    public void recalculateBagazoDirectoQty(){
        setBagazoDirectoQty(getCalBagazoDirectoQty());
    }
    
    public void recalculateCanaNeta(){
        setCanaNeta(getCalCanaNeta());
    }
    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************
    
    @PrePersist //Al grabar la primera vez
    private void onPersist(){
        recalculateSemana();
        recalculateJugoDiluidoQty();
        recalculateRhoJugoDiluido();
        recalculateCanaNeta();
    }

    @PreUpdate //Cada vez que se modifica
    private void onUpdate(){
        recalculateSemana();
        recalculateJugoDiluidoQty();
        recalculateRhoJugoDiluido();
        recalculateCanaNeta();
    }

    /*
    @PreRemove //Al borrar el registro
    private void onRemove(){
        if (isRemoving()) return; //Añadimos esta línea para evitar excepciones
        recalculateSemana();
    }

    @Transient //No se almacena en la tabla en la base de datos
    private boolean removing = false; //Indica si JPA está borrando el documento ahora

    boolean isRemoving(){ //Acceso paquete, no es accesible desde fuera
        return removing;
    }

    @PreRemove //Cuando el registro va a ser borrado marcamos removing como true
    private void markRemoving(){ 
        this.removing = true;
    }

    @PostRemove //Cuando el registro ha sido borrado marcamos removing como false
    private void unmarkRemoving(){
        this.removing = false;
    }
    */
}
