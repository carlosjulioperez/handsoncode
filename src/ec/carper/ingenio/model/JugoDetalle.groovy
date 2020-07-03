package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    horaS, hora;
    jeBri, jePol, jeSac, jePur;
    jdBri, jdPol, jdSac, jdPur;
    jcBri, jcPol, jcSac, jcPur;
    jnBri, jnPol, jnSac, jnPur;
    jrBri, jrPol, jrSac, jrPur;
    jfBri, jfPol, jfSac, jfPur
""")
class JugoDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Jugo jugo

    @Stereotype("TIME") @OnChange(JugoDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jeBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jePol
    @ReadOnly @DisplaySize(6)
    BigDecimal jeSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jePur
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jdBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jdPol
    @ReadOnly @DisplaySize(6)
    BigDecimal jdSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jdPur
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jcBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jcPol
    @ReadOnly @DisplaySize(6)
    BigDecimal jcSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jcPur
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jnBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jnPol
    @ReadOnly @DisplaySize(6)
    BigDecimal jnSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jnPur
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jrBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jrPol
    @ReadOnly @DisplaySize(6)
    BigDecimal jrSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jrPur
    
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jfBri
    @OnChange(JugoDetalleAction.class) @DisplaySize(6)
    BigDecimal jfPol
    @ReadOnly @DisplaySize(6)
    BigDecimal jfSac
    @ReadOnly @DisplaySize(6)
    BigDecimal jfPur
    
    BigDecimal getPorcSacJR(String diaTrabajoId, java.sql.Timestamp hora){
        BigDecimal valor = 0

        Query query = getManager().createQuery("SELECT jrSac FROM JugoDetalle WHERE jugo.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        // TODO: Validación de nulo en primer elemento de listas
        // List records = query.resultList
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // println records
        // valor = records[0]?: 0
        return query.resultList[0]?: 0
    }

}
