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
    hora;
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

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jeBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jePol
    @ReadOnly
    BigDecimal jeSac
    @ReadOnly
    BigDecimal jePur
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jdBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jdPol
    @ReadOnly
    BigDecimal jdSac
    @ReadOnly
    BigDecimal jdPur
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jcBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jcPol
    @ReadOnly
    BigDecimal jcSac
    @ReadOnly
    BigDecimal jcPur
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jnBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jnPol
    @ReadOnly
    BigDecimal jnSac
    @ReadOnly
    BigDecimal jnPur
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jrBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jrPol
    @ReadOnly
    BigDecimal jrSac
    @ReadOnly
    BigDecimal jrPur
    
    @OnChange(JugoDetalleAction.class)
    BigDecimal jfBri
    @OnChange(JugoDetalleAction.class)
    BigDecimal jfPol
    @ReadOnly
    BigDecimal jfSac
    @ReadOnly
    BigDecimal jfPur
    
    BigDecimal getPorcSacJR(String diaTrabajoId, java.sql.Timestamp hora){
        BigDecimal valor = 0

        Query query = getManager().createQuery("SELECT jrSac FROM JugoDetalle WHERE jugo.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        List records = query.resultList
        valor = records ? records[0]: 0
        return valor
    }

}
