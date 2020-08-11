package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.*

import javax.persistence.*
import org.openxava.annotations.*
//import org.openxava.calculators.*
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
        paroTotal
    }
    titVarPri {
        cana { 
            titMetLabCan { detalle21 }
            titMetBal { detalle22 }
        }
        bagazo { detalle3 }
        mielFinaMelaza { detalle4 }
        jugoDiluido { detalle5 }
    }
""")
class Blc extends Formulario {
    
    @OnChange(BlcShowHideCargarItemsAction.class)
    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

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
    Collection<ParoTotal> paroTotal 
    
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
                def d = new BlcDetalle1(blc: blc, orden: it.orden, material: it.material, unidad: it.unidad, unidad2: it.unidad2)
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
        }catch(Exception ex){
            throw new SystemException("datos_no_consultados", ex)
        }
    }
    
    def consultarParoTotal(){
        paroTotal = new ArrayList<ParoTotal>();
        def lista = getManager().createQuery("FROM Paro where diaTrabajo.id = :id")
                                .setParameter("id", diaTrabajo.id).resultList
        lista.each{
            it.total.each{
                paroTotal.add( new ParoTotal (area: it.area, totalParo: it.totalParo) )
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
                    // def sac  = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle21" , "sacCanaDac")
                    // def brix = SqlUtil.instance.getValorCampoBlc(diaTrabajo.id, "BlcDetalle21" , "brixCanaDac")
                    // it.valor = brix ? Calculo.instance.redondear(sac/brix*100,2): 0
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
            getManager().persist(it)
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
            }
            getManager().persist(it)
        }
    }
    
    def consultarJugoDiluido(){
        detalle4.each{
            def campo = it.material.campo ?: ""
            switch (campo){
                case "rhoJugDil":
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

            // this.fldTiempoPerdidoTotal = tiempoPerdidoTotal

            getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
}
