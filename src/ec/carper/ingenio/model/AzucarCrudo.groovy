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
    diaTrabajo.fecha,
    briCorr, bri, absFiltrada, absSinFiltrar, celda, rho,
    cedilla, briEle, color, turb
""")
@View(members="""
    diaTrabajo;
    titAnaEspAzuProTer { detalle }
""")
class AzucarCrudo extends Formulario {

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
    
    @OneToMany (mappedBy="azucarCrudo", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,    
        briCorr        [azucarCrudo.promBriCorr],
        bri            [azucarCrudo.promBri],
        absFiltrada    [azucarCrudo.promAbsFiltrada],
        absSinFiltrar  [azucarCrudo.promAbsSinFiltrar],
        celda          [azucarCrudo.promCelda],
        rho            [azucarCrudo.promRho],
        cedilla        [azucarCrudo.promCedilla],
        briEle         [azucarCrudo.promBriEle],
        color          [azucarCrudo.promColor],
        turb           [azucarCrudo.promTurb]
    """)
    Collection<AzucarCrudoDetalle>detalle
    
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

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
