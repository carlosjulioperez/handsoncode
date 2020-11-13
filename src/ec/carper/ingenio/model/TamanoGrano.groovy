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
@Tab(properties="""diaTrabajo.fecha""")
@View(members="""
    diaTrabajo;
    titTamGra { detalle }
""")
class TamanoGrano extends Formulario {

    @OneToMany (mappedBy="tamanoGrano", cascade=CascadeType.ALL)
    @ListProperties("""tipo, aberturaMedia, coeficienteVariacion""")
    Collection<TamanoGranoDetalle>detalle

}
