package ec.carper.ingenio.model 

import ec.carper.ingenio.actions.BtuLbBagazoAction
import ec.carper.ingenio.util.Calculo

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""diaTrabajo.descripcion""")
@View(members="""#
    diaTrabajo;
    pHum,pCrisol;pCriCen,pMtra;porcHumedad,porcCenBs;porcCenBh,pcBtuLb
""")
class BtuLbBagazo extends DiaTrabajoEditable {

    @OnChange(BtuLbBagazoAction.class) @Digits(integer=2, fraction=3) 
    @DisplaySize(6) @Required
    BigDecimal pHum

    @OnChange(BtuLbBagazoAction.class) @DisplaySize(6) @Required
    BigDecimal pCrisol
    
    @OnChange(BtuLbBagazoAction.class) @DisplaySize(6) @Required
    BigDecimal pCriCen
    
    @OnChange(BtuLbBagazoAction.class) @DisplaySize(6) @Required
    BigDecimal pMtra

    @ReadOnly @DisplaySize(6)
    BigDecimal porcHumedad

    @Digits(integer=3, fraction=3) @ReadOnly @DisplaySize(6) 
    BigDecimal porcCenBs

    @ReadOnly @DisplaySize(6) 
    BigDecimal porcCenBh

    @ReadOnly @DisplaySize(6) 
    BigDecimal pcBtuLb
}
