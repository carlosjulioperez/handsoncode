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
    detalle { detalle }
""")
class BlcCenicana extends Formulario {
    
    @OnChange(BlcCenicanaShowHideCargarItemsAction.class)
    boolean itemsCargados
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcCenicana", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcCenicanaDetalle> detalle

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(BlcCenicana blcCenicana){
        try{
            def lista = getManager().createQuery("FROM BlcCenicanaPDetalle WHERE blcCenicanaP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcCenicanaDetalle(blcCenicana: blcCenicana, orden: it.orden, peso: it.peso, porcC: it.porcC, descripcion: it.descripcion)
                getManager().persist(d)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }
    
    void consultarDatos() throws ValidationException{
        try{ 
            // consultar()
        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultar(){
        detalle.each{
            switch (it.orden){
                case 2:
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "polCachaza")
                    break
            }
            getManager().persist(it)
        }
    }
}

