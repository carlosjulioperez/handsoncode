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
    titLinEva{
        tonSacEva;
        titLinEva1 { detalle13 }
        titLinEva2 { detalle14 }
        titLinEva3 { detalle15 }
        titLinEva4 { detalle16 }
        titLinEva5 { detalle17 }
    }
    titTqMCru { detalle18 }
    titCalMel { 
        tonSacCalMel;
        titCalMel1 { detalle19 }
        titCalMel2 { detalle20 }
    }
    titClaMel { 
        tonSacClaMel;
        detalle21 
    }
    titVasRea { detalle22 }
    titCri{
        ft3, tonSacCri;
        titTac1 { 
            titMasA { detalle23 }
        }
        titTac2 { 
            titMasA { detalle24 }
            titMasB { detalle25 }
        }
        titTac3 { 
            titMasB { detalle26 }
            titMasC { detalle27 }
        }
        titTac4 { 
            titMasC { detalle28 }
        }
    }
    titTanAlm{
        tonSacTqAlm;
        titTanAlm01 { detalle29 }
        titTanAlm02 { detalle30 }
        titTanAlm03 { detalle31 }
        titTanAlm04 { detalle32 }
        titTanAlm05 { detalle33 }
        titTanAlm06 { detalle34 }
        titTanAlm07 { detalle35 }
        titTanAlm08 { detalle36 }
        titTanAlm09 { detalle37 }
        titTanAlm10 { detalle38 }
        titTanAlm11 { detalle39 }
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

    BigDecimal fldTonSacEva
    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle13> detalle13

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle14> detalle14

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle15> detalle15

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle16> detalle16

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle17> detalle17

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle18> detalle18

    BigDecimal fldTonSacCalMel

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle19> detalle19

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle20> detalle20

    BigDecimal fldTonSacClaMel
    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle21> detalle21

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle22> detalle22

    @DisplaySize(10)
    String getFt3(){
       return new Parametro().obtenerValor("FT3")
    }
    
    BigDecimal fldTonSacCri

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle23> detalle23

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle24> detalle24

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle25> detalle25

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle26> detalle26

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle27> detalle27

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle28> detalle28
    
    BigDecimal fldTonSacTqAlm

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle29> detalle29

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle30> detalle30

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle31> detalle31

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle32> detalle32

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle33> detalle33

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle34> detalle34

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle35> detalle35

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle36> detalle36

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle37> detalle37

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle38> detalle38

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle39> detalle39
    
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
            (1..39).each{
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
            this.fldTonSacEva    = tonSacEva
            this.fldTonSacCalMel = tonSacCalMel
            this.fldTonSacClaMel = tonSacClaMel
            this.fldTonSacCri    = tonSacCri
            this.fldTonSacTqAlm  = tonSacTqAlm

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
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacEva(){
        def lista = []
        (1..5).each{ lista << "TonSacMel" }
        return getSumaValores(13, 17, lista)
    }

    @DisplaySize(6)
    BigDecimal getTonSacCalMel(){
        def lista = []
        (1..2).each{ lista << "TonSacCal" }
        return getSumaValores(19, 20, lista)
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacClaMel(){
        return  tonSacCalMel + 
                getSumaValores(21, 21, ["TonSacClaMel"]) + 
                getSumaValores(22, 22, ["TonSacVasR"]) + 
                getSumaValores(18, 18, ["TonSacTqMelCru"])
    }

    @DisplaySize(6)
    BigDecimal getTonSacCri(){
        def lista = []
        (1..6).each{ lista << "TonSacMas" }
        return getSumaValores(23, 28, lista)
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacTqAlm(){
        def lista = []
        (1..3).each{ lista << "TonSacTkMel" }
        (1..2).each{ lista << "TonSacFun" }
        (1..6).each{ lista << "TonSacMieB" }
        return getSumaValores(29, 39, lista)
    }
    
}
