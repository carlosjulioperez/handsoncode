package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    hora, wH2O;
    wCana, polReal;
    brixExtracto, polExtracto;
    tamizVacioM0, muestraHumM1;
    muestraSecaM2, porcHumedad;
    brix, porcFibra;
    porcSacarosa, pureza;
    nSac, aR;
    porcArNsac
""")
class CanaDetalle1 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cana cana
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora

    @OnChange(CanaDetalle1Action.class)
    BigDecimal wH2O

    @OnChange(CanaDetalle1Action.class)
    BigDecimal wCana

    @ReadOnly
    BigDecimal polReal

    @OnChange(CanaDetalle1Action.class)
    BigDecimal brixExtracto
    
    @OnChange(CanaDetalle1Action.class)
    BigDecimal polExtracto

    @OnChange(CanaDetalle1Action.class)
    BigDecimal tamizVacioM0

    @ReadOnly
    BigDecimal muestraHumM1
    
    @OnChange(CanaDetalle1Action.class)
    BigDecimal muestraSecaM2

    @ReadOnly
    BigDecimal porcHumedad

    @ReadOnly
    BigDecimal brix
    
    @ReadOnly
    BigDecimal porcFibra
    
    @ReadOnly
    BigDecimal porcSacarosa
    
    @ReadOnly
    BigDecimal pureza
    
    @ReadOnly
    BigDecimal nSac
    
    @ReadOnly
    BigDecimal aR
    
    @Digits(integer=6, fraction=8)
    @ReadOnly
    BigDecimal porcArNsac

    BigDecimal getPromPorcHumedad(String diaTrabajoId, java.sql.Timestamp horaDesde, java.sql.Timestamp horaHasta){
        Query query = getManager().createQuery("FROM CanaDetalle1 WHERE cana.diaTrabajo.id = :diaTrabajoId ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)

        def lista = []
        long hora = horaDesde.time
        while(hora <= horaHasta.time){
            for ( CanaDetalle1 o: query.resultList ){
                long lapso = (hora - o.hora.time)
                if ( lapso==0 ){
                    lista << o.porcHumedad
                    break
                }
            }
            hora += (60*60*1000) //Incremento de una hora
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0

    }
}
