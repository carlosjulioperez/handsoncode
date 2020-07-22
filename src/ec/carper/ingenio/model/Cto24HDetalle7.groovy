package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    modulo, turno;
    horaS, hora;
    tipo, mlTitu;
    fd, ppm
""")
class Cto24HDetalle7 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Modulo modulo

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @NoCreate @NoModify
    Turno turno
    
    @Stereotype("TIME") @OnChange(Cto24HDetalle7Action.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora

    Tipo tipo 
    enum Tipo { TIPO_1, TIPO_2 }
    
    @OnChange(Cto24HDetalle7Action.class) @DisplaySize(6)
    BigDecimal mlTitu
    
    @OnChange(Cto24HDetalle7Action.class) @DisplaySize(6)
    BigDecimal fd
    
    @ReadOnly @DisplaySize(6)
    BigDecimal ppm
    
}
