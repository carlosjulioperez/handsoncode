package ec.carper.ingenio.model

import ec.carper.ingenio.util.*

import java.text.SimpleDateFormat
import java.time.format.*
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
@Tab(properties="""
    diaTrabajo.fecha
""")
@View(members="""
    diaTrabajo;
    titDetalle { detalle }
""")
class CanaMolida extends Formulario {
    
    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados
    //**************************************************

    @OneToMany (mappedBy="canaMolida", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora, 
        ticket1, guia1, horaBanda1,
        ticket2, guia2, horaBanda2,
        ticket3, guia3, horaBanda3
    """)
    Collection<CanaMolidaDetalle>detalle

    void crearItemsPorHora() throws ValidationException{
        try{
            this.itemsPorHoraCreados = true
            getManager().persist(this)
            crearItems(this)
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

    void crearItems(CanaMolida canaMolida) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)

            while(hora < horaF ) {
                def det = new CanaMolidaDetalle(canaMolida: canaMolida, horaS: Util.instance.getHoraS(hora), hora: hora)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

}
