package ec.carper.ingenio.model

import ec.carper.ingenio.calculators.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora;
    mlReductores;
    calTab7SusRed;
    calPorcAzuRed;
""")
class TrashCanaDetalle2 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TrashCana trashCana  

    @Stereotype("TIME") @Column(length=5) @OnChange(TrashCanaDetalle2Action.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DefaultValueCalculator(CeroCalculator.class)
    @OnChange(TrashCanaDetalle2Action.class) @DisplaySize(6)
    BigDecimal mlReductores
    
    @ReadOnly @DisplaySize(6)
    BigDecimal calTab7SusRed
    
    @ReadOnly
    @Digits(integer=4, fraction=3) @DisplaySize(6)
    BigDecimal calPorcAzuRed

    BigDecimal getPorcAzuRed(String diaTrabajoId, java.sql.Timestamp hora){

        BigDecimal valor = 0

        Query query = getManager().createQuery("FROM TrashCanaDetalle2 WHERE trashCana.diaTrabajo.id = :diaTrabajoId ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)

        // println hora.toString() + " - "
        for ( TrashCanaDetalle2 o: query.resultList ){
            long lapso = (hora.time - o.hora.time) / ( 60 * 60 * 1000)
            // println "\t" + "(" + o.calPorcAzuRed + ") " + o.hora.toString() + " = " + lapso
            if (lapso ==0 || Math.abs(lapso) == 1){
                valor = o.calPorcAzuRed 
                break
            }
        }
        return valor
    }

}
