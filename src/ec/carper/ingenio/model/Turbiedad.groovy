package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    diaTrabajo.descripcion, abs900Nm, turJClaro, polCachaza
""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
class Turbiedad extends DiaTrabajoEditable {

    @Digits(integer=4, fraction=3) 
    BigDecimal abs900Nm
    BigDecimal turJClaro
    BigDecimal polCachaza

    @OneToMany (mappedBy="turbiedad", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        abs900Nm  [turbiedad.promAbs900Nm],
        turJClaro [turbiedad.promTurJClaro]
    """)
    Collection<TurbiedadDetalle1>detalle1
    
    BigDecimal getPromAbs900Nm () { return super.getPromedio(detalle1, "abs900Nm", 3) }
    BigDecimal getPromTurJClaro() { return super.getPromedio(detalle1, "turJClaro", 2) }
    
    @OneToMany (mappedBy="turbiedad", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        polCachaza [turbiedad.promPolCachaza]
    """)
    Collection<TurbiedadDetalle2>detalle2
    
    BigDecimal getPromPolCachaza() { return super.getPromedio(detalle2, "polCachaza", 2) }
    
    void save() throws ValidationException{
        try{

            this.abs900Nm   = promAbs900Nm
            this.turJClaro  = promTurJClaro
            this.polCachaza = promPolCachaza
  
            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
