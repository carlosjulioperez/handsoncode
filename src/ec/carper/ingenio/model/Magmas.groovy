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
    mbBri, mbPol, mbSac, mbPur, mbBri2;
    mcBri, mcPol, mcSac, mcPur, mcBri2;
    mrBri, mrPol, mrSac, mrPur, mrBri2
""")
@View(members="""
    diaTrabajo;
    titAnaMatProMie{ detalle }
""")
class Magmas extends Formulario {

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
    BigDecimal mrBri
    BigDecimal mrPol
    BigDecimal mrSac
    BigDecimal mrPur
    BigDecimal mrBri2

    @OneToMany (mappedBy="magmas", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        mbBri  [magmas.promMbBri],
        mbPol  [magmas.promMbPol],
        mbSac  [magmas.promMbSac],
        mbPur  [magmas.promMbPur],
        mbBri2 [magmas.promMbBri2],
        mcBri  [magmas.promMcBri],
        mcPol  [magmas.promMcPol],
        mcSac  [magmas.promMcSac],
        mcPur  [magmas.promMcPur],
        mcBri2 [magmas.promMcBri2],
        mrBri  [magmas.promMrBri],
        mrPol  [magmas.promMrPol],
        mrSac  [magmas.promMrSac],
        mrPur  [magmas.promMrPur],
        mrBri2 [magmas.promMrBri2]
    """)
    Collection<MagmasDetalle>detalle
    
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
    BigDecimal getPromMrBri () { return super.getPromedio(detalle, "mrBri", 2) }
    BigDecimal getPromMrPol () { return super.getPromedio(detalle, "mrPol", 2) }
    BigDecimal getPromMrSac () { return super.getPromedio(detalle, "mrSac", 2) }
    BigDecimal getPromMrPur () { return super.getPromedio(detalle, "mrPur", 2) }
    BigDecimal getPromMrBri2() { return super.getPromedio(detalle, "mrBri2", 2) }
    
    void actualizar() throws ValidationException{
        try{

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
            this.mrBri  = promMrBri
            this.mrPol  = promMrPol
            this.mrSac  = promMrSac
            this.mrPur  = promMrPur
            this.mrBri2 = promMrBri2

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
