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
    diaTrabajo.descripcion, cteN1, cteN2, jdFosfatos, jcFosfatos
""")
@View(members="""
    diaTrabajo; 
    cteN1, cteN2; 
    titAnaMatProFosJug { detalle }
""")
class Fosfatos extends Formulario {

    @Digits(integer=3, fraction=4)
    BigDecimal cteN1 

    @Digits(integer=3, fraction=4)
    BigDecimal cteN2

    @Digits(integer=3, fraction=3)
    BigDecimal jdFosfatos
    
    @Digits(integer=3, fraction=3)
    BigDecimal jcFosfatos

    @OneToMany (mappedBy="fosfatos", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        jdAbsorbancia, jdMlMuestra, jdMgP, jdFosfatos [fosfatos.promJdFosfatos],
        jcAbsorbancia, jcMlMuestra, jcMgP, jcFosfatos [fosfatos.promJcFosfatos]
    """)
    Collection<FosfatosDetalle>detalle
    
    BigDecimal getPromJdFosfatos() { return super.getPromedio(detalle, "jdFosfatos",  3) }
    BigDecimal getPromJcFosfatos() { return super.getPromedio(detalle, "jcFosfatos",  3) }
    
    void actualizar() throws ValidationException{
        try{

            this.jdFosfatos = promJdFosfatos
            this.jcFosfatos = promJcFosfatos
  
            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
