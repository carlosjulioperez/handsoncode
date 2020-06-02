package ec.carper.ingenio.model 

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
    (color / turbiedad) [#color1,turb1;color2,turb2;color3,turb3;color4,turb4;color5,turb5;color6,turb6]
    detalle
""")
class ColorMat extends DiaTrabajoEditable {

    @ReadOnly
    BigDecimal color1
    @ReadOnly
    BigDecimal turb1

    @ReadOnly
    BigDecimal color2
    @ReadOnly
    BigDecimal turb2

    @ReadOnly
    BigDecimal color3
    @ReadOnly
    BigDecimal turb3

    @ReadOnly
    BigDecimal color4
    @ReadOnly
    BigDecimal turb4

    @ReadOnly
    BigDecimal color5
    @ReadOnly
    BigDecimal turb5

    @ReadOnly
    BigDecimal color6
    @ReadOnly
    BigDecimal turb6

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle>detalle

    void save() throws ValidationException{
        try{

            // Calcular promedios: Color y Turbiedad
            ColorMatDetalle d = (ColorMatDetalle) getManager()
                .createQuery("FROM ColorMatDetalle WHERE colorMat.id = :id ")
                .setParameter("id", this.id)
                .getSingleResult()

            if (d) {
                this.color1 = d.color1
                this.turb1  = d.turb1
                this.color2 = d.color2
                this.turb2  = d.turb2

                def lista = []
                if (d.color3) lista << d.color3
                if (d.color5) lista << d.color5
                this.color3 = Calculo.instance.getPromedio(lista, 2)

                lista = []
                if (d.turb3) lista << d.turb3
                if (d.turb5) lista << d.turb5
                this.turb3  = Calculo.instance.getPromedio(lista, 2)
                
                lista = []
                if (d.color4) lista << d.color4
                if (d.color6) lista << d.color6
                this.color4 = Calculo.instance.getPromedio(lista, 2)

                lista = []
                if (d.turb4) lista << d.turb4
                if (d.turb6) lista << d.turb6
                this.turb4  = Calculo.instance.getPromedio(lista, 2)

                this.color5 = d.color7
                this.turb5  = d.turb7
                this.color6 = d.color8
                this.turb6  = d.turb8

                // println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
                // println this.dump()

                XPersistence.getManager().persist(this)
            }

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
