package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@View(members="""
    fecha;
    tqJDil{#
        tqJDilH1;
        tqJDilH2;
        tqJDilH3;
        tqJDilVt;

        tqJDilBrix;
        tqJDilSac;
        
        tqSacJDil;
        tqSacJDilPorc;
        tqSacJDilRho;
    }
    """
)
@Entity
class StockFabrica extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    // Tanque Jugo Diluido
    BigDecimal tqJDilH1
    BigDecimal tqJDilH2
    BigDecimal tqJDilH3
    BigDecimal tqJDilVt
    
    BigDecimal tqJDilBrix
    BigDecimal tqJDilSac
    
    BigDecimal tqSacJDil
    BigDecimal tqSacJDilPorc
    BigDecimal tqSacJDilRho
    
    // @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    // private void preGrabar() throws Exception {
    //     recalcularTotalParo()
    // }
    //
    // @PreUpdate
    // void recalcularTotalParo() {
    //     setTotalParo(sumaParo)
    // }
}
