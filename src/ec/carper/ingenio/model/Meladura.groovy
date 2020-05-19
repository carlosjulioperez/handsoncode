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
    mcrBri, mcrPol, mcrSac, mcrPur, mcrBri2;
    mclBri, mclPol, mclSac, mclPur, mclBri2
""")
@View(members=  """diaTrabajo;detalle""")
class Meladura extends DiaTrabajoEditable {

    BigDecimal mcrBri
    BigDecimal mcrPol
    BigDecimal mcrSac
    BigDecimal mcrPur
    BigDecimal mcrBri2
    BigDecimal mclBri
    BigDecimal mclPol
    BigDecimal mclSac
    BigDecimal mclPur
    BigDecimal mclBri2

    @OneToMany (mappedBy="meladura", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        mcrBri  [meladura.promMcrBri],
        mcrPol  [meladura.promMcrPol],
        mcrSac  [meladura.promMcrSac],
        mcrPur  [meladura.promMcrPur],
        mcrBri2 [meladura.promMcrBri2],
        mclBri  [meladura.promMclBri],
        mclPol  [meladura.promMclPol],
        mclSac  [meladura.promMclSac],
        mclPur  [meladura.promMclPur],
        mclBri2 [meladura.promMclBri2]
    """)
    Collection<MeladuraDetalle>detalle
    
    BigDecimal getPromMcrBri()  { return super.getPromedio(detalle, "mcrBri",  2) }
    BigDecimal getPromMcrPol()  { return super.getPromedio(detalle, "mcrPol",  2) }
    BigDecimal getPromMcrSac()  { return super.getPromedio(detalle, "mcrSac",  2) }
    BigDecimal getPromMcrPur()  { return super.getPromedio(detalle, "mcrPur",  2) }
    BigDecimal getPromMcrBri2() { return super.getPromedio(detalle, "mcrBri2", 2) }
    BigDecimal getPromMclBri()  { return super.getPromedio(detalle, "mclBri",  2) }
    BigDecimal getPromMclPol()  { return super.getPromedio(detalle, "mclPol",  2) }
    BigDecimal getPromMclSac()  { return super.getPromedio(detalle, "mclSac",  2) }
    BigDecimal getPromMclPur()  { return super.getPromedio(detalle, "mclPur",  2) }
    BigDecimal getPromMclBri2() { return super.getPromedio(detalle, "mclBri2", 2) }
    
    void save() throws ValidationException{
        try{

            this.mcrBri  = promMcrBri
            this.mcrPol  = promMcrPol
            this.mcrSac  = promMcrSac
            this.mcrPur  = promMcrPur
            this.mcrBri2 = promMcrBri2
            this.mclBri  = promMclBri
            this.mclPol  = promMclPol
            this.mclSac  = promMclSac
            this.mclPur  = promMclPur
            this.mclBri2 = promMclBri2
  
            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
