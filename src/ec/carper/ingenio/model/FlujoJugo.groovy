package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*

import java.time.LocalDate

@Entity
@Tab(properties="""
    diaTrabajo.descripcion, descripcion,
    tot,
    brixJDil,
    tonJugo 
""")
@View(members="""
    diaTrabajo, descripcion;
    titFluJugMol { detalle }
""")
class FlujoJugo extends Formulario {
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    Integer tot
    BigDecimal brixJDil
    
    @Digits(integer=12, fraction=6)
    BigDecimal tonJugo

    @OneToMany (mappedBy="flujoJugo", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        tot      [flujoJugo.sumTot],
        brixJDil [flujoJugo.promBrixJDil],
        tonJugo  [flujoJugo.sumTonJugo]
    """)
    Collection<FlujoJugoDetalle>detalle

    BigDecimal getPromBrixJDil(){
        return super.getPromedio(detalle, "brixJDil", 2)
    }
    
    BigDecimal getSumTot(){
        return super.getSuma(detalle, "tot", 2)
    }
    
    BigDecimal getSumTonJugo(){
        return super.getSuma(detalle, "tonJugo", 6)
    }

    void actualizar() throws ValidationException{
        try{

            this.tot      = sumTot   
            this.brixJDil = promBrixJDil
            this.tonJugo  = sumTonJugo

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
