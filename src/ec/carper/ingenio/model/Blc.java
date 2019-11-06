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
    "tabDatosDia{" + 
    "   canaDia;" +
    "   aguaMaceracion;" +
    "   jugoDiluido,calJugoDiluidoQty" +
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

    //Datos dia
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
    
    @Getter @Setter
    private BigDecimal bagazoCalculado;

    @Depends("aguaMaceracion, jugoDiluido")
    public BigDecimal getCalBagazoCalculado(){
        return BigDecimal.ZERO;
    }

    @Getter @Setter
    private BigDecimal bagazoCalculadoQty;

    @Getter @Setter
    private BigDecimal bagazoDirecto;

    @Getter @Setter
    private BigDecimal bagazoDirectoQty;

    // Variables primarias
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

    public void recalculateQtyJugoDiluido(){
        setJugoDiluidoQty(getCalJugoDiluidoQty());
    }

    public void recalculateRhoJugoDiluido(){
        setRhoJugoDiluido(getCalRhoJugoDiluido());
    }

    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************
    
    @PrePersist //Al grabar la primera vez
    private void onPersist(){
        recalculateSemana();
        recalculateQtyJugoDiluido();
        recalculateRhoJugoDiluido();
    }

    @PreUpdate //Cada vez que se modifica
    private void onUpdate(){
        recalculateSemana();
        recalculateQtyJugoDiluido();
        recalculateRhoJugoDiluido();
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
