package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""diaTrabajo.descripcion""")
@View(members=  """diaTrabajo;detalle1;detalle2;detalle3;detalle4;detalle5;detalle6;detalle7;detalle8""")
class ColorMat extends DiaTrabajoEditable {

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle1>detalle1

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle2>detalle2

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle3>detalle3

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle4>detalle4

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle5>detalle5

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle6>detalle6

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle7>detalle7

    @OneToMany (mappedBy="colorMat", cascade=CascadeType.ALL)
    Collection<ColorMatDetalle8>detalle8

    void save() throws ValidationException{
        try{

            //TODO: Calcular promedios
            // Color y Turbiedad
  
            //XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

}
