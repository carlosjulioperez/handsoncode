package ec.carper.ingenio.model 

import ec.carper.ingenio.actions.Cto24HAction
import ec.carper.ingenio.util.Calculo

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""diaTrabajo.descripcion""")
@View(members="""#
    diaTrabajo, fFelining;
    atr {
        atM1,atC1,atJ11,atJd1,atJc1,atJf1,atMc1,atMa1,atMb1,atMf1;
        atM2,atC2,atJ12,atJd2,atJc2,atJf2,atMc2,atMa2,atMb2,atMf2
    }
    psi {
        siM1,siM2,siM3,siPorc;
        siC1;siC2;siC3;siC4;siC5;siC6;siC7;siC8
    }
    ar  {
        arM1,arC1,arJ11,arJd1,arJc1,arJf1,arMc1,arMa1,arMb1,arMf1;
        arM2,arC2,arJ12,arJd2,arJc2,arJf2,arMc2,arMa2,arMb2,arMf2;
        arM3,arC3,arJ13,arJd3,arJc3,arJf3,arMc3,arMa3,arMb3,arMf3
    }
    cc  {
        ccM1,ccJ11,ccJd1,ccJc1,ccJf1,ccMc1,ccMa1,ccMb1,ccMf1;
        ccM2,ccJ12,ccJd2,ccJc2,ccJf2,ccMc2,ccMa2,ccMb2,ccMf2;
        ccM3,ccJ13,ccJd3,ccJc3,ccJf3,ccMc3,ccMa3,ccMb3,ccMf3;
        ccM4,ccJ14,ccJd4,ccJc4,ccJf4,ccMc4,ccMa4,ccMb4,ccMf4;
        ccM5,ccJ15,ccJd5,ccJc5,ccJf5,ccMc5,ccMa5,ccMb5,ccMf5;
        ccM6,ccJ16,ccJd6,ccJc6,ccJf6,ccMc6,ccMa6,ccMb6,ccMf6;
        ccM7,ccJ17,ccJd7,ccJc7,ccJf7,ccMc7,ccMa7,ccMb7,ccMf7;
        ccM8,ccJ18,ccJd8,ccJc8,ccJf8,ccMc8,ccMa8,ccMb8,ccMf8;
        ccM9,ccJ19,ccJd9,ccJc9,ccJf9,ccMc9,ccMa9,ccMb9,ccMf9;
        ccM0,ccJ10,ccJd0,ccJc0,ccJf0,ccMc0,ccMa0,ccMb0,ccMf0
    }
""")
class Cto24H extends DiaTrabajoEditable {

    @OnChange(Cto24HAction.class) @Required
    @Digits(integer=3, fraction=3) @DisplaySize(6) 
    BigDecimal fFelining

    // AZUCARES TOTALES REDUCTORES  A.T.R.
    @Transient @ReadOnly @DisplaySize(1)
    String atM1
    @Transient @ReadOnly @DisplaySize(1)
    String atM2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atC1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atC2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atJ11
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atJ12

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atJd1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atJd2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atJc1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atJc2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atJf1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atJf2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atMc1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atMc2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atMa1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atMa2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atMb1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atMb2

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal atMf1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal atMf2

    // % SOLIDOS INSOLUBLES
    
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    @LabelFormat(LabelFormatType.SMALL) @ReadOnly
    BigDecimal siM1

    @Digits(integer=4, fraction=4) @DisplaySize(6)
    @LabelFormat(LabelFormatType.SMALL) @ReadOnly
    BigDecimal siM2
 
    @LabelFormat(LabelFormatType.SMALL) @ReadOnly @DisplaySize(6)
    BigDecimal siM3 

    @LabelFormat(LabelFormatType.SMALL) @ReadOnly @DisplaySize(6)
    BigDecimal siPorc
    
    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC1

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC2

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC3

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC4

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC5

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC6

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC7

    @OnChange(Cto24HAction.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6) 
    BigDecimal siC8

    // AZUCARES REDUCTORES - Lane Eynon

    @Transient @ReadOnly @DisplaySize(1)
    String arM1
    @Transient @ReadOnly @DisplaySize(1)
    String arM2
    @Transient @ReadOnly @DisplaySize(1)
    String arM3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arC1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arC2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arC3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arJ11
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJ12
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJ13

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arJd1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJd2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJd3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arJc1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJc2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJc3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arJf1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJf2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arJf3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arMc1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMc2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMc3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arMa1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMa2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMa3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arMb1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMb2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMb3

    @OnChange(Cto24HAction.class)
    @LabelFormat(LabelFormatType.SMALL) @DisplaySize(4)
    BigDecimal arMf1
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMf2
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(4) @ReadOnly 
    BigDecimal arMf3

    // CENIZAS CONDUCTIMETRICAS EN MATERIALES DE PROCESO

    @Transient @ReadOnly @DisplaySize(1)
    String ccM1
    @Transient @ReadOnly @DisplaySize(1)
    String ccM2
    @Transient @ReadOnly @DisplaySize(1)
    String ccM3
    @Transient @ReadOnly @DisplaySize(1)
    String ccM4
    @Transient @ReadOnly @DisplaySize(1)
    String ccM5
    @Transient @ReadOnly @DisplaySize(1)
    String ccM6
    @Transient @ReadOnly @DisplaySize(1)
    String ccM7
    @Transient @ReadOnly @DisplaySize(1)
    String ccM8
    @Transient @ReadOnly @DisplaySize(1)
    String ccM9
    @Transient @ReadOnly @DisplaySize(1)
    String ccM0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccJ11
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJ12
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJ13
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJ14
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJ15
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJ16
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJ17
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJ18
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJ19
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJ10

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccJd1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJd2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJd3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJd4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJd5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJd6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJd7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJd8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJd9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJd0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccJc1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJc2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJc3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJc4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJc5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJc6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJc7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJc8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJc9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJc0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccJf1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJf2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJf3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJf4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccJf5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJf6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccJf7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJf8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccJf9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccJf0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccMc1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMc2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMc3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMc4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMc5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMc6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMc7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMc8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMc9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMc0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccMa1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMa2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMa3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMa4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMa5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMa6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMa7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMa8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMa9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMa0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccMb1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMb2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMb3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMb4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMb5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMb6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMb7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMb8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMb9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMb0

    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.SMALL) @DisplaySize(8)
    BigDecimal ccMf1
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMf2
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMf3
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMf4
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8)
    BigDecimal ccMf5
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMf6
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=8) @ReadOnly
    BigDecimal ccMf7
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMf8
    @OnChange(Cto24HAction.class) @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @Digits(integer=6,fraction=3) @ReadOnly
    BigDecimal ccMf9
    @LabelFormat(LabelFormatType.NO_LABEL) @DisplaySize(8) @ReadOnly
    BigDecimal ccMf0

}
