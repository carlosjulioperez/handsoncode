package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

@Entity
// @Tab(properties="""fecha,observaciones,totalParo""")
// @View(members=  """fecha;observaciones;detalles""")
class Jugo extends Identifiable{

    @Version
    private Integer version;

    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    @Required
    LocalDate fecha

    @ElementCollection
    @ListProperties("""
        fecha,
        jeBri,jePol,calJeSac,calJePur,
        jdBri,jdPol,calJdSac,calJdPur,
        jcBri,jcPol,calJcSac,calJcPur,
        jnBri,jnPol,calJnSac,calJnPur,
        jrBri,jrPol,calJrSac,calJrPur,
        jfBri,jfPol,calJfSac,calJfPur
    """)
    Collection<JugoDetalle>detalles
}
