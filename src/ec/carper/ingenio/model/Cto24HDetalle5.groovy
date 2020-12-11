package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*
    
@Entity
//@View(members="""# pMtra; pCrisol; pCriCen""")
class Cto24HDetalle5 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @OnChange(Cto24HDetalle5Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal pMtra
    
    @OnChange(Cto24HDetalle5Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal pCrisol
    
    @OnChange(Cto24HDetalle5Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal pCriCen
    
    @DisplaySize(6) @ReadOnly
    BigDecimal porcCenizas

}
