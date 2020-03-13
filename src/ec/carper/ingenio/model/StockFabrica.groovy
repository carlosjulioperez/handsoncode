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
        calTqSacJDil, calTqJDilBri;
        tqSacJDilPorc, calTqJDilSac;
        calTqSacJDilRho;

        tqJDilH1;
        tqJDilH2;
        tqJDilH3; 
        calTqJDilVt;
    }
    tqJCla{#
        calTqSacJCla, calTqJClaBri;
        tqSacJClaPorcN, calTqJClaSac;
        tqSacJClaPorcV;
        calTqSacJClaRho;

        tqJClaH1;
        tqJClaH2;
        tqJClaH3;
        calTqJClaV1;
        tqJClaOSlash;
        tqJClaH;
        calTqJClaV2;
        calTqJClaVt;
    }
    """
)

// Ha sido imposible ejecutar la acción Init view: tqSacJClaPorcN no se reconoce como propiedad de la vista tqJCla de StockFabrica 

@Entity
class StockFabrica extends Identifiable{

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha
    
    // Tanque Jugo Diluido 
    @Depends("fecha") //Propiedad calculada
    BigDecimal getCalTqJDilBri(){
        return new Jugo().getAvgField(fecha, "avgJdBri")
    }

    @Depends("fecha") //Propiedad calculada
    BigDecimal getCalTqJDilSac(){
        return new Jugo().getAvgField(fecha, "avgJdSac")
    }

    @Depends("calTqJDilVt, calTqJDilSac, calTqSacJDilRho") //Propiedad calculada
    BigDecimal getCalTqSacJDil(){
        //=((K6*X6/1000)*(Q5/100))
        return (calTqJDilVt && calTqJDilSac && calTqSacJDilRho) ? (calTqJDilVt *  calTqSacJDilRho / 1000 * calTqJDilSac / 100 ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }
    
    BigDecimal tqSacJDilPorc
    
    @Depends("calTqJDilBri") //Propiedad calculada
    BigDecimal getCalTqSacJDilRho(){
        return new BrixDensidadWp().getP(calTqJDilBri)
    }

    // Tanque Jugo Diluido
    BigDecimal tqJDilH1
    BigDecimal tqJDilH2
    BigDecimal tqJDilH3
    
    @Depends("tqJDilH1,tqJDilH2,tqJDilH3,tqSacJDilPorc") //Propiedad calculada
    BigDecimal getCalTqJDilVt(){
        //i =+(K5*K4*K3)*W5/100
        return (tqJDilH1 && tqJDilH2 && tqJDilH3 && tqSacJDilPorc) ? (tqJDilH1 * tqJDilH2 * tqJDilH3 * tqSacJDilPorc / 100 ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }

    // --------------------------------------------------
    // Tanque Jugo Claro 

    @Depends("fecha") //Propiedad calculada
    BigDecimal getCalTqJClaBri(){
        return new Jugo().getAvgField(fecha, "avgJcBri")
    }

    @Depends("fecha") //Propiedad calculada
    BigDecimal getCalTqJClaSac(){
        return new Jugo().getAvgField(fecha, "avgJcSac")
    }

    @Depends("calTqJClaVt, calTqSacJClaRho, calTqJClaSac") //Propiedad calculada
    BigDecimal getCalTqSacJCla(){
        //=(AQ9*AV7/1000)*(AJ4/100)
        return (calTqJClaVt && calTqSacJClaRho && calTqJClaSac) ? (calTqJClaVt  * calTqSacJClaRho / 1000 * calTqJClaSac/100 ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }
    
    BigDecimal tqSacJClaPorcN
    BigDecimal tqSacJClaPorcV
    
    @Depends("calTqJClaBri") //Propiedad calculada
    BigDecimal getCalTqSacJClaRho(){
        return new BrixDensidadWp().getP(calTqJClaBri)
    }

    BigDecimal tqJClaH1
    BigDecimal tqJClaH2
    BigDecimal tqJClaH3

    @Depends("tqJClaH1,tqJClaH2,tqJClaH3,tqSacJClaPorcN") //Propiedad calculada
    BigDecimal getCalTqJClaV1(){
        //i =+(K5*K4*K3)*W5/100
        return (tqJClaH1 && tqJClaH2 && tqJClaH3 && tqSacJClaPorcN) ? (tqJClaH1 * tqJClaH2 * tqJClaH3 * tqSacJClaPorcN / 100 ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }
    
    BigDecimal tqJClaOSlash
    BigDecimal tqJClaH

    @Depends("tqJClaOSlash,tqJClaH,tqSacJClaPorcV") //Propiedad calculada
    BigDecimal getCalTqJClaV2(){
        //=(3,1416*((AQ6/2)*(AQ6/2))*AQ7)*AV6/100
        return (tqJClaOSlash && tqJClaH && tqSacJClaPorcV) ? (3.1416 * tqJClaOSlash/2 * tqJClaOSlash/2 * tqJClaH * tqSacJClaPorcV/100  ).setScale(2, BigDecimal.ROUND_HALF_UP) : 0
    }

    @Depends("calTqJClaV1,calTqJClaV2") //Propiedad calculada
    BigDecimal getCalTqJClaVt(){
        //=+AQ8+AQ5
        return (calTqJClaV1 && calTqJClaV2) ? (calTqJClaV1 + calTqJClaV2) : 0
    }

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
