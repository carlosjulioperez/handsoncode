package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""
    datos[tipo, aberturaMedia, coeficienteVariacion];
    #tamizAbertura1, tamiz1, pesoTamiz1, pesoTamizMuestra1, porcRetenido1, porcAcumulado1;
    tamizAbertura2, tamiz2, pesoTamiz2, pesoTamizMuestra2, porcRetenido2, porcAcumulado2;
    tamizAbertura3, tamiz3, pesoTamiz3, pesoTamizMuestra3, porcRetenido3, porcAcumulado3;
    tamizAbertura4, tamiz4, pesoTamiz4, pesoTamizMuestra4, porcRetenido4, porcAcumulado4;
    tamizAbertura5, tamiz5, pesoTamiz5, pesoTamizMuestra5, porcRetenido5, porcAcumulado5
""")
class TamanoGranoDetalle extends Identifiable {

    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    TamanoGrano tamanoGrano

    @Required
    Tipo tipo 
    enum Tipo { GRASSHOPER, FAMILIAR }
    
    @ReadOnly @DisplaySize(6)
    BigDecimal aberturaMedia

    @ReadOnly @DisplaySize(6)
    BigDecimal coeficienteVariacion

    //*****************************************
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    BigDecimal tamizAbertura1
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    String tamiz1

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamiz1

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamizMuestra1
    
    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcRetenido1

    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcAcumulado1

    //*****************************************

    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    BigDecimal tamizAbertura2
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    String tamiz2

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamiz2

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamizMuestra2
    
    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcRetenido2

    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcAcumulado2

    //*****************************************

    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    BigDecimal tamizAbertura3
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    String tamiz3

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamiz3

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamizMuestra3
    
    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcRetenido3

    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcAcumulado3

    //*****************************************

    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    BigDecimal tamizAbertura4
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @ReadOnly
    String tamiz4

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamiz4

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamizMuestra4
    
    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcRetenido4

    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcAcumulado4

    //*****************************************

    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @Transient @ReadOnly
    BigDecimal tamizAbertura5
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    @Transient @ReadOnly
    String tamiz5

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamiz5

    @OnChange(TamanoGranoDetalleAction.class) 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal pesoTamizMuestra5
    
    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcRetenido5

    @ReadOnly 
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(6)
    BigDecimal porcAcumulado5

}
