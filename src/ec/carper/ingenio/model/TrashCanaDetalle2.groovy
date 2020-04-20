package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
@View(members="""#
    hora;
    mlReductores;
    calTab7SusRed;
    calPorcAzuRed;
""")
class TrashCanaDetalle2 extends Identifiable {
    
    @Transient
    def getLista(){
        return new BrixDensidadTitSus().getSqlToList() 
    }
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TrashCana trashCana  

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp hora
    
    @Required
    BigDecimal mlReductores
    
    BigDecimal valTab7SusRed
    
    @Depends("mlReductores") //Propiedad calculada
    BigDecimal getCalTab7SusRed(){
        def valor = (mlReductores) ? (mlReductores/4).setScale(2, BigDecimal.ROUND_HALF_UP): 0
        setValTab7SusRed(valor)
        return valor
    }
    
    @Digits(integer=4, fraction=3)
    BigDecimal valPorcAzuRed
        
    //setValPorcAzuRed(calPorcAzuRed)

    @Digits(integer=4, fraction=3)
    @Depends("calTab7SusRed") //Propiedad calculada
    BigDecimal getCalPorcAzuRed(){
        //def susRed = (calTab7SusRed) ? new BrixDensidadTitSus().getSusRed(calTab7SusRed): 0

        //def result = lista.find { it[0] <= 5.10 }

        def tupla = lista.find { it[0] <= calTab7SusRed }
        BigDecimal susRed = tupla[1] 
    
        // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        // println susRed
        return (calTab7SusRed) ? susRed: 0
    }
    
    @PrePersist // ejecutado justo antes de grabar el objeto por primera vez
    private void onPrePersist() throws Exception {
        recalcular()
    }

    @PreUpdate
    void recalcular() {
        setValPorcAzuRed(calPorcAzuRed)
    }

}
