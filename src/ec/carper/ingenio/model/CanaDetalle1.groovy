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
    horaS, hora;
    wH2O, wCana;
    polReal, brixExtracto;
    polExtracto, tamizVacioM0;
    muestraHumM1, muestraSecaM2;
    porcHumedad, brix;
    porcFibra, porcSacarosa;
    pureza, nSac;
    aR, porcArNsac;
    pH
""")
class CanaDetalle1 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cana cana
    
    @Stereotype("TIME") @Column(length=5) @OnChange(CanaDetalle1Action.class) @Required
    String horaS

    @OnChange(CanaDetalle1Action.class)
    @Stereotype("DATETIME") @ReadOnly @Required
    //@Stereotype("FECHAHORA") @Required
    java.sql.Timestamp hora

    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal wH2O

    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal wCana

    @ReadOnly @DisplaySize(6)
    BigDecimal polReal

    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal brixExtracto
    
    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal polExtracto

    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal tamizVacioM0

    @ReadOnly @DisplaySize(6)
    BigDecimal muestraHumM1
    
    @OnChange(CanaDetalle1Action.class) @DisplaySize(6)
    BigDecimal muestraSecaM2

    @ReadOnly @DisplaySize(6)
    BigDecimal porcHumedad

    @ReadOnly @DisplaySize(6)
    BigDecimal brix
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcFibra
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcSacarosa
    
    @ReadOnly @DisplaySize(6)
    BigDecimal pureza
    
    @ReadOnly @DisplaySize(6)
    BigDecimal nSac
    
    @ReadOnly @DisplaySize(6)
    BigDecimal aR
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcArNsac
    
    @DisplaySize(6)
    BigDecimal pH

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

    // Obtener wH2O wCana
    def getCampos(String diaTrabajoId, java.sql.Timestamp hora){
        def resultado = []

        Query query = getManager().createQuery("FROM CanaDetalle1 WHERE cana.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        def lista = query.resultList
        if (lista){
            def o = lista[0]
            resultado << o.wH2O
            resultado << o.wCana
        }
        return resultado
    }

}
