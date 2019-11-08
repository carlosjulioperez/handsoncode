package ec.carper.ingenio.model

import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.WeekFields

import java.util.Locale

import javax.persistence.*
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
       cachaza, calCachazaQty;
       mielFinalMelaza, calMielFinalMelazaQty;
       azucarBlanca, calAzucarBlancaQty;
       calCanaNeta;
       calJugoNeto, calJugoNetoQty;
       calMeladura, calMeladuraQty;
       hojaCana;
       calSacosDisueltos, sacosDisueltosQty;
    }
    tabTiempos {
    }
    tabVariablesPrimarias{
       tabJugoDiluido{
           calRhoJugoDiluido, brixJDil;
           solidosInsol, sacJDil;
       }
       tabJugoClaro{
           brixJClaro;
       }
    }
    tabAnalisisRutinariosEspecialesFabrica{
        tabAnalisisAREF {
            brixMeladuraCruda;
        }
    }
    """
)
//Reporte de fábrica
class Blc extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha

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
    BigDecimal canaDia
    
    BigDecimal aguaMaceracion
    
    BigDecimal jugoDiluido
    
    BigDecimal jugoDiluidoQty

    @Depends("jugoDiluido, brixJDil") //Propiedad calculada 2
    BigDecimal getCalJugoDiluidoQty(){
        return (jugoDiluido!=null && brixJDil!=null) ? 
            ( jugoDiluido * getCalRhoJugoDiluido() / 1000 ) : 0
    }
    
    //************************************************************
    BigDecimal bagazoCalculado

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana") //Propiedad calculada 3
    BigDecimal getCalBagazoCalculado(){ 
        return (aguaMaceracion!=null && jugoDiluido!=null && canaDia!=null && hojaCana!=null) ? 
            ( getCalCanaNeta() + aguaMaceracion - getCalJugoDiluidoQty() ) : 0
    }

    BigDecimal bagazoCalculadoQty

    @Depends("aguaMaceracion, jugoDiluido, canaDia, hojaCana") //Propiedad calculada 4
    BigDecimal getCalBagazoCalculadoQty(){ 
        return (aguaMaceracion!=null && jugoDiluido!=null && canaDia!=null && hojaCana!=null) ? 
            ( getCalBagazoCalculado() / canaDia * 100 ) : 0
    }

    BigDecimal bagazoDirecto

    BigDecimal bagazoDirectoQty

    @Depends("bagazoDirecto, canaDia") //Propiedad calculada 5
    BigDecimal getCalBagazoDirectoQty(){
        return (bagazoDirecto!=null && canaDia!=null) ? 
            ( bagazoDirecto * 100 / canaDia) : 0
    }

    BigDecimal cachaza

    BigDecimal cachazaQty

    @Depends("canaDia, cachaza") //Propiedad calculada 6
    BigDecimal getCalCachazaQty(){
        return (canaDia!=null && cachaza!=null) ? 
            (cachaza * 100 / canaDia ) : 0
    }

    BigDecimal mielFinalMelaza

    BigDecimal mielFinalMelazaQty

    @Depends("canaDia, mielFinalMelaza") //Propiedad calculada 7
    BigDecimal getCalMielFinalMelazaQty(){
        return (canaDia!=null && mielFinalMelaza!=null) ? 
            (mielFinalMelaza * 100 / canaDia ) : 0
    }

    BigDecimal azucarBlanca

    BigDecimal azucarBlancaQty

    @Depends("azucarBlanca") //Propiedad calculada 8
    BigDecimal getCalAzucarBlancaQty(){
        return (azucarBlanca!=null) ? 
            (azucarBlanca / 20) : 0
    }

    BigDecimal canaNeta

    @Depends("canaDia, hojaCana") //Propiedad calculada
    BigDecimal getCalCanaNeta(){
        return (canaDia!=null && hojaCana!=null) ? 
            (canaDia - hojaCana) : 0
    }

    BigDecimal jugoNeto

    @Depends("jugoDiluido, brixJDil, solidosInsol") //Propiedad calculada 9
    BigDecimal getCalJugoNeto(){
        return (jugoDiluido!=null && brixJDil!=null && solidosInsol!=null) ? 
            ( getCalJugoDiluidoQty() - (getCalJugoDiluidoQty() * solidosInsol / 100) ) : 0
    }

    BigDecimal jugoNetoQty

    @Depends("jugoDiluido, brixJDil, solidosInsol, canaDia") //Propiedad calculada 10
    BigDecimal getCalJugoNetoQty(){
        return (jugoDiluido!=null && brixJDil!=null && solidosInsol!=null && canaDia!=null) ? 
            ( getCalJugoNeto() * 100 / canaDia ) : 0
    }

    BigDecimal meladura

    //TODO
    @Depends("jugoDiluido, brixJDil, solidosInsol, brixJClaro, brixMeladuraCruda") //Propiedad calculada 11
    BigDecimal getCalMeladura(){
        return (jugoDiluido!=null && brixJDil!=null && solidosInsol!=null && brixJClaro!=null && brixMeladuraCruda!=null) ? 
            ( (getCalJugoDiluidoQty()-(getCalJugoDiluidoQty()*solidosInsol/100)) - ((getCalJugoDiluidoQty()-(getCalJugoDiluidoQty()*solidosInsol/100)) * (1 - (brixJClaro/brixMeladuraCruda))) ) : 0
    }

    BigDecimal meladuraQty

    @Depends("jugoDiluido, brixJDil, solidosInsol, brixJClaro, brixMeladuraCruda, canaDia") //Propiedad calculada 12
    BigDecimal getCalMeladuraQty(){
        return (jugoDiluido!=null && brixJDil!=null && solidosInsol!=null && brixJClaro!=null && brixMeladuraCruda!=null && canaDia!=null) ? 
        ( getCalMeladura()*100 / canaDia ) : 0
    }

    BigDecimal hojaCana

    BigDecimal sacosDisueltos

    @Depends("sacosDisueltosQty")
    BigDecimal getCalSacosDisueltos(){
        return (sacosDisueltosQty!=null) ? sacosDisueltosQty * 20 : 0
    }

    BigDecimal sacosDisueltosQty

    //************************************************************

    // VARIABLES PRIMARIAS
    BigDecimal rhoJugoDiluido

    @Depends("brixJDil") //Propiedad calculada
    BigDecimal getCalRhoJugoDiluido(){
        return brixJDil!=null ? new BrixDensidadWp().getP(getBrixJDil()) : 0
    }
    
    BigDecimal brixJDil
    
    BigDecimal solidosInsol
    
    BigDecimal sacJDil
    
    BigDecimal brixJClaro

    // ANALISIS RUTINARIOS Y ESPECIALES FABRICA
    BigDecimal brixMeladuraCruda

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
    void recalculateCachazaQty(){ //6
        setCachazaQty(getCalCachazaQty())
    }
    void recalculateMielFinalMelazaQty(){ //7
        setMielFinalMelazaQty(getCalMielFinalMelazaQty())
    }
    void recalculateAzucarBlancaQty(){ //8
        setAzucarBlancaQty(getCalAzucarBlancaQty())
    }
    void recalculateCanaNeta(){ //9
        setCanaNeta(getCalCanaNeta())
    }
    void recalculateJugoNeto(){ //10
        setJugoNeto(getCalJugoNeto())
    }
    void recalculateJugoNetoQty(){ //11
        setJugoNetoQty(getCalJugoNetoQty())
    }
    void recalculateMeladura(){ //12
        setMeladura(getCalMeladura())
    }
    void recalculateMeladuraQty(){ //13
        setMeladuraQty(getCalMeladuraQty())
    }
    void recalculateSacosDisueltos(){ //14
        setSacosDisueltos(getCalSacosDisueltos())
    }

    void recalculateRhoJugoDiluido(){ //
        setRhoJugoDiluido(getCalRhoJugoDiluido())
    }

    //**********************************************************************
    // Sincronización de propiedades calculadas y persistentes
    //**********************************************************************

    void sincronizarPropiedadesPersistentes(){
        recalculateSemana() //1
        recalculateJugoDiluidoQty() //2
        recalculateBagazoCalculado() //3
        recalculateBagazoCalculadoQty() //4
        recalculateBagazoDirectoQty() //5
        recalculateCachazaQty() //6
        recalculateMielFinalMelazaQty() //7
        recalculateAzucarBlancaQty() //8
        recalculateCanaNeta() //9
        recalculateJugoNeto() //10
        recalculateJugoNetoQty() //11
        recalculateMeladura() //12
        recalculateMeladuraQty() //13
        recalculateSacosDisueltos() //14

        recalculateRhoJugoDiluido()
    }
    
    // https://www.openxava.org/OpenXavaDoc/docs/basic-business-logic_es.html#Metodos-de-retrollamadas-JPA-Sincronizar-propiedades-persistentes-y-calculadas

    @PrePersist //Al grabar la primera vez
    private void preGrabar() throws Exception{
        sincronizarPropiedadesPersistentes()
    }
    
    // @PrePersist 
    // private void preGrabar() throws Exception {
    //     calcularNumero(); //cabecera
    //     recalcularImporte(); //detalle
    // }
    //
    // @Version
    // private Integer version; // Añadida propiedad 'version', sin getter, ni setter
    //  
    // @PreUpdate // Añadido '@PreUPdate'
    // public void recalcularImporte() { // Ejecutado justo antes de actualizar el objeto
    //     setImporte(getImporteTotal());
    // }

}
