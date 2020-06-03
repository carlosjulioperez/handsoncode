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
    maBri, maPol, maSac, maPur, maBri2;
    mbBri, mbPol, mbSac, mbPur, mbBri2;
    mcBri, mcPol, mcSac, mcPur, mcBri2
""")
@View(members=  """diaTrabajo;detalle""")
class MielesNutsch extends DiaTrabajoEditable {

    BigDecimal maBri
    BigDecimal maPol
    BigDecimal maSac
    BigDecimal maPur
    BigDecimal maBri2
    BigDecimal mbBri
    BigDecimal mbPol
    BigDecimal mbSac
    BigDecimal mbPur
    BigDecimal mbBri2
    BigDecimal mcBri
    BigDecimal mcPol
    BigDecimal mcSac
    BigDecimal mcPur
    BigDecimal mcBri2

    @OneToMany (mappedBy="mielesNutsch", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        maBri  [mielesNutsch.promMaBri],
        maPol  [mielesNutsch.promMaPol],
        maSac  [mielesNutsch.promMaSac],
        maPur  [mielesNutsch.promMaPur],
        maBri2 [mielesNutsch.promMaBri2],
        mbBri  [mielesNutsch.promMbBri],
        mbPol  [mielesNutsch.promMbPol],
        mbSac  [mielesNutsch.promMbSac],
        mbPur  [mielesNutsch.promMbPur],
        mbBri2 [mielesNutsch.promMbBri2],
        mcBri  [mielesNutsch.promMcBri],
        mcPol  [mielesNutsch.promMcPol],
        mcSac  [mielesNutsch.promMcSac],
        mcPur  [mielesNutsch.promMcPur],
        mcBri2 [mielesNutsch.promMcBri2]
    """)
    Collection<MielesNutschDetalle>detalle
    
    BigDecimal getPromMaBri()  { return super.getPromedio(detalle, "maBri", 2) }
    BigDecimal getPromMaPol()  { return super.getPromedio(detalle, "maPol", 2) }
    BigDecimal getPromMaSac()  { return super.getPromedio(detalle, "maSac", 2) }
    BigDecimal getPromMaPur()  { return super.getPromedio(detalle, "maPur", 2) }
    BigDecimal getPromMaBri2() { return super.getPromedio(detalle, "maBri2", 2) }
    BigDecimal getPromMbBri()  { return super.getPromedio(detalle, "mbBri", 2) }
    BigDecimal getPromMbPol()  { return super.getPromedio(detalle, "mbPol", 2) }
    BigDecimal getPromMbSac()  { return super.getPromedio(detalle, "mbSac", 2) }
    BigDecimal getPromMbPur()  { return super.getPromedio(detalle, "mbPur", 2) }
    BigDecimal getPromMbBri2() { return super.getPromedio(detalle, "mbBri2", 2) }
    BigDecimal getPromMcBri () { return super.getPromedio(detalle, "mcBri", 2) }
    BigDecimal getPromMcPol () { return super.getPromedio(detalle, "mcPol", 2) }
    BigDecimal getPromMcSac () { return super.getPromedio(detalle, "mcSac", 2) }
    BigDecimal getPromMcPur () { return super.getPromedio(detalle, "mcPur", 2) }
    BigDecimal getPromMcBri2() { return super.getPromedio(detalle, "mcBri2", 2) }
    void actualizar() throws ValidationException{
        try{

            this.maBri  = promMaBri
            this.maPol  = promMaPol
            this.maSac  = promMaSac
            this.maPur  = promMaPur
            this.maBri2 = promMaBri2
            this.mbBri  = promMbBri
            this.mbPol  = promMbPol
            this.mbSac  = promMbSac
            this.mbPur  = promMbPur
            this.mbBri2 = promMbBri2
            this.mcBri  = promMcBri
            this.mcPol  = promMcPol
            this.mcSac  = promMcSac
            this.mcPur  = promMcPur
            this.mcBri2 = promMcBri2

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
