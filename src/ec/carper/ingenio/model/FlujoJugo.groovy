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
    diaTrabajo.fecha, descripcion,
    tot,
    brixJDil,
    tonJugo 
""")
@View(members="""
    diaTrabajo, descripcion;
    titFluJugMol { detalle }
""")
class FlujoJugo extends Formulario {
    
    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados
    //**************************************************
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    Integer tot
    BigDecimal brixJDil
    
    @Digits(integer=12, fraction=6)
    BigDecimal tonJugo

    @OneToMany (mappedBy="flujoJugo", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora, ini, fin,
        tot      [flujoJugo.sumTot],
        brixJDil [flujoJugo.promBrixJDil],
        p,
        tonJugo  [flujoJugo.sumTonJugo]
    """)
    Collection<FlujoJugoDetalle>detalle

    BigDecimal getPromBrixJDil(){
        return super.getPromedio(detalle, "brixJDil", 2)
    }
    
    BigDecimal getSumTot(){
        return super.getSuma(detalle, "tot")
    }
    
    BigDecimal getSumTonJugo(){
        return super.getSuma(detalle, "tonJugo")
    }

    void actualizar() throws ValidationException{
        try{

            this.tot      = sumTot   
            this.brixJDil = promBrixJDil
            this.tonJugo  = sumTonJugo

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

    void crearItems(FlujoJugo flujoJugo) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)
            def i     = 0

            while(hora < horaF ) {
                def det = new FlujoJugoDetalle(flujoJugo: flujoJugo, horaS: Util.instance.getHoraS(hora), hora: hora)
                if (i==0) {
                    det.ini = 0
                    i++
                }
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }
}
