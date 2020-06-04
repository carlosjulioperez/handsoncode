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
    briCorr, bri, absFiltrada, absSinFiltrar, celda, rho,
    cedilla, briEle, color, turb, pol, humedad
""")
@View(members=  """diaTrabajo;detalle""")
class Grasshoper extends DiaTrabajoEditable {

    BigDecimal briCorr
    BigDecimal bri
    BigDecimal absFiltrada
    BigDecimal absSinFiltrar
    BigDecimal celda
    BigDecimal rho
    BigDecimal cedilla
    BigDecimal briEle
    BigDecimal color
    BigDecimal turb
    BigDecimal pol
    BigDecimal humedad
    
    @OneToMany (mappedBy="grasshoper", cascade=CascadeType.ALL)
    @ListProperties("""
        hora,    
        briCorr        [grasshoper.promBriCorr],
        bri            [grasshoper.promBri],
        absFiltrada    [grasshoper.promAbsFiltrada],
        absSinFiltrar  [grasshoper.promAbsSinFiltrar],
        celda          [grasshoper.promCelda],
        rho            [grasshoper.promRho],
        cedilla        [grasshoper.promCedilla],
        briEle         [grasshoper.promBriEle],
        color          [grasshoper.promColor],
        turb           [grasshoper.promTurb],
        pol            [grasshoper.promPol],
        humedad        [grasshoper.promHumedad]
    """)
    Collection<GrasshoperDetalle>detalle
    
    BigDecimal getPromBriCorr      () { return super.getPromedio(detalle, "briCorr", 2) }
    BigDecimal getPromBri          () { return super.getPromedio(detalle, "bri", 2) }
    BigDecimal getPromAbsFiltrada  () { return super.getPromedio(detalle, "absFiltrada", 3) }
    BigDecimal getPromAbsSinFiltrar() { return super.getPromedio(detalle, "absSinFiltrar", 3) }
    BigDecimal getPromCelda        () { return super.getPromedio(detalle, "celda", 2) }
    BigDecimal getPromRho          () { return super.getPromedio(detalle, "rho", 3) }
    BigDecimal getPromCedilla      () { return super.getPromedio(detalle, "cedilla", 6) }
    BigDecimal getPromBriEle       () { return super.getPromedio(detalle, "briEle", 2) }
    BigDecimal getPromColor        () { return super.getPromedio(detalle, "color", 2) }
    BigDecimal getPromTurb         () { return super.getPromedio(detalle, "turb", 2) }
    BigDecimal getPromPol          () { return super.getPromedio(detalle, "pol", 2) }
    BigDecimal getPromHumedad      () { return super.getPromedio(detalle, "humedad", 2) }
    
    void actualizar() throws ValidationException{
        try{

            this.briCorr       = promBriCorr
            this.bri           = promBri
            this.absFiltrada   = promAbsFiltrada
            this.absSinFiltrar = promAbsSinFiltrar
            this.celda         = promCelda
            this.rho           = promRho
            this.cedilla       = promCedilla
            this.briEle        = promBriEle
            this.color         = promColor
            this.turb          = promTurb
            this.pol           = promPol
            this.humedad       = promHumedad

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
