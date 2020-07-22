package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Embeddable
class Cto24HDetalle8{

    @Column(length=1) @ReadOnly
    int filaNo
    
    @Column(length=20) @ReadOnly
    String descripcion

    @OnChange(Cto24HDetalle8Action.class) @DisplaySize(6)
    BigDecimal brixRef
    
    @DisplaySize(6) @ReadOnly
    BigDecimal brixEle
    
    @DisplaySize(6) @ReadOnly
    BigDecimal porc

}
