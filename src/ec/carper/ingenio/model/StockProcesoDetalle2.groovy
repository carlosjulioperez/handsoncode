package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""#
    orden,material;
    volumen1,volumen2,peso;
    porcBrix,eq,tonBrix;
    porcSac, tonSac;
    pureza,densidad,factor
""")
class StockProcesoDetalle2 extends Identifiable{
   
    @ManyToOne
    StockProceso stockProceso

    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Material material

    @OnChange(StockProcesoDetalle2Action.class)
    @Digits(integer=10, fraction=3) @DisplaySize(6)
    BigDecimal volumen1
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal volumen2
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal peso

    @DisplaySize(6) @ReadOnly
    BigDecimal porcBrix

    @OnChange(StockProcesoDetalle2Action.class) @Column(length=3)
    int eq

    @DisplaySize(6) @ReadOnly
    BigDecimal tonBrix

    @DisplaySize(6) @ReadOnly
    BigDecimal porcSac

    @DisplaySize(6) @ReadOnly
    BigDecimal tonSac

    @DisplaySize(6) @ReadOnly
    BigDecimal pureza

    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal densidad

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal factor
    
}
