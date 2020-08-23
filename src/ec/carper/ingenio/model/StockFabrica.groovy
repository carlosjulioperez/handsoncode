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
    titTqJCla { detalle2 }
    titTqJEnc { detalle3 }
    titTqJFil { detalle4 }
    titClaJug { detalle5 }
    titTorSul {
        fldTonSacTorSul;
        titTorSulJug{ detalle6 }
        titTorSulMel{ detalle7 }
    }

""")
class StockFabrica extends Formulario {
    
    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle1> detalle1

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle2> detalle2

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle3> detalle3

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle4> detalle4

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle5> detalle5

    @ReadOnly @DisplaySize(6)
    BigDecimal fldTonSacTorSul

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle6> detalle6

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle7> detalle7

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
            (1..7).each{
                cargarDetalle(stockFabrica, "StockFabricaDetalle${it}", "StockFabricaPDetalle${it}")
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    private void cargarDetalle(StockFabrica stockFabrica, String modulo, String moduloP){
        def lista = getManager().createQuery("FROM ${moduloP} WHERE stockFabricaP.id = 1 ORDER BY orden").getResultList()
        //println ">>> ${lista}"
        lista.each{
            def instance = new groovy.lang.GroovyClassLoader().loadClass( 
                "ec.carper.ingenio.model.${modulo}", true, false )?.newInstance()
            
            instance.stockFabrica = stockFabrica
            instance.orden        = it.orden
            instance.indicador    = it.indicador
            instance.unidad       = it.unidad
            instance.valor        = it.valor
            instance.modificable  = it.modificable

            getManager().persist(instance)
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
    
    void actualizar() throws ValidationException{
        try{
            this.fldTonSacTorSul = tonSacTorSul
            getManager().persist(this)
        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

    BigDecimal getTonSacTorSul(){
        def padreId = this.id
        def campoFk = "stockFabrica.id"
        if (padreId){

            def d1 = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle6", campoFk, "TonSacJSulf")
            def d2 = SqlUtil.instance.getDetallePorIndicador(padreId, "StockFabricaDetalle7", campoFk, "TonSacMel")
            return d1.valor + d2.valor
        }
    }
}
