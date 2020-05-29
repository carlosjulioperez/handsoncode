package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    diaTrabajo.descripcion,
    bri, pol, sac, pur, bri2
""")
@View(members=  """diaTrabajo;detalle""")
class TqFundidor extends DiaTrabajoEditable {

    BigDecimal bri
    BigDecimal pol
    BigDecimal sac
    BigDecimal pur
    BigDecimal bri2

    @OneToMany (mappedBy="tqFundidor", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        bri  [tqFundidor.promBri],
        pol  [tqFundidor.promPol],
        sac  [tqFundidor.promSac],
        pur  [tqFundidor.promPur],
        bri2 [tqFundidor.promBri2]
    """)
    Collection<TqFundidorDetalle>detalle
    
    BigDecimal getPromBri()  { return super.getPromedio(detalle, "bri", 2) }
    BigDecimal getPromPol()  { return super.getPromedio(detalle, "pol", 2) }
    BigDecimal getPromSac()  { return super.getPromedio(detalle, "sac", 2) }
    BigDecimal getPromPur()  { return super.getPromedio(detalle, "pur", 2) }
    BigDecimal getPromBri2() { return super.getPromedio(detalle, "bri2", 2) }
    
    void save() throws ValidationException{
        try{

            this.bri  = promBri
            this.pol  = promPol
            this.sac  = promSac
            this.pur  = promPur
            this.bri2 = promBri2

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
