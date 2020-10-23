package ec.carper.ingenio.model 

import ec.carper.ingenio.actions.ColorMatAction
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
@View(members="""
    diaTrabajo;
    titAnaMatProCol{
        promedios(color/turbiedad) [#avgColor1,avgTurb1,avgColor2,avgTurb2;avgColor3,avgTurb3,avgColor4,avgTurb4;avgColor5,avgTurb5,avgColor6,avgTurb6]
        bri1,absFiltrada1,absSinFiltrar1,celda1,cedilla1,rho1,color1,turb1;
        bri2,absFiltrada2,absSinFiltrar2,celda2,cedilla2,rho2,color2,turb2;
        bri3,absFiltrada3,absSinFiltrar3,celda3,cedilla3,rho3,color3,turb3;
        bri4,absFiltrada4,absSinFiltrar4,celda4,cedilla4,rho4,color4,turb4;
        bri5,absFiltrada5,absSinFiltrar5,celda5,cedilla5,rho5,color5,turb5;
        bri6,absFiltrada6,absSinFiltrar6,celda6,cedilla6,rho6,color6,turb6;
        bri7,absFiltrada7,absSinFiltrar7,celda7,cedilla7,rho7,color7,turb7;
        bri8,absFiltrada8,absSinFiltrar8,celda8,cedilla8,rho8,color8,turb8
    }
""")
class ColorMat extends Formulario {

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor1
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb1

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor2
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb2

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor3
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb3

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor4
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb4

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor5
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb5

    @ReadOnly @DisplaySize(6)
    BigDecimal avgColor6
    @ReadOnly @DisplaySize(6)
    BigDecimal avgTurb6

    // Promedios
    @Depends("color1")
    BigDecimal getAvgColor1(){ return color1 }
    @Depends("turb1")
    BigDecimal getAvgTurb1(){ return turb1 }

    @Depends("color2")
    BigDecimal getAvgColor2(){ return color2 }
    @Depends("turb2")
    BigDecimal getAvgTurb2(){ return turb2 }

    @Depends("color7")
    BigDecimal getAvgColor5(){ return color7 }
    @Depends("turb7")
    BigDecimal getAvgTurb5(){ return turb7 }

    @Depends("color8")
    BigDecimal getAvgColor6(){ return color8 }
    @Depends("turb8")
    BigDecimal getAvgTurb6(){ return turb8 }

    // Campos
    @Depends("color3,color5")
    BigDecimal getAvgColor3(){ 
        def lista = []
        if (color3) lista << color3
        if (color5) lista << color5
        return Calculo.instance.getPromedio(lista, 2)
    }
    @Depends("turb3,turb5")
    BigDecimal getAvgTurb3(){ 
        def lista = []
        if (turb3) lista << turb3
        if (turb5) lista << turb5
        return Calculo.instance.getPromedio(lista, 2)
    }

    @Depends("color4,color6")
    BigDecimal getAvgColor4(){ 
        def lista = []
        if (color4) lista << color4
        if (color6) lista << color6
        return Calculo.instance.getPromedio(lista, 2)
    }
    @Depends("turb4,turb6")
    BigDecimal getAvgTurb4(){ 
        def lista = []
        if (turb4) lista << turb4
        if (turb6) lista << turb6
        return Calculo.instance.getPromedio(lista, 2)
    }

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri1
    
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal absFiltrada1
    
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal absSinFiltrar1
    
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal celda1
    
    @ReadOnly
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal cedilla1
    
    @ReadOnly
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal rho1
    
    @ReadOnly
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal color1
    
    @ReadOnly
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    @LabelFormat(LabelFormatType.SMALL)
    BigDecimal turb1

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri2                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada2                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar2                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda2                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla2                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho2                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color2                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb2

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri3                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada3                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar3                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda3                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla3                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho3                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color3                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb3

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri4                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada4                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar4                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda4                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla4                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho4                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color4                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb4

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri5                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada5                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar5                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda5                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla5                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho5                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color5                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb5

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri6                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada6                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar6                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda6                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla6                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho6                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color6                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb6

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri7                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada7                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar7                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda7                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla7                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho7                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color7                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb7

    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal bri8                               
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absFiltrada8                       
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=3) @DisplaySize(3)
    BigDecimal absSinFiltrar8                     
    @OnChange(ColorMatAction.class)
    @Digits(integer=2, fraction=2) @DisplaySize(2)
    BigDecimal celda8                             
    @ReadOnly                                     
    @Digits(integer=2, fraction=6) @DisplaySize(8)
    BigDecimal cedilla8                           
    @ReadOnly                                     
    @Digits(integer=4, fraction=6) @DisplaySize(8)
    BigDecimal rho8                               
    @ReadOnly                                     
    @Digits(integer=8, fraction=2) @DisplaySize(6)
    BigDecimal color8                             
    @ReadOnly                                     
    @Digits(integer=6, fraction=2) @DisplaySize(6)
    BigDecimal turb8
    
    void actualizar() throws ValidationException{
        try{
            this.avgColor1 = avgColor1
            this.avgColor2 = avgColor2
            this.avgColor3 = avgColor3
            this.avgColor4 = avgColor4
            this.avgColor5 = avgColor5
            this.avgColor6 = avgColor6
            this.avgTurb1  = avgTurb1
            this.avgTurb2  = avgTurb2
            this.avgTurb3  = avgTurb3
            this.avgTurb4  = avgTurb4
            this.avgTurb5  = avgTurb5
            this.avgTurb6  = avgTurb6

            XPersistence.getManager().persist(this)
        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
}
