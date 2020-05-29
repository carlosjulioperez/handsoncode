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
    mfBri, mfPol, mfSac, mfPur, mfBri2;
    mrBri, mrPol, mrSac, mrPur, mrBri2;
    mpBri, mpPol, mpSac, mpPur, mpBri2
""")
@View(members=  """diaTrabajo;detalle""")
class Mieles extends DiaTrabajoEditable {

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
    BigDecimal mfBri
    BigDecimal mfPol
    BigDecimal mfSac
    BigDecimal mfPur
    BigDecimal mfBri2
    BigDecimal mrBri
    BigDecimal mrPol
    BigDecimal mrSac
    BigDecimal mrPur
    BigDecimal mrBri2
    BigDecimal mpBri
    BigDecimal mpPol
    BigDecimal mpSac
    BigDecimal mpPur
    BigDecimal mpBri2

    @OneToMany (mappedBy="mieles", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        maBri  [mieles.promMaBri],
        maPol  [mieles.promMaPol],
        maSac  [mieles.promMaSac],
        maPur  [mieles.promMaPur],
        maBri2 [mieles.promMaBri2],
        mbBri  [mieles.promMbBri],
        mbPol  [mieles.promMbPol],
        mbSac  [mieles.promMbSac],
        mbPur  [mieles.promMbPur],
        mbBri2 [mieles.promMbBri2],
        mfBri  [mieles.promMfBri],
        mfPol  [mieles.promMfPol],
        mfSac  [mieles.promMfSac],
        mfPur  [mieles.promMfPur],
        mfBri2 [mieles.promMfBri2],
        mrBri  [mieles.promMrBri],
        mrPol  [mieles.promMrPol],
        mrSac  [mieles.promMrSac],
        mrPur  [mieles.promMrPur],
        mrBri2 [mieles.promMrBri2],
        mpBri  [mieles.promMpBri],
        mpPol  [mieles.promMpPol],
        mpSac  [mieles.promMpSac],
        mpPur  [mieles.promMpPur],
        mpBri2 [mieles.promMpBri2]
    """)
    Collection<MielesDetalle>detalle
    
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
    BigDecimal getPromMfBri () { return super.getPromedio(detalle, "mfBri", 2) }
    BigDecimal getPromMfPol () { return super.getPromedio(detalle, "mfPol", 2) }
    BigDecimal getPromMfSac () { return super.getPromedio(detalle, "mfSac", 2) }
    BigDecimal getPromMfPur () { return super.getPromedio(detalle, "mfPur", 2) }
    BigDecimal getPromMfBri2() { return super.getPromedio(detalle, "mfBri2", 2) }
    BigDecimal getPromMrBri () { return super.getPromedio(detalle, "mrBri", 2) }
    BigDecimal getPromMrPol () { return super.getPromedio(detalle, "mrPol", 2) }
    BigDecimal getPromMrSac () { return super.getPromedio(detalle, "mrSac", 2) }
    BigDecimal getPromMrPur () { return super.getPromedio(detalle, "mrPur", 2) }
    BigDecimal getPromMrBri2() { return super.getPromedio(detalle, "mrBri2", 2) }
    BigDecimal getPromMpBri () { return super.getPromedio(detalle, "mpBri", 2) }
    BigDecimal getPromMpPol () { return super.getPromedio(detalle, "mpPol", 2) }
    BigDecimal getPromMpSac () { return super.getPromedio(detalle, "mpSac", 2) }
    BigDecimal getPromMpPur () { return super.getPromedio(detalle, "mpPur", 2) }
    BigDecimal getPromMpBri2() { return super.getPromedio(detalle, "mpBri2", 2) }
    
    void save() throws ValidationException{
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
            this.mfBri  = promMfBri
            this.mfPol  = promMfPol
            this.mfSac  = promMfSac
            this.mfPur  = promMfPur
            this.mfBri2 = promMfBri2
            this.mrBri  = promMrBri
            this.mrPol  = promMrPol
            this.mrSac  = promMrSac
            this.mrPur  = promMrPur
            this.mrBri2 = promMrBri2
            this.mpBri  = promMpBri
            this.mpPol  = promMpPol
            this.mpSac  = promMpSac
            this.mpPur  = promMpPur
            this.mpBri2 = promMpBri2

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
