package ec.carper.ingenio.model

import javax.persistence.*
import javax.validation.constraints.Digits
import org.apache.commons.beanutils.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*

import ec.carper.ingenio.util.Util
import java.time.LocalDate

@Entity
@Tab(properties="""diaTrabajo.descripcion,promCantCana,promNetaCana,promTrashCana,promPorcTrash,promPorcAzuRed""")
@View(members=  """diaTrabajo;detalle1;detalle2""")
class TrashCana extends DiaTrabajoEditable {
    
    @Version
    private Integer version;
        
    @ElementCollection
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

    @ElementCollection
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
            BeanUtils.copyProperties(trash, this)
            trash.id = null
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
                o.hora         = it.hora
                o.modulo       = it.modulo
                o.turno        = it.turno
                o.variedad     = it.variedad
                o.cantidadCana = it.cantidadCana
                o.netaCana     = it.netaCana
                o.valTrashCana = it.valTrashCana
                o.valPorcTrash = it.valPorcTrash

                XPersistence.getManager().persist(o)
            }
        }catch(Exception ex){
            throw new SystemException("detalles_formulario_trash_no_copiados", ex)
        }
    }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcular()
    }

    @PreUpdate
    void recalcular() {
        avgCantCana   = promCantCana
        avgNetaCana   = promNetaCana
        avgTrashCana  = promTrashCana
        avgPorcTrash  = promPorcTrash
        avgPorcAzuRed = promPorcAzuRed
    }
}