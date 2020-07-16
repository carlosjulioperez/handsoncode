package ec.carper.ingenio.model

import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Embeddable
class Cto24HDetalle4{

    int id

    @Column(length=20)
    String descripcion
    
    @DisplaySize(6)
    BigDecimal cana
    
    @DisplaySize(6)
    BigDecimal j1Extracto
    
    @DisplaySize(6)
    BigDecimal jDiluido
    
    @DisplaySize(6)
    BigDecimal jClaro
    
    @DisplaySize(6)
    BigDecimal jFiltrado
    
    @DisplaySize(6)
    BigDecimal mClara
    
    @DisplaySize(6)
    BigDecimal mielA
    
    @DisplaySize(6)
    BigDecimal mielB
    
    @DisplaySize(6)
    BigDecimal mielF

}
