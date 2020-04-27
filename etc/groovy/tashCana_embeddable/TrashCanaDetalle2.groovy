package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Embeddable
@View(members="""#
    hora;
    mlReductores;
    calTab7SusRed;
    calPorcAzuRed;
""")
class TrashCanaDetalle2{
    
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @OnChange(TrashCanaCalculosAction.class)
    @Required
    BigDecimal mlReductores
    
    @ReadOnly
    BigDecimal calTab7SusRed

    @ReadOnly
    @Digits(integer=4, fraction=3)
    BigDecimal calPorcAzuRed
    
    /*
    @Depends("mlReductores") //Propiedad calculada
    BigDecimal getCalTab7SusRed(){
        return (mlReductores) ? (mlReductores/4).setScale(2, BigDecimal.ROUND_HALF_UP): 0
    }
    /*

    /*
    @Digits(integer=4, fraction=3)
    @Depends("calTab7SusRed") //Propiedad calculada
    BigDecimal getCalPorcAzuRed(){
        //TODO
        //BigDecimal valor = (calTab7SusRed) ? new BrixDensidadTitSus().getSusRed(susRed): 0
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // def tupla = lista.find { it[0] <= calTab7SusRed}
        // BigDecimal valor = tupla[1] 
        def valor = 0
        println valor
        return 0
    }
    */

}
