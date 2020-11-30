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

    def getReporteDetalle1(){
        def lista = []
        try{
            def dt    = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(dt.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(dt.turnoTrabajo.horaHasta, diaTrabajo.id)

            while(hora < horaF ) {
                boolean agregarFila = false
                def horaS = Util.instance.getHoraS(hora)

                def cmr = new CanaMolidaReporteDetalle1(horaS: horaS, hora: hora)

                def d = SqlUtil.instance.getDetallePorHora(diaTrabajo.id, "trashCana", "TrashCanaDetalle1", horaS)
                if (d){
                    agregarFila      = true
                    cmr.modulo       = d.modulo.descripcion
                    cmr.turno        = d.turno.descripcion
                    cmr.calTrashCana = d.calTrashCana
                    cmr.calPorcTrash = d.calPorcTrash
                }
                
                d = SqlUtil.instance.getDetallePorHora(diaTrabajo.id, "trashCana", "TrashCanaDetalle2", horaS)
                if (d){
                    agregarFila       = true
                    cmr.calPorcAzuRed = d.calPorcAzuRed
                }

                d = SqlUtil.instance.getDetallePorHora(diaTrabajo.id, "cana", "CanaDetalle1", horaS)
                if (d){
                    agregarFila      = true
                    cmr.polExtracto  = d.polExtracto
                    cmr.porcHumedad  = d.porcHumedad
                    cmr.brix         = d.brix
                    cmr.porcFibra    = d.porcFibra
                    cmr.porcSacarosa = d.porcSacarosa
                    cmr.pureza       = d.pureza
                    cmr.pH           = d.pH
                }
                
                d = SqlUtil.instance.getDetallePorHora(diaTrabajo.id, "canaMolida", "CanaMolidaDetalle", horaS)
                if (d){
                    agregarFila    = true
                    cmr.ticket1    = d.ticket1
                    cmr.guia1      = d.guia1
                    cmr.horaBanda1 = d.horaBanda1
                    cmr.ticket2    = d.ticket2
                    cmr.guia2      = d.guia2
                    cmr.horaBanda2 = d.horaBanda2
                    cmr.ticket3    = d.ticket3
                    cmr.guia3      = d.guia3
                    cmr.horaBanda3 = d.horaBanda3
                }
                
                if (agregarFila) lista << cmr

                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
                
            // Agregar la última fila como promedios
            def cmr = new CanaMolidaReporteDetalle1()

            def o = SqlUtil.instance.getRegistros(diaTrabajo.id, "TrashCana", "diaTrabajo.id")[0]
            if (o){
                cmr.calTrashCana  = o.avgTrashCana
                cmr.calPorcTrash  = o.avgPorcTrash
                cmr.calPorcAzuRed = o.avgPorcAzuRed
            }

            o = SqlUtil.instance.getRegistros(diaTrabajo.id, "Cana", "diaTrabajo.id")[0]
            if (o){
                cmr.polExtracto  = o.polExtracto
                cmr.porcHumedad  = o.porcHumedad
                cmr.brix         = o.brix
                cmr.porcFibra    = o.porcFibra
                cmr.porcSacarosa = o.porcSacarosa
                cmr.pureza       = o.pureza
                cmr.pH           = o.pH
            }

            lista << cmr

        }catch(Exception ex){
            throw new SystemException("detalles_no_cargados", ex)
        }
        return lista
    }
    
    def getReporteDetalle2(){
        def lista = []
        try{
            def det = SqlUtil.instance.getRegistros(diaTrabajo.id, "CanaDetalle2", "cana.diaTrabajo.id")
            det.each{
                def cmr = new CanaMolidaReporteDetalle2(horaSD: it.horaSD, horaSH: it.horaSH, brix: it.brix, porcSacarosa: it.porcSacarosa, pureza: it.pureza)
                lista << cmr
            }
        }catch(Exception ex){
            throw new SystemException("detalles_no_cargados", ex)
        }
        return lista
    }
}
