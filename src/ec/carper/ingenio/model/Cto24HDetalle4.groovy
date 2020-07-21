package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Embeddable
class Cto24HDetalle4{

    @Column(length=20) @ReadOnly
    String descripcion
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal j1Extracto
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal jDiluido
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal jClaro
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal jFiltrado
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal mClara
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal mielA
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal mielB
    
    @OnChange(Cto24HDetalle4Action.class) @DisplaySize(6)
    BigDecimal mielF

}
