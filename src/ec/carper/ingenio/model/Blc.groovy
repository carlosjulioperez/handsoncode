package ec.carper.ingenio.model

import ec.carper.ingenio.calculators.*
import ec.carper.ingenio.util.*

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    diaTrabajo.descripcion
""")
@View(members="""
    diaTrabajo;
    datosDia { detalle1 }
    tiempos { paroTotal }
""")
class Blc extends DiaTrabajoEditable {
    
    boolean itemsCargados

    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @XOrderBy("material.id") @EditOnly
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
            // datosDia
            def lista = getManager().createQuery("FROM BlcPDetalle1 WHERE blcP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d1      = new BlcDetalle1()
                d1.id       = null
                d1.blc      = blc
                d1.material = it.material
                d1.unidad   = it.unidad
                d1.unidad2  = it.unidad2
                getManager().persist(d1)
            }
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void consultarDatos() throws ValidationException{
        try{ 
            consultarParoTotal()
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
    }

    // Si un total es de tipo String, todos los demás también deben serlo.
    String getTiempoPerdidoTotal(){
        return diaTrabajo ? SqlUtil.instance.getCampo(diaTrabajo.id, "Paro" , "totalParada"): "00:00:00"
    }

    String getTiempoMoliendaReal(){
        return Util.instance.getDiferenciaHoras(tiempoPerdidoTotal, "24:00:00")
    }
    
    String getFraccionTiempo(){
        return Util.instance.getFraccionTiempo(tiempoMoliendaReal)
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
        println ">>> fraccion: ${fraccion}"
        return fraccion ? Calculo.instance.redondear( (100 - (fraccion/24*100)), 2): 0
    }
    
    void actualizar() throws ValidationException{
        try{

            this.fldTiempoPerdidoTotal = tiempoPerdidoTotal
            this.fldTiempoMoliendaReal = tiempoMoliendaReal
            this.fldFraccionTiempo     = new BigDecimal(fraccionTiempo)
            this.fldRataMolienda       = new BigDecimal(rataMolienda)
            this.fldPorcTot            = new BigDecimal(porcTot)

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
}
