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
        tonSacTorSul;
        titTorSulJug{ detalle6 }
        titTorSulMel{ detalle7 }
    }
    titCalJug{
        tonSacTraJug, tonSacCal;
        titCalJug1 { detalle8  }
        titCalJug2 { detalle9  }
        titCalJug3 { detalle10 }
        titCalJug4 { detalle11 }
        titCalJug5 { detalle12 }
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

    BigDecimal fldTonSacTorSul
    BigDecimal fldTonSacTraJug
    BigDecimal fldTonSacCal

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle6> detalle6

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle7> detalle7

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle8> detalle8

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle9> detalle9

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle10> detalle10

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle11> detalle11

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle12> detalle12

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
            (1..12).each{
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
    
    void actualizar() throws ValidationException{
        try{
            this.fldTonSacTorSul = tonSacTorSul
            this.fldTonSacTraJug = tonSacTraJug 
            this.fldTonSacCal    = tonSacCal
            getManager().persist(this)
        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }

    def getSumaValores(def desde, def hasta, def campos){
        def valor = 0
        def campoFk = "stockFabrica.id"
        if (this.id){
            def i = 0
            (desde..hasta).each{
                def d = SqlUtil.instance.getDetallePorIndicador(this.id, "StockFabricaDetalle${it}", campoFk, campos[i++])
                if (d)
                    valor += d.valor ?: 0
            }
        }
        return valor
    }

    @DisplaySize(6)
    BigDecimal getTonSacTorSul(){
        return getSumaValores(6, 7, ["TonSacJSulf", "TonSacMel"])
    }

    @DisplaySize(6)
    BigDecimal getTonSacTraJug(){
        return getSumaValores(1, 5, ["TonSacJDil", "TonSacJCla", "TonSacJSulf", "TonSacJFiltr", "TonSacClar"])
    }

    @DisplaySize(6)
    BigDecimal getTonSacCal(){
        def lista = []
        (1..5).each{ lista << "TonSacJC" }
        return getSumaValores(8, 12, lista)
        //return getSumaValores(8, 12, ["TonSacJC", "TonSacJC", "TonSacJC", "TonSacJC", "TonSacJC"])
    }
}
