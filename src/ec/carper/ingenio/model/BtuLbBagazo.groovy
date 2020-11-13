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
@Tab(properties="""diaTrabajo.fecha""")
@View(members="""#
    diaTrabajo;
    titMatPriQueCan {#pHum,pCrisol;pCriCen,pMtra;porcHumedad,porcCenBs;porcCenBh,pcBtuLb}
""")
class BtuLbBagazo extends Formulario {

    @Digits(integer=10, fraction=4) @DisplaySize(6)
    BigDecimal pHum

    @OnChange(BtuLbBagazoAction.class)
    @Digits(integer=10, fraction=4) @DisplaySize(6)
    BigDecimal pCrisol
    
    @OnChange(BtuLbBagazoAction.class)
    @Digits(integer=10, fraction=4) @DisplaySize(6)
    BigDecimal pCriCen
    
    @OnChange(BtuLbBagazoAction.class)
    @Digits(integer=10, fraction=4) @DisplaySize(6)
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
