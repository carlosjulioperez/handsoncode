package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Entity
@View(members="""#
    cana       , pd11;
    j1Extracto , pd12;
    jDiluido   , pd13;
    jClaro     , pd14;
    jFiltrado  , pd15;
    mClara     , pd16;
    mielA      , pd17;
    mielB      , pd18;
    mielF      , pd19
""")
class Cto24HDetalle1 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal cana  
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal j1Extracto  
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal jDiluido    
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal jClaro      
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal jFiltrado   
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal mClara
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal mielA
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal mielB
    
    @OnChange(Cto24HDetalle1Action.class) @DisplaySize(6)
    BigDecimal mielF
     
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd11
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd12
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd13
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd14
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd15
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd16
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd17
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd18
    
    @ReadOnly @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal pd19

}
