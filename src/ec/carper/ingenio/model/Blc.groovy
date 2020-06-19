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
@Tab(properties="""
    diaTrabajo.descripcion
""")
@View(members="""
    diaTrabajo;
    datos {
        datosDia { detalle1 }
    }
""")
class Blc extends DiaTrabajoEditable {
    
    boolean itemsCargados

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @EditOnly
    Collection<BlcDetalle1>detalle1

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(Blc blc){
        try{
            def lista = getManager().createQuery("FROM BlcPDetalle1 WHERE blcP.id = 1 ORDER BY orden").getResultList()

            lista.each{
                def d1      = new BlcDetalle1()
                d1.id       = null
                d1.blc      = blc
                d1.material = it.material
                d1.unidad   = it.unidad
                d1.unidad2  = it.unidad2
                getManager().persist(d1)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

}
