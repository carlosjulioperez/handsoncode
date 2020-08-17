package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.*

import javax.persistence.*
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
    titTqJDil { detalle1 }
""")
class StockFabrica extends Formulario {
    
    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle1> detalle1

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(StockFabrica stockFabrica){
        try{
            def lista = getManager().createQuery("FROM StockFabricaPDetalle1 WHERE stockFabricaP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new StockFabricaDetalle1(stockFabrica: stockFabrica, orden: it.orden, indicador: it.indicador, unidad: it.unidad, valor: it.valor, modificable: it.modificable)
                getManager().persist(d)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void consultarDatos() throws ValidationException{
        try{ 
            consultarTqJDil()
        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultarTqJDil(){
        detalle1.each{
            def campo = it.indicador.campo ?: ""
            switch (campo){
                case "":
                    //it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "turJClaro")
                    it.valor = 0
                    break
            }
            getManager().persist(it)
        }
    }
    
    // void actualizar() throws ValidationException{
    //     try{
    //
    //         // this.fldTiempoPerdidoTotal = tiempoPerdidoTotal
    //         getManager().persist(this)
    //
    //     }catch(Exception ex){
    //         throw new SystemException("registro_no_actualizado", ex)
    //     }
    // }
}
