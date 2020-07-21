package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Entity
//@View(members="""# pMtra; pCrisol; pCriCen""")
class Cto24HDetalle5 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @OnChange(Cto24HDetalle5Action.class) @DisplaySize(6)
    BigDecimal pMtra
    
    @OnChange(Cto24HDetalle5Action.class) @DisplaySize(6)
    BigDecimal pCrisol
    
    @OnChange(Cto24HDetalle5Action.class) @DisplaySize(6)
    BigDecimal pCriCen
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porcCenizas

}
