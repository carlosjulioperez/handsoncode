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
    maBri, maPol, maSac, maPur, maBri2,
    mbBri, mbPol, mbSac, mbPur, mbBri2,
    mcBri, mcPol, mcSac, mcPur, mcBri2
""")
@View(members=  """diaTrabajo;detalle1;detalle2;detalle3;detalle4;detalle5;detalle6;detalle7;detalle8""")
class Masas extends DiaTrabajoEditable {

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

    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        bri  [masas.promMaBri],
        pol  [masas.promMaPol],
        sac  [masas.promMaSac],
        pur  [masas.promMaPur],
        bri2 [masas.promMaBri2]
    """)
    Collection<MasasDetalle1>detalle1
    
    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        bri  [masas.promMbBri],
        pol  [masas.promMbPol],
        sac  [masas.promMbSac],
        pur  [masas.promMbPur],
        bri2 [masas.promMbBri2]
    """)
    Collection<MasasDetalle2>detalle2
    
    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,
        bri  [masas.promMcBri],
        pol  [masas.promMcPol],
        sac  [masas.promMcSac],
        pur  [masas.promMcPur],
        bri2 [masas.promMcBri2]
    """)
    Collection<MasasDetalle3>detalle3
    
    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties(""" hora, bri, pol, sac, pur, bri2 """)
    Collection<MasasDetalle4>detalle4

    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties(""" hora, bri, pol, sac, pur, bri2 """)
    Collection<MasasDetalle5>detalle5

    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties(""" hora, bri, pol, sac, pur, bri2 """)
    Collection<MasasDetalle6>detalle6

    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties(""" hora, bri, pol, sac, pur, bri2 """)
    Collection<MasasDetalle7>detalle7

    @OneToMany (mappedBy="masas", cascade=CascadeType.ALL)
    @ListProperties(""" hora, bri, pol, sac, pur, bri2 """)
    Collection<MasasDetalle8>detalle8
    
    BigDecimal getPromMaBri()  { return super.getPromedio(detalle1, "bri",  2) }
    BigDecimal getPromMaPol()  { return super.getPromedio(detalle1, "pol",  2) }
    BigDecimal getPromMaSac()  { return super.getPromedio(detalle1, "sac",  2) }
    BigDecimal getPromMaPur()  { return super.getPromedio(detalle1, "pur",  2) }
    BigDecimal getPromMaBri2() { return super.getPromedio(detalle1, "bri2", 2) }
    
    BigDecimal getPromMbBri()  { return super.getPromedio(detalle2, "bri",  2) }
    BigDecimal getPromMbPol()  { return super.getPromedio(detalle2, "pol",  2) }
    BigDecimal getPromMbSac()  { return super.getPromedio(detalle2, "sac",  2) }
    BigDecimal getPromMbPur()  { return super.getPromedio(detalle2, "pur",  2) }
    BigDecimal getPromMbBri2() { return super.getPromedio(detalle2, "bri2", 2) }
    
    BigDecimal getPromMcBri()  { return super.getPromedio(detalle3, "bri",  2) }
    BigDecimal getPromMcPol()  { return super.getPromedio(detalle3, "pol",  2) }
    BigDecimal getPromMcSac()  { return super.getPromedio(detalle3, "sac",  2) }
    BigDecimal getPromMcPur()  { return super.getPromedio(detalle3, "pur",  2) }
    BigDecimal getPromMcBri2() { return super.getPromedio(detalle3, "bri2", 2) }
    
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
