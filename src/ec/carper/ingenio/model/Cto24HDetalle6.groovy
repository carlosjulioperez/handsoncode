package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Entity
class Cto24HDetalle6 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @OnChange(Cto24HDetalle6Action.class) @DisplaySize(6)
    BigDecimal bxOc
    
    @OnChange(Cto24HDetalle6Action.class) @DisplaySize(6)
    BigDecimal bxDig
    
    @ReadOnly @DisplaySize(6)
    BigDecimal porc

}
