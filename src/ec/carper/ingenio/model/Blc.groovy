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
        consultaParo
    }
    titVarPri {
        cana { 
            titMetLabCan { detalle21 }
            titMetBal { detalle22 }
        }
        bagazo { detalle3 }
        mielFinaMelaza { detalle4 }
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

    @ElementCollection @ReadOnly @Transient
    @ListProperties(""" 
        area.descripcion,
        totalParo [ blc.tiempoPerdidoTotal, blc.tiempoMoliendaReal, blc.fraccionTiempo, blc.rataMolienda, blc.porcTot ] """)
    Collection<ParoTotal> consultaParo 
    
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

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle12> detalle12

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL) @XOrderBy("orden") @ReadOnly
    Collection<BlcDetalle13> detalle13
    
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

            // TODO
            // CalculoFabrica 
            lista = getManager().createQuery("FROM BlcPDetalle12 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new BlcDetalle102(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad)
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

        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultarParoTotal(){
        consultaParo = new ArrayList<ParoTotal>();
        def lista = getManager().createQuery("FROM Paro where diaTrabajo.id = :id")
                                .setParameter("id", diaTrabajo.id).resultList
        lista.each{
            it.total.each{
                consultaParo.add( new ParoTotal (area: it.area, totalParo: it.totalParo) )
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
                    )
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
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcBri")
                    break
                case "sacJRes":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcSac")
                    break
                case "pzaJRes":
                    it.valor = SqlUtil.instance.getValorCampo(diaTrabajo.id, "Jugo", "jcPur")
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

    // Si un total es de tipo String, todos los demás también deben serlo.
    String getTiempoPerdidoTotal(){
        return diaTrabajo ? SqlUtil.instance.getCampo(diaTrabajo.id, "Paro" , "totalParada"): "00:00:00"
    }

    String getTiempoMoliendaReal(){
        return Util.instance.getDiferenciaHoras(tiempoPerdidoTotal, "24:00:00")
    }
    
    String getFraccionTiempo(){
        return Calculo.instance.getFraccionTiempo(tiempoMoliendaReal)
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
}
