package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""#
    horaI, fechaInicio;
    horaF, fechaFin;
    totalParo, area;
    descripcion
""")
class ParoDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Paro paro
    
    // https://github.com/mariuszs/openxava/blob/master/source/src/test/java/org/openxava/test/model/Clerk.java
    @Stereotype("TIME") @OnChange(ParoDetalleAction.class) @Required
    String horaI

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp fechaInicio

    @Stereotype("TIME") @OnChange(ParoDetalleAction.class) @Required
    String horaF

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp fechaFin

    @Column(length=8) @ReadOnly
    String totalParo
    
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList @NoCreate @NoModify
    Area area

    @Column(length=100) @DisplaySize(50) @Required
    String descripcion

}
