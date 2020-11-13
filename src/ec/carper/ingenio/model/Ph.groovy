package ec.carper.ingenio.model

import ec.carper.ingenio.util.*

import java.text.SimpleDateFormat
import java.time.format.*
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

import java.time.LocalDate

@Entity
@Tab(properties="""
    diaTrabajo.fecha,
    j1Extracto,
    jDiluido,
    jEncalado,
    jClaro,
    jFiltrado,
    mCruda,
    mClarificada,
    tJClaro
""")
@View(members="""
    diaTrabajo;
    titAnaMatProPh {detalle}
""")
class Ph extends Formulario {
    
    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados
    //**************************************************

    BigDecimal j1Extracto
    BigDecimal jDiluido
    BigDecimal jEncalado
    BigDecimal jClaro
    BigDecimal jFiltrado
    BigDecimal mCruda
    BigDecimal mClarificada
    BigDecimal tJClaro

    @OneToMany (mappedBy="ph", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        j1Extracto   [ph.promJ1Extracto],
        jDiluido     [ph.promJDiluido],
        jEncalado    [ph.promJEncalado],
        jClaro       [ph.promJClaro],
        jFiltrado    [ph.promJFiltrado],
        mCruda       [ph.promMCruda],
        mClarificada [ph.promMClarificada],
        tJClaro      [ph.promTJClaro]
    """)
    Collection<PhDetalle>detalle

    BigDecimal getPromJ1Extracto(){
        return super.getPromedio(detalle, "j1Extracto", 2)
    }
    BigDecimal getPromJDiluido(){
        return super.getPromedio(detalle, "jDiluido", 2)
    }
    BigDecimal getPromJEncalado(){
        return super.getPromedio(detalle, "jEncalado", 2)
    }
    BigDecimal getPromJClaro(){
        return super.getPromedio(detalle, "jClaro", 2)
    }
    BigDecimal getPromJFiltrado(){
        return super.getPromedio(detalle, "jFiltrado", 2)
    }
    BigDecimal getPromMCruda(){
        return super.getPromedio(detalle, "mCruda", 2)
    }
    BigDecimal getPromMClarificada(){
        return super.getPromedio(detalle, "mClarificada", 2)
    }
    BigDecimal getPromTJClaro(){
        return super.getPromedio(detalle, "tJClaro", 2)
    }
    
    void actualizar() throws ValidationException{
        try{

            this.j1Extracto   = promJ1Extracto   
            this.jDiluido     = promJDiluido
            this.jEncalado    = promJEncalado
            this.jClaro       = promJClaro
            this.jFiltrado    = promJFiltrado
            this.mCruda       = promMCruda
            this.mClarificada = promMClarificada
            this.tJClaro      = promTJClaro

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    void crearItemsPorHora() throws ValidationException{
        try{
            this.itemsPorHoraCreados = true
            getManager().persist(this)
            crearItems(this)
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

    void crearItems(Ph ph) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)

            while(hora < horaF ) {
                def det = new PhDetalle(ph: ph, horaS: Util.instance.getHoraS(hora), hora: hora)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

}
