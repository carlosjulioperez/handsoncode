package ec.carper.ingenio.model

import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
    
@Entity
@View(members="""#
    cana       , pd311   , pd321;
    j1Extracto , pd312   , pd322;
    jDiluido   , pd313   , pd323;
    jClaro     , pd314   , pd324;
    jFiltrado  , pd315   , pd325;
    mClara     , pd316   , pd326;
    mielA      , pd317   , pd327;
    mielB      , pd318   , pd328;
    mielF      , pd319   , pd329;
""")
class Cto24HDetalle3 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
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

    @Depends("cana") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd311(){
        return cana ? Calculo.instance.redondear(cana/4, 2): 0
    }
    @Depends("pd311") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd321(){
        return pd311 ? new BrixDensidadTitSus().getSusRed(pd311): 0
    }
    
    @Depends("j1Extracto") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd312(){
        return j1Extracto ? Calculo.instance.redondear(j1Extracto/2, 2): 0
    }
    @Depends("pd312") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd322(){
        return pd312 ? new BrixDensidadTitSus().getSusRed(pd312): 0
    }

    @Depends("jDiluido") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd313(){
        return jDiluido ? Calculo.instance.redondear(jDiluido/2, 2): 0
    }
    @Depends("pd313") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd323(){
        return pd313 ? new BrixDensidadTitSus().getSusRed(pd313): 0
    }

    @Depends("jClaro") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd314(){
        return jClaro ? Calculo.instance.redondear(jClaro/2, 2): 0
    }
    @Depends("pd314") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd324(){
        return pd314 ? new BrixDensidadTitSus().getSusRed(pd314): 0
    }

    @Depends("jFiltrado") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd315(){
        return jFiltrado ? Calculo.instance.redondear(jFiltrado/2, 2): 0
    }
    @Depends("pd315") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd325(){
        return pd315 ? new BrixDensidadTitSus().getSusRed(pd315): 0
    }

    @Depends("mClara") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd316(){
        return mClara ? new BrixDensidadTitSus().getSusRed(mClara): 0
    }
    @Depends("pd316") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd326(){
        return pd316 ? Calculo.instance.redondear(pd316*4, 2): 0
    }

    @Depends("mielA") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd317(){
        return mielA ? new BrixDensidadTitSus().getSusRed(mielA): 0
    }
    @Depends("pd317") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd327(){
        return pd317 ? Calculo.instance.redondear(pd317*4, 2): 0
    }

    @Depends("mielB") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd318(){
        return mielB ? new BrixDensidadTitSus().getSusRed(mielB): 0
    }
    @Depends("pd318") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd328(){
        return pd318 ? Calculo.instance.redondear(pd318*20, 2): 0
    }

    @Depends("mielF") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd319(){
        return mielF ? new BrixDensidadTitSus().getSusRed(mielF): 0
    }
    @Depends("pd319") @DisplaySize(6) @LabelFormat(LabelFormatType.NO_LABEL)
    BigDecimal getPd329(){
        return pd319 ? Calculo.instance.redondear(pd319*25, 2): 0
    }

}
