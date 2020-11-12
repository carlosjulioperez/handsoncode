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
    titDatDia { detalle1 }
    titTie { 
        detalle17
    }
    titVarPri {
        cana { 
            titMetLabCan { detalle21 }
            titMetBal { detalle22 }
        }
        bagazo { detalle3 }
        mielFinalMelaza { detalle4 }
        jugoDiluido { detalle5 }
        jugoClaro { detalle6 }
        jugoPrimeraExtraccion { detalle7 }
        jugoResidual { detalle8 }
        cachaza { detalle9 }
        azucarGranel { 
            azucarGranel { detalle101 }
            grasshoper { detalle102 }
        }
    }
    titProAzuPre { calQqTotalesDia; detalle11 }
    titCalFab { detalle12 }
    titConSerInsFab { detalle13 }
    titAnaRutEspFab {
        titSeccion1 { detalle14 }
        titSeccion2 { detalle15 }
        titSeccion3 { detalle16 }
    }
""")
class Blc extends Formulario {
    
    @OnChange(BlcShowHideCargarItemsAction.class)
    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    @EditAction("Blc.editDetail")
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle1>detalle1
    
    @Column(length=8)
    String fldTiempoPerdidoTotal

    @Column(length=8)
    String fldTiempoMoliendaReal

    BigDecimal fldFraccionTiempo

    BigDecimal fldRataMolienda

    BigDecimal fldPorcTot

    // @ElementCollection @ReadOnly @Transient
    // @ListProperties(""" 
    //     area.descripcion,
    //     totalParo [ blc.tiempoPerdidoTotal, blc.tiempoMoliendaReal, blc.fraccionTiempo, blc.rataMolienda, blc.porcTot ] """)
    // Collection<ParoTotal> consultaParo 

    @ElementCollection @ReadOnly
    @ListProperties(""" 
        area.descripcion,
        totalParo [ blc.tiempoPerdidoTotal, blc.tiempoMoliendaReal, blc.fraccionTiempo, blc.rataMolienda, blc.porcTot ] """)
    Collection<BlcDetalle17> detalle17
    
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle21> detalle21

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle22> detalle22

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle3> detalle3

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle4> detalle4

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle5> detalle5

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle6> detalle6

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle7> detalle7

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle8> detalle8

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle9> detalle9

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle101> detalle101

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle102> detalle102

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden")
    @ListProperties(""" 
        orden,
        granel [ blc.sumGranel ],
        k5     [ blc.sumK5 ],
        k2     [ blc.sumK2 ],
        k1     [ blc.sumK1 ],
        g500   [ blc.sumG500   , blc.calG500   ],
        g250   [ blc.sumG250   , blc.calG250   ],
        arroba [ blc.sumArroba , blc.calArroba ] """)
    Collection<BlcDetalle11> detalle11

    @EditAction("Blc.editDetail12")
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle12> detalle12

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<BlcDetalle13> detalle13

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle14> detalle14

    BigDecimal color
    BigDecimal turbiedad

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    @ListProperties(""" 
        orden, material.descripcion, cenizasCon, ph, 
        colorTur1 [ blc.promColor ],
        colorTur2 [ blc.promTurbiedad ] """)
    Collection<BlcDetalle15> detalle15

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle16> detalle16
    
    BigDecimal granel
    BigDecimal k5
    BigDecimal k2
    BigDecimal k1
    BigDecimal g500
    BigDecimal g250
    BigDecimal arroba
    BigDecimal g5002
    BigDecimal g2502
    BigDecimal arroba2
    BigDecimal qqTotalesDia

    BigDecimal getSumGranel()       { return super.getSuma(detalle11, "granel") }
    BigDecimal getSumK5()           { return super.getSuma(detalle11, "k5") }
    BigDecimal getSumK2()           { return super.getSuma(detalle11, "k2") }
    BigDecimal getSumK1()           { return super.getSuma(detalle11, "k1") }
    BigDecimal getSumG500()         { return super.getSuma(detalle11, "g500") }
    BigDecimal getSumG250()         { return super.getSuma(detalle11, "g250") }
    BigDecimal getSumArroba()       { return super.getSuma(detalle11, "arroba") }
    BigDecimal getCalG500()         { return sumG500/2 }
    BigDecimal getCalG250()         { return sumG250/2 }
    BigDecimal getCalArroba()       { return Calculo.instance.redondear(sumArroba*11.34/50, 2) }
    BigDecimal getCalQqTotalesDia() { return sumGranel + sumK5 + sumK2 + sumK1 + calG500 + calG250 + calArroba }

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
            // DATOSDIA
            def lista = getManager().createQuery("FROM BlcPDetalle1 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle1(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad, unidad2: it.unidad2, modificable: it.modificable)
                getManager().persist(d)
            }

            // VARIABLES PRIMARIAS
            // Método Laboratorio de Caña
            lista = getManager().createQuery("FROM BlcPDetalle21 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle21(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Método Balance
            lista = getManager().createQuery("FROM BlcPDetalle22 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle22(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Bagazo
            lista = getManager().createQuery("FROM BlcPDetalle3 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle3(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Miel Fina Melaza
            lista = getManager().createQuery("FROM BlcPDetalle4 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle4(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Jugo diluido
            lista = getManager().createQuery("FROM BlcPDetalle5 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle5(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Jugo Claro
            lista = getManager().createQuery("FROM BlcPDetalle6 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle6(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Jugo Primera Extracción
            lista = getManager().createQuery("FROM BlcPDetalle7 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle7(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Jugo Residual
            lista = getManager().createQuery("FROM BlcPDetalle8 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle8(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Cachaza
            lista = getManager().createQuery("FROM BlcPDetalle9 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle9(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Azucar granel
            lista = getManager().createQuery("FROM BlcPDetalle101 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle101(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // Grasshoper
            lista = getManager().createQuery("FROM BlcPDetalle102 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle102(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
                getManager().persist(d)
            }

            // CalculoFabrica 
            lista = getManager().createQuery("FROM BlcPDetalle12 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle12(blc: blc, orden: it.orden, indicador: it.indicador, unidad: it.unidad, unidad2: it.unidad2, modificable: it.modificable)
                getManager().persist(d)
            }

            // CONSUMOS - SERVICIOS E INSUMOS FABRICA
            lista = getManager().createQuery("FROM BlcPDetalle13 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle13(blc: blc, orden: it.orden, indicador: it.indicador, unidad: it.unidad)
                getManager().persist(d)
            }

            // ANALISIS RUTINARIOS Y ESPECIALES FABRICA
            lista = getManager().createQuery("FROM BlcPDetalle14 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle14(blc: blc, orden: it.orden, material: it.material)
                getManager().persist(d)
            }

            lista = getManager().createQuery("FROM BlcPDetalle15 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle15(blc: blc, orden: it.orden, material: it.material)
                getManager().persist(d)
            }

            lista = getManager().createQuery("FROM BlcPDetalle16 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle16(blc: blc, orden: it.orden, material: it.material)
                getManager().persist(d)
            }

        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void consultarDatos() throws ValidationException{
        try{ 
            consultarParoTotal()
            consultarMetLabCan()
            consultarMetBal()
            consultarBagazo()
            consultarMielFinaMelaza()
            consultarJugoDiluido()
            consultarJugoClaro()
            consultarJugoPrimeraExtraccion()
            consultarJugoResidual()
            consultarCachaza()
            consultarAzucarGranelGrasshoper()
            consultarCalculoFabrica()
            consultarAnalisisRutinariosEspecialesFabrica()

        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultarParoTotal(){
        detalle17 = new ArrayList<BlcDetalle17>() //Inicializar el detalle
        def lista = getManager().createQuery("FROM Paro where diaTrabajo.id = :id")
                                .setParameter("id", diaTrabajo.id).resultList
        lista.each{
            it.total.each{
                detalle17.add( new BlcDetalle17 (area: it.area, totalParo: it.totalParo) )
            }
        }
        // Actualizar los totales del paro en la cabecera
        fldTiempoPerdidoTotal = tiempoPerdidoTotal
        fldTiempoMoliendaReal = tiempoMoliendaReal
        fldFraccionTiempo     = new BigDecimal(fraccionTiempo)
        fldRataMolienda       = new BigDecimal(rataMolienda)
        fldPorcTot            = new BigDecimal(porcTot)
    }

    def consultarMetLabCan(){
        detalle21.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "trash":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Trash" , "avgPorcTrash")
                    break
                case "brixCanaDac":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cana" , "brix")
                    break
                case "sacCanaDac":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cana" , "porcSacarosa")
                    break
                case "pzaCanaDac":
                    //it.valor = (=+D37/D36*100)
                    def sac  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle21" , "sacCanaDac")
                    def brix = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle21" , "brixCanaDac")
                    it.valor = brix ? Calculo.instance.redondear(sac/brix*100,2): 0
                    break 
                case "fibCana":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cana" , "porcFibra")
                    break
                case "humCana":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cana" , "porcHumedad")
                    break
            }
            getManager().persist(it)
        }
    }

    def consultarMetBal(){
        detalle22.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "brixCana":
                    //def cdV = getValor("canaDia", 1)
                    def d16 = getValor("canaNeta", 1)
                    def d17 = getValor("jugoNeto", 1)
                    def h48 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdBri")
                    def d11 = getValor("bagazoC", 1)
                    def h44 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "brix")
                    def d42 = d16 ? Calculo.instance.redondear((d17*h48 + d11*h44)/d16, 2): 0 //brix cana
                    it.valor = d42
                    break 
                
                case "sacCana":
                    def d16 = getValor("canaNeta", 1)
                    def d17 = getValor("jugoNeto", 1)
                    def d11 = getValor("bagazoC", 1)
        
                    def h49 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdSac")
                    // =+($D$17*H49+$D$11*H41)/$D$16
                    def h41 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcSacarosa")
                    def d43 = d16 ? Calculo.instance.redondear((d17*h49 + d11*h41)/d16, 2): 0 //sac cana
                    it.valor = d43
                    break 
                
                case "pzaCana":
                    def d42 = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle22" , "brixCana")
                    def d43 = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle22" , "sacCana")
                    def d44 = d42 ? Calculo.instance.redondear(d43/d42*100, 2): 0
                    it.valor = d44
                    break 
                
                case "fibCana":
                    def d11 = getValor("bagazoC", 1)
                    def h42 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcFibra")
                    def d16 = getValor("canaNeta", 1)
                    def d45 = d16 ? Calculo.instance.redondear(d11*h42/d16, 2): 0
                    it.valor = d45
                    break 
                
                case "sacJBrCanaExtraida":
                    def d8  = getValor("canaNeta", 1)
                    def f10 = getValor("jDiluidoBr", 2)
                    def h49 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdSac")
                    def d46 = d8 ? Calculo.instance.redondear(h49*f10/d8, 2): 0
                    it.valor = d46
                    break 
            }
            getManager().persist(it)
        }
    }

    def consultarBagazo(){
        detalle3.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "sacBagazo":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcSacarosa")
                    break 
                case "fibBagazo":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcFibra")
                    break 
                case "humBagazo":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcHumedad")
                    break 
                case "brixBagazo":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "brix")
                    break 
                case "podCal":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "BtuLbBagazo" , "pcBtuLb")
                    break 
                case "cenSeca":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "BtuLbBagazo" , "porcCenBs")
                    break
            }
        }
    }

    def consultarMielFinaMelaza(){
        detalle4.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "brixMf":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Mieles" , "mfBri2")
                    break 
                case "sacMf":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Mieles" , "mfSac")
                    break 
                case "pzaMf":
                    def sac  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle4" , "sacMf")
                    def brix = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle4" , "brixMf")
                    it.valor = brix ? Calculo.instance.redondear(sac/brix*100,2): 0
                    break 
                case "cenMf":
                    it.valor = SqlUtil.instance.getValorDetalleCampo(diaTrabajo.id, "cto24H", "Cto24HDetalle5" , "porcCenizas")
                    break 
                case "arAsh":
                    def cenMf  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle4" , "cenMf")
                    def mielF2 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H", "mielF2")
                    it.valor   = cenMf ? Calculo.instance.redondear(mielF2/cenMf,2): 0
                    break 
                case "galTcm":
                    def d      = SqlUtil.instance.getDetallePorDTI(diaTrabajo.id, "blc", "BlcDetalle12", "KilMieTonCan")
                    def k822   = d ? (d.totalZafra?:0): 0

                    def mfBri2 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mfBri2")
                    def l41    = mfBri2
                    //         = (K82/(BUSCAR(L41,'Brix y Densidad'!A3:B953)))*(1000/3.785)
                    def p      = new BrixDensidadWp().getP(l41)
                    def l46    = p ? Calculo.instance.redondear( (k822/p)*(1000/3.785), 2): 0
                    it.valor   = l46
                    break 
            }
            getManager().persist(it)
        }
    }
    
    def consultarJugoDiluido(){
        def brixJDil = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdBri")
        def sacJDil  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdSac")
        
        detalle5.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "rhoJugDil":
                    it.valor = new BrixDensidadWp().getP(brixJDil)
                    break
                case "solInsol":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H", "porcInso")
                    break
                case "fosfatos":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Fosfatos", "jdFosfatos")
                    break
                
                case "jDiluidoBr":
                    def d8  = getValor("canaNeta", 1)
                    def f10 = getValor("jDiluidoBr", 2)
                    def d51 = d8 ? Calculo.instance.redondear(f10*100/d8, 2): 0
                    it.valor = d51
                    break
                
                case "brixJDil":
                    it.valor = brixJDil
                    break
                case "sacJDil":
                    it.valor = sacJDil
                    break
                case "pzaJDil":
                    it.valor = brixJDil ? Calculo.instance.redondear(sacJDil*100/brixJDil,2): 0
                    break
            }
            getManager().persist(it)
        }
    }
    
    def consultarJugoClaro(){
        detalle6.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "turbiedad":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "turJClaro")
                    break
                case "fosfatos":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Fosfatos", "jcFosfatos")
                    break
                case "brixJClaro":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcBri")
                    break
                case "sacJClaro":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcSac")
                    break
                case "pzaJClaro":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcPur")
                    break
            }
            getManager().persist(it)
        }
    }
    
    def consultarJugoPrimeraExtraccion(){
        detalle7.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "brixJPEx":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jeBri")
                    break
                case "sacJPEx":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jeSac")
                    break
                case "pzaJPEx":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jePur")
                    break
            }
            getManager().persist(it)
        }
    }
    
    def consultarJugoResidual(){
        detalle8.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "brixJRes":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jrBri")
                    break
                case "sacJRes":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jrSac")
                    break
                case "pzaJRes":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jrPur")
                    break
            }
            getManager().persist(it)
        }
    }
    
    def consultarCachaza(){
        detalle9.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "polCachaza":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "polCachaza")
                    break
                case "briCachaza":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "briCachaza")
                    break
                case "humCachaza":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "humCachaza")
                    break
                case "rataEvap":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H", "porcConTotLinEva")
                    break
                case "ip":
                    it.valor = SqlUtil.instance.getValorDetalleCampo(diaTrabajo.id, "cto24H", "Cto24HDetalle6" , "porc")
                    break
            }
            getManager().persist(it)
        }
    }

    def consultarAzucarGranelGrasshoper(){
        detalle101.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "color":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "AzucarGranel", "color")
                    break
                case "turbiedad":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "AzucarGranel", "turb")
                    break
                case "polAzucar":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "AzucarGranel", "pol")
                    break
                case "humedad":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "AzucarGranel", "humedad")
                    break
            }
            getManager().persist(it)
        }

        detalle102.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "color":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Grasshoper", "color")
                    break
                case "turbiedad":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Grasshoper", "turb")
                    break
                case "humedad":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Grasshoper", "humedad")
                    break
            }
            getManager().persist(it)
        }

    }

    def consultarCalculoFabrica(){

        def cdV = getValor("canaDia", 1)
        def solInsol = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Cto24H"       , "porcInso")
        
        def h41  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcSacarosa")
        
        def d11  = getValor("bagazoC", 1)
        def d17  = getValor("jugoNeto", 1)

        def d49  = solInsol
        def h49  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jdSac")
        def h60  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jrPur")
        def h43  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "porcHumedad")
        def s16  = h60 ? Calculo.instance.redondear((h41/h60)*100, 2): 0
        def s17  = 100-h43-s16
        def d13  = getValor("cachaza", 1)
        def l48  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Turbiedad", "polCachaza")
        def d14  = getValor("mielFM", 1)
        def l42  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Mieles" , "mfSac")
        def f15  = getValor("azucarB", 2)
        def l55  = SqlUtil.instance.getValorCampo(diaTrabajo.id, "AzucarGranel", "pol")
        
        def diaFin = diaTrabajo.numeroDia - 1
        def h63A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacJDil"   , diaFin)
        def h64A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacCan"    , diaFin)
        def h65A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSolIns"    , diaFin)
        def h66A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonFibBag"    , diaFin)
        def h67A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonFibCan"    , diaFin)
        def h68A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacBag"    , diaFin)
        def h69A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacCac"    , diaFin)
        def h70A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacMieFin" , diaFin)
        def h71A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacAzuHec" , diaFin)
        def k81A = SqlUtil.instance.getValIndBlcAcu("BlcDetalle12" , "TonSacRec"    , diaFin)

        def h63 = Calculo.instance.redondear(d17*(h49/100), 2)
        def h68 = Calculo.instance.redondear(d11*(h41/100), 2)
        def h64 = h63 + h68
        def f10 = getValor("jDiluidoBr", 2)
        def h65 = Calculo.instance.redondear(f10*(d49/100), 2)
        def h66 = Calculo.instance.redondear(d11*(s17/100), 2)
        def h67 = h65 + h66
        def h69 = Calculo.instance.redondear(d13*l48/100, 2)
        def h70 = Calculo.instance.redondear(d14*l42/100, 2)
        def h71 = Calculo.instance.redondear(f15*l55/100, 2)

        def k72 = h64 ? Calculo.instance.redondear((h63/h64)*100, 2): 0
                    
        def d16 = getValor("canaNeta", 1)
        def s24 = d16 ? Calculo.instance.redondear(h67/d16*100, 2): 0
        def s49 = s24 ? Calculo.instance.redondear( (100-k72)*(100-s24)/s24, 2): 0
        def k73 = 100 - Calculo.instance.redondear(s49/7, 2)
        def d9  = getValor("aguaM", 1) //amV 
        def k74 = h67 ? Calculo.instance.redondear(d9/h67*100, 2): 0
        def k75 = d16 ? Calculo.instance.redondear(d9/d16*100, 2): 0

        def k76 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Bagazo" , "gradosAguaMac") // ='Stock Proceso'!H69 BAGAZO!P30
        def k77 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "StockProceso" , "tonSac")  // ='Stock Proceso'!L67
        def k78 = SqlUtil.instance.getValorBlcCenicana(diaTrabajo.id, 73)   // ='BLC Cenicaña'!D59
        def k79 = k78 ? Calculo.instance.redondear(k77*k78/100, 2): 0
    
        // k81 = 'Stock Fabrica'!AP152
        
        // =K79+(F15*L55/100)-K81
        def k80  = k79 + Calculo.instance.redondear(f15*l55/100, 2 ) - k81A
        def l14  = getValorBlc("mielFM", 3)
        def k84  = l14
        def d    = SqlUtil.instance.getDetallePorIndicador(diaTrabajo.id, "StockFabricaDetalle73", "stockFabrica.diaTrabajo.id", "tonMelProTotDiaAnt")
        def k85  = d ? (d.valor?:0): 0
        def k86  = k84-k85
        def k83  = Calculo.instance.redondear(k86*l42/100, 2)
        def k82  = (d16*(l42/100))!= 0 ? Calculo.instance.redondear((k83*1000)/(d16*(l42/100)), 2): 0 
        //       = (100*'BLC Cenicaña'!D57*(H50-L43))/(H50*('BLC Cenicaña'!D57-L43))
        def k87  = SqlUtil.instance.getValorBlcCenicana(diaTrabajo.id, 60)   //                                                                        = 'BLC Cenicaña'!D59
        def h50  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle5" , "pzaJDil")
        def k88  = h50 ? Calculo.instance.redondear((1.4-(40/h50))*100 ,2): 0
        def k89  = k87 ? Calculo.instance.redondear(k80/k87, 2): 0
        def d43  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle22" , "sacCana")
        def k100 = Calculo.instance.redondear((d16*d43/100)*20, 2)
        def k101 = k100 ? Calculo.instance.redondear( ((k80*20)/k100)*100, 2): 0
        def k90  = k88 ? Calculo.instance.redondear(k101/k88, 2): 0
        def k91  = l55 ? Calculo.instance.redondear((d17*h49/100)*k87*k89*(1/l55), 2): 0
        def k92  = d16 ? Calculo.instance.redondear(k91/d16*100, 2): 0
        def k93  = d16 ? Calculo.instance.redondear(k80/d16*100, 2): 0
        def k94  = d43 - k93
        def k95  = Calculo.instance.redondear(d16*k94/100, 2)
        def i96  = h68
        def l96  = d16 ? Calculo.instance.redondear(i96/d16*100, 2): 0
        def i97  = h69 + h70
        def l97  = d16 ? Calculo.instance.redondear(i97/d16*100, 2): 0
        def l98  = k94 - l97 - l96
        def i98  = Calculo.instance.redondear(d16*l98/100, 2)
        def i99  = i98 + i97
        def l99  = l98 + l97
        def d15  = getValorBlc("azucarB", 1)
        def k102 = k100 ? Calculo.instance.redondear(d15*100/k100, 2): 0
        def k103 = 0 //Temporalmente ingresado desde BlcAdmin
        def k104 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "StockProceso" , "sacarosaSilos")  // ='Stock Proceso'!L68
        def k105 = SqlUtil.instance.getValorBlcCenicana(diaTrabajo.id, 98)   // ='BLC Cenicaña'!H72
        
        detalle12.each{
            def campo = it.indicador.campo ?: ""
            switch (campo){
                case "TonSacJDil"         : setD12(campo, h63, h63A, h63+h63A); break
                case "TonSacCan"          : setD12(campo, h64, h64A, h64+h64A); break
                case "TonSolIns"          : setD12(campo, h65, h65A, h65+h65A); break
                case "TonFibBag"          : setD12(campo, h66, h66A, h66+h66A); break
                case "TonFibCan"          : setD12(campo, h67, h67A, h67+h67A); break
                case "TonSacBag"          : setD12(campo, h68, h68A, h68+h68A); break
                case "TonSacCac"          : setD12(campo, h69, h69A, h69+h69A); break
                case "TonSacMieFin"       : setD12(campo, h70, h70A, h70+h70A); break
                case "TonSacAzuHec"       : setD12(campo, h71, h71A, h71+h71A); break
                case "ExtPol"             : setD12(campo, 0, k72, 0); break
                case "ExtRed"             : setD12(campo, 0, k73, 0); break
                case "MacPorFib"          : setD12(campo, 0, k74, 0); break
                case "MacPorCan"          : setD12(campo, 0, k75, 0); break
                case "TemAguMac"          : setD12(campo, 0, k76, 0); break
                case "TonEstStoFab"       : setD12(campo, 0, 0, k77); break
                case "RecTeoSJMMatPro"    : setD12(campo, 0, k78, 0); break
                case "TonSacRec"          : setD12(campo, 0, 0, k79); break
                case "TonAzuHecEst"       : setD12(campo, 0, k80, 0); break
                case "StoDiaAnt"          : setD12(campo, 0, 0, k81A); break
                case "KilMieTonCan"       : setD12(campo, 0, 0, k82); break
                case "TonSacMieFinHyE"    : setD12(campo, 0, 0, k83); break
                case "TonTotEstMieFinHoy" : setD12(campo, 0, 0, k84); break
                case "StoTotDiaAnt"       : setD12(campo, 0, 0, k85); break
                case "MieEst"             : setD12(campo, 0, 0, k86); break
                case "RecTeoSJM"          : setD12(campo, 0, k87, 0); break
                case "RecTeoWin"          : setD12(campo, 0, k88, 0); break
                case "EfiSJM"             : setD12(campo, 0, k89, 0); break
                case "EfiWin"             : setD12(campo, 0, k90, 0); break
                case "TonAzuRec"          : setD12(campo, 0, k91, 0); break
                case "RenTeo"             : setD12(campo, 0, k92, 0); break
                case "Ren"                : setD12(campo, 0, k93, 0); break
                case "PerTotPorCan"       : setD12(campo, 0, k94, 0); break
                case "TonPerTot"          : setD12(campo, 0, k95, 0); break
                case "PerMol"             : setD12(campo, i96, 0, l96); break
                case "PerCacMieFin"       : setD12(campo, i97, 0, l97); break
                case "PerIndElaMol"       : setD12(campo, i98, 0, l98); break
                case "PerElaMol"          : setD12(campo, i99, 0, l99); break
                case "QQEspTeo"           : setD12(campo, 0, k100, 0) ; break
                case "RecTotSacPorCan"    : setD12(campo, 0, k101, 0) ; break
                case "RecCom"             : setD12(campo, 0, k102, 0) ; break
                case "SacSilDiaAnt"       : setD12(campo, 0, k103, 0) ; break
                case "SacSilHoy"          : setD12(campo, 0, k104, 0) ; break
                case "EfiEla"             : setD12(campo, 0, k105, 0) ; break
            }
        }
    }   
    
    def getSacTeoRed(def g, i){
        return Calculo.instance.redondear((i-g)*0.95, 2)
    }

    BigDecimal getPromColor(){
        return color?:0
    }

    BigDecimal getPromTurbiedad(){
        return turbiedad?:0
    }

    def consultarAnalisisRutinariosEspecialesFabrica(){

        def g176 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "TrashCana" , "avgPorcAzuRed")
        def g177 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "j1Extracto2")
        def g178 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jDiluido2")
        def g179 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jClaro2")
        def g180 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jFiltrado2")
        def g181 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mClara2")
        def g182 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielA2")
        def g183 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielB2")
        def g184 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielF2")

        def i176 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "cana")
        def i177 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "j1Extracto")
        def i178 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jDiluido")
        def i179 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jClaro")
        def i180 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jFiltrado")
        def i181 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mClara")
        def i182 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielA")
        def i183 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielB")
        def i184 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielF")

        def g187 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "j1Extracto3")
        def g188 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jDiluido3")
        def g191 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jClaro3")
        def g193 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "jFiltrado3")
        def g195 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mClara3")
        def g196 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Cto24H" , "mielA3")

        def i187 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "j1Extracto")
        def i188 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "jDiluido")
        def i190 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "jEncalado")
        def i191 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "jClaro")
        def i193 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "jFiltrado")
        def i194 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "mCruda")
        def i195 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Ph" , "mClarificada")

        def k188 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgColor1")
        def l188 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgTurb1")
        def k191 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgColor2")
        def l191 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgTurb2")
        def k194 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgColor3")
        def l194 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgTurb3")
        def k195 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgColor4")
        def l195 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgTurb4")
        def k196 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgColor5")
        def l196 = SqlUtil.instance.getValorCampo(diaTrabajo.id, "ColorMat" , "avgTurb5")

        def proCol = k194 ? Calculo.instance.redondear( ((k194-k195)/k194)*100, 2): 0
        def proMat = l194 ? Calculo.instance.redondear( ((l194-l195)/l194)*100, 2): 0

        detalle14.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "can"    : setD14( campo, g176, i176, getSacTeoRed(g176, i176) ); break
                case "j1Ext"  : setD14( campo, g177, i177, getSacTeoRed(g177, i177) ); break
                case "jDil"   : setD14( campo, g178, i178, getSacTeoRed(g178, i178) ); break
                case "jCla"   : setD14( campo, g179, i179, getSacTeoRed(g179, i179) ); break
                case "jFil"   : setD14( campo, g180, i180, getSacTeoRed(g180, i180) ); break
                case "mCla"   : setD14( campo, g181, i181, getSacTeoRed(g181, i181) ); break
                case "mieA"   : setD14( campo, g182, i182, getSacTeoRed(g182, i182) ); break
                case "mieB"   : setD14( campo, g183, i183, getSacTeoRed(g183, i183) ); break
                case "mielFM" : setD14( campo, g184, i184, getSacTeoRed(g184, i184) ); break
            }
        }

        detalle15.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "j1Ext"   : setD15( campo, g187, i187, 0, 0       ) ; break
                case "jDilDia" : setD15( campo, g188, i188, k188, l188 ) ; break
                case "jEnc"    : setD15( campo, 0, i190, 0 , 0         ) ; break
                case "jClaDia" : setD15( campo, g191, i191, k191, l191 ) ; break
                case "jFil"    : setD15( campo, g193, i193, 0, 0       ) ; break
                case "melCru"  : setD15( campo, 0, i194, k194, l194    ) ; break
                case "melCla"  : setD15( campo, g195, i195, k195, l195 ) ; break
                case "mieA"    : setD15( campo, g196, 0, k196, l196    ) ; break
            }
        }

        // println "REMOCION ESTACION DE CLARIFICACION DE MELADURA| " + getCadena (0, 0, proCol+' '+proMat)    
        this.color     = proCol
        this.turbiedad = proMat
        
        // getManager().persist(this)

        def g199 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jfBri"   )
        def i199 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jfSac"   )
        def l199 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Jugo"         , "jfPur"   )

        def g200 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mcrBri2" )
        def i200 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mcrSac" )
        def l200 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mcrPur" )

        def g201 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mclBri2" )
        def i201 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mclSac" )
        def l201 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Meladura"     , "mclPur" )

        def g202 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "maBri2"  )
        def i202 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "maSac"  )
        def l202 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "maPur"  )

        def g203 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mbBri2"  )
        def i203 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mbSac"  )
        def l203 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mbPur"  )

        def g204 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mcBri2"  )
        def i204 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mcSac"  )
        def l204 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Masas"        , "mcPur"  )

        def g205 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "maBri2"  )
        def i205 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "maSac"  )
        def l205 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "maPur"  )

        def g206 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mbBri2"  )
        def i206 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mbSac"  )
        def l206 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mbPur"  )

        def g207 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mrBri2"  )
        def i207 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mrSac"  )
        def l207 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Mieles"       , "mrPur"  )

        def g208 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "maBri2"  )
        def i208 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "maSac"  )
        def l208 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "maPur"  )

        def g209 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mbBri2"  )
        def i209 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mbSac"  )
        def l209 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mbPur"  )

        def g210 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mcBri2"  )
        def i210 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mcSac"  )
        def l210 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "MielesNutsch" , "mcPur"  )

        def g211 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mbBri2"  )
        def i211 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mbSac"  )
        def l211 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mbPur"  )

        def g212 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mcBri2"  )
        def i212 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mcSac"  )
        def l212 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mcPur"  )

        def g213 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mrBri2"  )
        def i213 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mrSac"  )
        def l213 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "Magmas"       , "mrPur"  )

        def g214 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "TqFundidor"   , "bri2"    )
        def i214 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "TqFundidor"   , "sac"    )
        def l214 = SqlUtil.instance.getValorCampo(diaTrabajo.id , "TqFundidor"   , "pur"    )
        
        detalle16.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "jFil"    : setD16( campo, g199, i199, l199 ) ; break
                case "melCru"  : setD16( campo, g200, i200, l200 ) ; break
                case "melCla"  : setD16( campo, g201, i201, l201 ) ; break
                case "masA"    : setD16( campo, g202, i202, l202 ) ; break
                case "masB"    : setD16( campo, g203, i203, l203 ) ; break
                case "masC"    : setD16( campo, g204, i204, l204 ) ; break
                case "mieA"    : setD16( campo, g205, i205, l205 ) ; break
                case "mieB"    : setD16( campo, g206, i206, l206 ) ; break
                case "mieRP"   : setD16( campo, g207, i207, l207 ) ; break
                case "mieNutA" : setD16( campo, g208, i208, l208 ) ; break
                case "mieNutB" : setD16( campo, g209, i209, l209 ) ; break
                case "mieNutC" : setD16( campo, g210, i210, l210 ) ; break
                case "magB"    : setD16( campo, g211, i211, l211 ) ; break
                case "magC"    : setD16( campo, g212, i212, l212 ) ; break
                case "magRP"   : setD16( campo, g213, i213, l213 ) ; break
                case "fun"     : setD16( campo, g214, i214, l214 ) ; break
            }
        }
    }

    // Si un total es de tipo String, todos los demás también deben serlo.
    String getTiempoPerdidoTotal(){
        // return diaTrabajo ? SqlUtil.instance.getCampo(diaTrabajo.id, "Paro" , "totalParada"): "00:00:00"
        return diaTrabajo ? SqlUtil.instance.getCampo(diaTrabajo.id, "Paro" , "totalParada"): ""
    }

    String getTiempoMoliendaReal(){
        return Util.instance.getDiferenciaHoras(tiempoPerdidoTotal, "24:00:00")
    }
    
    String getFraccionTiempo(){
        return Calculo.instance.getFraccionTiempo(tiempoMoliendaReal) ?:0
    }

    String getRataMolienda(){
        BigDecimal fraccion = new BigDecimal(fraccionTiempo)
        return fraccion ? 
            Calculo.instance.redondear(
                SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle1" , "canaDia") / fraccion
            , 2) : 0
    }

    String getPorcTot(){
        // =100-(fraccionTiempo/24*100)
        BigDecimal fraccion = new BigDecimal(fraccionTiempo)
        //println ">>> fraccion: ${fraccion}"
        return fraccion ? Calculo.instance.redondear( (100 - (fraccion/24*100)), 2): 0
    }
    
    void actualizar() throws ValidationException{
        try{

            this.granel       = sumGranel
            this.k5           = sumK5
            this.k2           = sumK2
            this.k1           = sumK1
            this.g500         = sumG500
            this.g250         = sumG250
            this.arroba       = sumArroba
            this.g5002        = calG500
            this.g2502        = calG250
            this.arroba2      = calArroba
            this.qqTotalesDia = calQqTotalesDia
            getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    def getValor(def campo, def col){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle1", campo)
        return col == 1 ? (d.valor?:0): (d.cantidad?:0)
    }
    
    def getValorBlc(def campo, def col){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle1", campo)
        return col==1 ? (d.valor?:0): ( col==2 ? (d.cantidad?:0) : (d.acumulado?:0) )
    }
    
    void setD12(String campo, def uni, def acu, def zaf){
        def d = SqlUtil.instance.getDetallePorDTI(diaTrabajo.id, "blc", "BlcDetalle12", campo)
        d.setUnidades(uni)
        d.setAcumulado(acu)
        d.setTotalZafra(zaf)
        getManager().persist(d)
    }

    void setD14(String campo, def ar, def at, def st){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle14", campo)
        d.setAzucarReductor(ar)
        d.setAzucaresTotRed(at)
        d.setSacarosaTeoRed(st)
        getManager().persist(d)
    }

    void setD15(String campo, def cc, def ph, def ct1, def ct2){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle15", campo)
        d.setCenizasCon(cc)
        d.setPh(ph)
        d.setColorTur1(ct1)
        d.setColorTur2(ct2)
        getManager().persist(d)
    }

    void setD16(String campo, def br, def sa, def pu){
        def d = SqlUtil.instance.getDetallePorDTM(diaTrabajo.id, "blc", "BlcDetalle16", campo)
        d.setBrix(br)
        d.setSacarosa(sa)
        d.setPureza(pu)
        getManager().persist(d)
    }
}
