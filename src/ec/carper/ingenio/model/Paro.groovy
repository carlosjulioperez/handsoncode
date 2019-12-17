package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@Tab(properties="""fecha,observaciones,totalParo""")
@View(members=  """fecha;observaciones;detalles""")
class Paro extends Identifiable{

    @Version
    private Integer version;

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    @Column(length=100) @Required
    String observaciones 

    @ElementCollection
    @ListProperties("""
        area,descripcion,fechaInicio,fechaFin,
        calParo[paro.sumaParo]
    """)
    Collection<ParoDetalle>detalles
    
    String totalParo
    
    String getSumaParo(){
        try{
            def timeList = []
            detalles.each { timeList << it.calParo }
            //prinln ">>>>>>>>>>>>>>> " + timeList.size()
            def duration = Util.instance.getDuration(timeList)
            def valor = Util.instance.toTimeString(duration)
            return valor
        }catch (Exception e){
           return ""
        }
    }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcularTotalParo()
    }

    @PreUpdate
    void recalcularTotalParo() {
        setTotalParo(sumaParo)
    }
}
