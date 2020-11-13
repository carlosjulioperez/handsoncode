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
@Tab(properties="""diaTrabajo.descripcion,promCantCana,promNetaCana,promTrashCana,promPorcTrash,promPorcAzuRed""")
@View(members="""
    diaTrabajo, moduloTmp, turnoTmp, variedadTmp;
    titAnaTraCan { detalle1 }
    titAnaAzuRed { detalle2 }
""")
class TrashCana extends Formulario {
    
    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Modulo moduloTmp

    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Turno turnoTmp
    
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Variedad variedadTmp
    //**************************************************

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,modulo.descripcion,turno.descripcion,variedad.descripcion,
        cantidadCana[trashCana.promCantCana],netaCana[trashCana.promNetaCana],calTrashCana[trashCana.promTrashCana], calPorcTrash[trashCana.promPorcTrash]
    """)
    Collection<TrashCanaDetalle1>detalle1
    
    BigDecimal getPromCantCana(){
        return super.getPromedio(detalle1, "cantidadCana", 2)
    }

    BigDecimal getPromNetaCana(){
        return super.getPromedio(detalle1, "netaCana", 2)
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromTrashCana(){
        return super.getPromedio(detalle1, "calTrashCana", 3)
    }

    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcTrash(){
        return super.getPromedio(detalle1, "calPorcTrash", 3)
    }
    
    BigDecimal avgCantCana

    BigDecimal avgNetaCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgTrashCana

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcTrash

    @OneToMany (mappedBy="trashCana", cascade=CascadeType.ALL) @XOrderBy("hora") 
    @ListProperties("""hora,mlReductores,calTab7SusRed,calPorcAzuRed[trashCana.promPorcAzuRed]""")
    Collection<TrashCanaDetalle2>detalle2

    @Digits(integer=4, fraction=3)
    BigDecimal avgPorcAzuRed

    @Digits(integer=4, fraction=3)
    BigDecimal getPromPorcAzuRed(){
        return super.getPromedio(detalle2, "calPorcAzuRed", 3)
    }

    void crearTrash() throws ValidationException{
        try{
            Trash trash = new Trash()
            trash.id = null

            trash.diaTrabajo   = this.diaTrabajo
            trash.avgCantCana  = this.avgCantCana
            trash.avgNetaCana  = this.avgNetaCana
            trash.avgTrashCana = this.avgTrashCana
            trash.avgPorcTrash = this.avgPorcTrash

            XPersistence.getManager().persist(trash)
            copiarDetallesHaciaTrash(trash)
                
        }catch(Exception ex){
            throw new SystemException("formulario_trash_no_creado", ex)
        }
    }
    
    // Este método puede servir para grabar propiedades calculadas en otro campo
    void copiarDetallesHaciaTrash(Trash trash){
        try{
            detalle1.each{
                TrashDetalle o = new TrashDetalle()
                
                o.id           = null
                o.trash        = trash
                o.horaS        = it.horaS
                o.hora         = it.hora
                o.modulo       = it.modulo
                o.turno        = it.turno
                o.variedad     = it.variedad
                o.cantidadCana = it.cantidadCana
                o.netaCana     = it.netaCana
                o.calTrashCana = it.calTrashCana
                o.calPorcTrash = it.calPorcTrash

                XPersistence.getManager().persist(o)
            }
        }catch(Exception ex){
            throw new SystemException("detalles_formulario_trash_no_copiados", ex)
        }
    }
    
    void actualizar() throws ValidationException{
        try{

            this.avgCantCana   = promCantCana
            this.avgNetaCana   = promNetaCana
            this.avgTrashCana  = promTrashCana
            this.avgPorcTrash  = promPorcTrash
            this.avgPorcAzuRed = promPorcAzuRed

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

    void crearItems(TrashCana trashCana) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def horaI = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)

            // Detalle 1, incremento de 1 hora 
            def hora = horaI
            while(hora < horaF ) {
                def det = new TrashCanaDetalle1(trashCana: trashCana, horaS: Util.instance.getHoraS(hora), hora: hora, modulo: moduloTmp, turno: turnoTmp, variedad: variedadTmp)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }

            // Detalle 2, incremento de 2 horas
            hora = horaI
            while(hora < horaF ) {
                def det = new TrashCanaDetalle2(trashCana: trashCana, horaS: Util.instance.getHoraS(hora), hora: hora)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora, 2) // Incremento de dos horas
            }
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }


}
