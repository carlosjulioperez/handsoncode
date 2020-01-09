package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Embeddable
class JugoDetalle{

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp fecha

    @DisplaySize(10)
    BigDecimal jeBri
    @DisplaySize(10)
    BigDecimal jePol
    @Depends("jeBri,jePol") //Propiedad calculada
    BigDecimal getCalJeSac(){
        return (jeBri && jePol) ? getSac(jeBri, jePol) : 0
    }
    @Depends("jeBri,calJeSac ") //Propiedad calculada
    BigDecimal getCalJePur(){
        return (calJeSac && jeBri) ? getPur(calJeSac, jeBri) : 0
    }

    BigDecimal jdBri
    BigDecimal jdPol
    @Depends("jdBri,jdPol") //Propiedad calculada
    BigDecimal getCalJdSac(){
        return (jdBri && jdPol) ? getSac(jdBri, jdPol) : 0
    }
    @Depends("jdBri,calJdSac ") //Propiedad calculada
    BigDecimal getCalJdPur(){
        return (calJdSac && jdBri) ? getPur(calJdSac, jdBri) : 0
    }

    BigDecimal jcBri
    BigDecimal jcPol
    @Depends("jcBri,jcPol") //Propiedad calculada
    BigDecimal getCalJcSac(){
        return (jcBri && jcPol) ? getSac(jcBri, jcPol) : 0
    }
    @Depends("jcBri,calJcSac ") //Propiedad calculada
    BigDecimal getCalJcPur(){
        return (calJcSac && jcBri) ? getPur(calJcSac, jcBri) : 0
    }

    BigDecimal jnBri
    BigDecimal jnPol
    @Depends("jnBri,jnPol") //Propiedad calculada
    BigDecimal getCalJnSac(){
        return (jnBri && jnPol) ? getSac(jnBri, jnPol) : 0
    }
    @Depends("jnBri,calJnSac ") //Propiedad calculada
    BigDecimal getCalJnPur(){
        return (calJnSac && jnBri) ? getPur(calJnSac, jnBri) : 0
    }

    BigDecimal jrBri
    BigDecimal jrPol
    @Depends("jrBri,jrPol") //Propiedad calculada
    BigDecimal getCalJrSac(){
        return (jrBri && jrPol) ? getSac(jrBri, jrPol) : 0
    }
    @Depends("jrBri,calJrSac ") //Propiedad calculada
    BigDecimal getCalJrPur(){
        return (calJrSac && jrBri) ? getPur(calJrSac, jrBri) : 0
    }

    BigDecimal jfBri
    BigDecimal jfPol
    @Depends("jfBri,jfPol") //Propiedad calculada
    BigDecimal getCalJfSac(){
        return (jfBri && jfPol) ? getSac(jfBri, jfPol) : 0
    }
    @Depends("jfBri,calJfSac ") //Propiedad calculada
    BigDecimal getCalJfPur(){
        return (calJfSac && jfBri) ? getPur(calJfSac, jfBri) : 0
    }

    private BigDecimal getSac(BigDecimal bri, BigDecimal pol){
        return (pol*0.26)/(0.9971883+0.00385310413*bri+0.0000132218495*bri*bri+0.00000004655189*bri*bri*bri)
    }

    private BigDecimal getPur(BigDecimal sac, BigDecimal bri){
        return (sac/bri)*100 
    }
}
