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
    // diaTrabajo.descripcion, abs900Nm, turJClaro, polCachaza, briCachaza, humCachaza 
@Tab(properties="""
    diaTrabajo.fecha, abs900Nm, turJClaro, polCachaza, briCachaza, humCachaza 
""")
@View(members="""
    diaTrabajo;
    titAnaJugCla {detalle1}
    titAnaCac {detalle2}
""")
class Turbiedad extends Formulario {

    @Digits(integer=4, fraction=3) 
    BigDecimal abs900Nm
    BigDecimal turJClaro
    BigDecimal polCachaza
    BigDecimal briCachaza
    BigDecimal humCachaza

    @OneToMany (mappedBy="turbiedad", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        abs900Nm  [turbiedad.promAbs900Nm],
        turJClaro [turbiedad.promTurJClaro]
    """)
    Collection<TurbiedadDetalle1>detalle1
    
    BigDecimal getPromAbs900Nm () { return super.getPromedio(detalle1, "abs900Nm", 3) }
    BigDecimal getPromTurJClaro() { return super.getPromedio(detalle1, "turJClaro", 2) }
    
    @OneToMany (mappedBy="turbiedad", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        polCachaza [turbiedad.promPolCachaza],
        briCachaza [turbiedad.promBriCachaza],
        humCachaza [turbiedad.promHumCachaza]
    """)
    Collection<TurbiedadDetalle2>detalle2
    
    BigDecimal getPromPolCachaza() { return super.getPromedio(detalle2, "polCachaza", 2) }
    BigDecimal getPromBriCachaza() { return super.getPromedio(detalle2, "briCachaza", 2) }
    BigDecimal getPromHumCachaza() { return super.getPromedio(detalle2, "humCachaza", 2) }
    
    void actualizar() throws ValidationException{
        try{

            this.abs900Nm   = promAbs900Nm
            this.turJClaro  = promTurJClaro
            this.polCachaza = promPolCachaza
            this.briCachaza = promBriCachaza
            this.humCachaza = promHumCachaza
  
            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
