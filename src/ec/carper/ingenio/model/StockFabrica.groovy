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
    titSemVac{
        tonSacCriVac;
        titSemVac1 { detalle40 }
        titSemVac2 { detalle41 }
        titSemVac3 { detalle42 }
        titSemVac4 { detalle43 }
    }
    titRecMagMas{
        tonSacRecMas;
        titRecMagMas1 { detalle44 }
        titRecMagMas2 { detalle45 }
        titRecMagMas3 { detalle46 }
        titRecMagMas4 { detalle47 }
        titRecMagMas5 { detalle48 }
        titRecMagMas6 { detalle49 }
        titRecMagMas7 { detalle50 }
    }
    titAliCen{
        tonSacRecMat;
        titAliCen1 { detalle51 }
        titAliCen2 { detalle52 }
        titAliCen3 { detalle53 }
        titAliCen4 { detalle54 }
    }
    titRecMag{
        tonSacRecCen;
        titRecMag1 { detalle55 }
        titRecMag2 { detalle56 }
        titRecMag3 { detalle57 }
        titRecMag4 { detalle58 }
    }
    titRecMieCen{
        tonSacRecMieCen;
        titRecMieCen1 { detalle59 }
        titRecMieCen2 { detalle60 }
        titRecMieCen3 { detalle61 }
        titRecMieCen4 { detalle62 }
    }
    titFun{
        tonSacFunCriVer;
        titFun1 { detalle63 }
        titFun2 { detalle64 }
    }
    titCriVer { detalle65 }
    titSilProTer{
        tonSacSilAzu;
        titSilProTer1 { detalle66 }
        titSilProTer2 { detalle67 }
        titSilProTer3 { detalle68 }
    }
    titRecMieFinMel{
        tonTot;
        titRecMieFinMel1 { detalle69 }
        titRecMieFinMel2 { detalle70 }
        titRecMieFinMel3 { detalle71 }
    }
    titTotales{
        titTotTonSac { detalle72 }
        titTotGen { detalle73 }
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
    
    BigDecimal fldTonSacCriVac

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle40> detalle40

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle41> detalle41

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle42> detalle42

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle43> detalle43

    BigDecimal fldTonSacRecMas

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle44> detalle44

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle45> detalle45

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle46> detalle46

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle47> detalle47

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle48> detalle48

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle49> detalle49

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle50> detalle50

    BigDecimal fldTonSacRecMat

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle51> detalle51

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle52> detalle52

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle53> detalle53

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle54> detalle54

    BigDecimal fldTonSacRecCen

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle55> detalle55

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle56> detalle56

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle57> detalle57

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle58> detalle58

    BigDecimal fldTonSacRecMieCen

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle59> detalle59

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle60> detalle60

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle61> detalle61

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle62> detalle62

    BigDecimal fldTonSacFunCriVer

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle63> detalle63

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle64> detalle64

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle65> detalle65

    BigDecimal fldTonSacSilAzu

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle66> detalle66

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle67> detalle67

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle68> detalle68

    BigDecimal fldTonTot

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle69> detalle69

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle70> detalle70

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle71> detalle71

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle72> detalle72

    @EditAction("StockFabrica.editDetail")
    @OneToMany (mappedBy="stockFabrica", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockFabricaDetalle73> detalle73

    @DisplaySize(6)
    BigDecimal fldTonAzuDis

    // Totales
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldTonSacTot
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldPesMatTotDia
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldProSolBriTotDia
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldProSacPolTotDia
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldProPzaTotDia   
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldDenKgm         
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldPesSolDia      
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldPesPolDia
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldSjmMatPro
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldSacRecAz
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldAzuRec
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldSacMieFin
    
    @Digits(integer=6, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal fldMieFinRec

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
            (1..73).each{
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

    void actualizar() throws ValidationException{
        try{
            this.fldTonSacTorSul    = tonSacTorSul       //J35
            this.fldTonSacTraJug    = tonSacTraJug       //AW22
            this.fldTonSacCal       = tonSacCal          //AQ34
            this.fldTonSacEva       = tonSacEva          //AR41
            this.fldTonSacCalMel    = tonSacCalMel
            this.fldTonSacClaMel    = tonSacClaMel       //AU67
            this.fldTonSacCri       = tonSacCri          //AU71
            this.fldTonSacTqAlm     = tonSacTqAlm        //AW76
            this.fldTonSacCriVac    = tonSacCriVac       //C101
            this.fldTonSacRecMas    = tonSacRecMas       //AC101
            this.fldTonSacRecMat    = tonSacRecMat       //P115
            this.fldTonSacRecCen    = tonSacRecCen       //AQ115
            this.fldTonSacRecMieCen = tonSacRecMieCen    //R128
            this.fldTonSacFunCriVer = tonSacFunCriVer    //AM128
            this.fldTonSacSilAzu    = tonSacSilAzu       //L139
            this.fldTonTot          = tonTot

            calcularTotales()

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
    
    def getSumaValores(def desde, def hasta, String indicador){
        def campoFk = "stockFabrica.id"
        def (suma, i) = [0, 0]
        if (this.id){
            (desde..hasta).each{
                def d = SqlUtil.instance.getDetallePorIndicador(this.id, "StockFabricaDetalle${it}", campoFk, indicador)
                suma += d ? d.valor: 0
                // if (indicador=="Vt" || indicador=="VTot")
                //     println "${it}, ${d.valor}"
            }
        }
        return suma
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
 
    @DisplaySize(6)
    BigDecimal getTonSacCriVac(){
        return getSumaValores(40, 43, ["TonSacSemA", "TonSacSemB", "TonSacSemC", "TonSacSemC"])
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacRecMas(){
        return getSumaValores(44, 50, ["TonSacMagB", "TonSacMasAI", "TonSacMasAII", "TonSacMasB", "TonSacMasB", "TonSacMasC", "TonSacMasC"])
    }

    @DisplaySize(6)
    BigDecimal getTonSacRecMat(){
        def lista = []
        (1..4).each{ lista << "TonSacJC" }
        return getSumaValores(51, 54, lista)
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacRecCen(){
        return getSumaValores(55, 58, ["TonSacMagB", "TonSacMagR", "TonSacMagC", "TonSacTer"])
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacRecMieCen(){
        return getSumaValores(59, 62, ["TonSacMieA", "TonSacMieA", "TonSacMieB", "TonSacMieCRep"])
    }
    
    @DisplaySize(6)
    BigDecimal getTonSacFunCriVer(){
        return getSumaValores(63, 65, ["TonSacEnFun", "TonSacEnFun", "TonSacMasCV"])
    }

    @DisplaySize(6)
    BigDecimal getTonSacSilAzu(){
        def lista = []
        (1..3).each{ lista << "TonSacAzu" }
        return getSumaValores(66, 68, lista)
    }
    
    @DisplaySize(6)
    BigDecimal getTonTot(){
        return getSumaValores(70, 71, ["TonMF", "TonMF"])
    }
    
    def calcularTotales(){

        def bg144 = (tonSacTorSul + tonSacTraJug + tonSacCal + tonSacEva + tonSacClaMel + tonSacCri + tonSacTqAlm + tonSacCriVac + tonSacRecMas + tonSacRecMat + tonSacRecCen + tonSacRecMieCen + tonSacFunCriVer + tonSacSilAzu - (fldTonAzuDis?:0) )
        //println ">>> tonAzu: ${tonAzu},  suma: ${suma}"
        
        def bg149 = Calculo.instance.redondear((
            getSumaValores(1 , 28, "Brix") + 
            getSumaValores(30, 38, "Brix") +  
            getSumaValores(40, 42, "Brix") +  
            getSumaValores(44, 47, "Brix") +  
            getSumaValores(49, 49, "Brix") +  
            getSumaValores(51, 65, "Brix") +
            getSumaValores(66, 68, "Bx")) / 63, 3
        )
        
        def bg150 = Calculo.instance.redondear((
            getSumaValores(1 , 28, "Sac") + 
            getSumaValores(30, 38, "Sac") +  
            getSumaValores(40, 42, "Sac") +  
            getSumaValores(44, 47, "Sac") +  
            getSumaValores(49, 49, "Sac") +  
            getSumaValores(51, 68, "Sac")) / 63, 3
        )
        
        def bg151 = Calculo.instance.redondear(bg149 ? bg150/bg149*100: 0, 3)
        def bg152 = new BrixDensidadWp().getP(bg149)
        
        def bg147 = Calculo.instance.redondear((
            getSumaValores( 1 , 4  , "Vt") +
            getSumaValores( 8 , 12 , "Vt") +
            getSumaValores(23 , 28 , "Vt") +
            getSumaValores(40 , 42 , "Vt") +
            getSumaValores(44 , 47 , "Vt") +
            getSumaValores(49 , 49 , "Vt") +
            getSumaValores(65 , 68 , "Vt") +
            getSumaValores(5  , 7  , "VTot") +
            getSumaValores(13 , 22 , "VTot") +
            getSumaValores(30 , 38 , "VTot") +
            getSumaValores(51 , 64 , "VTot") ) * (bg152/1000), 3
        )
        
        def bg153 = Calculo.instance.redondear(bg147*bg149/100, 3)
        def bg154 = Calculo.instance.redondear(bg147*bg150/100, 3)

        def q133 = getSumaValores(67 , 67  , "Sac")
        def u150 = getSumaValores(70 , 70  , "Pza")
        def bg155 = Calculo.instance.redondear((q133*(bg151-u150))/(bg151*(q133-u150))*100 , 3)
        
        def bg156 = Calculo.instance.redondear(bg154*bg155/100, 3)
        def bg157 = Calculo.instance.redondear((bg156/q133)*100, 3)
        def bg158 = Calculo.instance.redondear(bg154-bg156, 3)
        
        def u146 = getSumaValores(70 , 70  , "Sac")
        def bg159 = Calculo.instance.redondear(bg158/u146*100, 3)
        
        this.fldTonSacTot       = bg144
        this.fldPesMatTotDia    = bg147
        this.fldProSolBriTotDia = bg149
        this.fldProSacPolTotDia = bg150
        this.fldProPzaTotDia    = bg151
        this.fldDenKgm          = bg152
        this.fldPesSolDia       = bg153
        this.fldPesPolDia       = bg154
        this.fldSjmMatPro       = bg155
        this.fldSacRecAz        = bg156
        this.fldAzuRec          = bg157
        this.fldSacMieFin       = bg158
        this.fldMieFinRec       = bg159
        getManager().persist(this)

        println ""
        println ">>> bg147: ${bg147}"
        println ">>> bg149: ${bg149}"
        println ">>> bg150: ${bg150}"
        println ">>> bg151: ${bg151}"
        println ">>> bg152: ${bg152}"
        println ">>> bg153: ${bg153}"
        println ">>> bg154: ${bg154}"
        println ">>> bg155: ${bg155}"
        println ">>> bg156: ${bg156}"
        println ">>> bg157: ${bg157}"
        println ">>> bg158: ${bg158}"
        println ">>> bg159: ${bg159}"

    }
    
}
