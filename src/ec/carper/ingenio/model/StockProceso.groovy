package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="diaTrabajo.descripcion, descripcion")
@View(members="""
    diaTrabajo, descripcion;
    detalle { detalle1 }
""")
class StockProceso extends Formulario {

    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    @OneToMany (mappedBy="stockProceso", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    @ListProperties("""
        orden,material.descripcion,temp,volumen1,volumen2,peso,porcBrix,eq,tonBrix,porcSac,tonSac,pureza,densidad,factor
    """)
    Collection<StockProcesoDetalle1> detalle1

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(StockProceso stockProceso){
        try{
            def lista = getManager().createQuery("FROM StockProcesoPDetalle WHERE stockProcesoP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new StockProcesoDetalle1(stockProceso: stockProceso, orden: it.orden, material: it.material)
                getManager().persist(d)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

}
