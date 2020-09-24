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
    titDetalle { detalle1 }
    titTotales { detalle2 }
""")
class StockProceso extends Formulario {

    boolean itemsCargados
    
    // Usado para pruebas solamente
    @Column(length=10)
    String descripcion 

    BigDecimal tonBrix
    BigDecimal tonSac
    BigDecimal pureza 

    @OneToMany (mappedBy="stockProceso", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    @ListProperties("""
        orden,material.descripcion,temp,volumen1,volumen2,peso,porcBrix,eq,
        tonBrix [stockProceso.sumTonBrix],
        porcSac,
        tonSac [stockProceso.sumTonSac],
        pureza [stockProceso.calcPureza],
        densidad,factor
    """)
    Collection<StockProcesoDetalle1> detalle1
    
    @OneToMany (mappedBy="stockProceso", cascade=CascadeType.ALL) @XOrderBy("orden") @EditOnly
    Collection<StockProcesoDetalle2> detalle2
    
    BigDecimal getSumTonBrix() {
        // println ">>>id: ${this.id}"
        def d = SqlUtil.instance.getDetallePorIndicador(this.id, "StockFabricaDetalle73", "stockFabrica.id", "tonAzuDis")
        def bg142 = d ? d.valor?:0 : 0
        def sumaColumna = super.getSuma(detalle1, "tonBrix")
        return (sumaColumna - bg142)
    }
    
    BigDecimal getSumTonSac() {
        // println ">>>id: ${this.id}"
        def d = SqlUtil.instance.getDetallePorIndicador(this.id, "StockFabricaDetalle73", "stockFabrica.id", "tonAzuDis")
        def bg142 = d ? d.valor?:0 : 0
        def sumaColumna = super.getSuma(detalle1, "tonSac")
        return (sumaColumna - bg142)
    }
    
    BigDecimal getCalcPureza() {
        return sumTonBrix ? Calculo.instance.redondear(sumTonSac/sumTonBrix*100, 2): 0
    }

    void cargarItems() throws ValidationException{
        try{
            this.itemsCargados = true
            getManager().persist(this)
            cargarDetalles(this)
        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }

    void cargarDetalles(StockProceso stockProceso){
        try{
            def lista = getManager().createQuery("FROM StockProcesoPDetalle WHERE stockProcesoP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new StockProcesoDetalle1(stockProceso: stockProceso, orden: it.orden, material: it.material)
                getManager().persist(d)
            }

            lista = getManager().createQuery("FROM StockProcesoPDetalle2 WHERE stockProcesoP.id = 1 ORDER BY orden").getResultList()
            lista.each{
                def d = new StockProcesoDetalle2(stockProceso: stockProceso, orden: it.orden, material: it.material)
                getManager().persist(d)
            }

        }catch(Exception ex){
            throw new SystemException("items_no_cargados", ex)
        }
    }
    
    void actualizar() throws ValidationException{
        try{

            this.tonBrix = sumTonBrix
            this.tonSac  = sumTonSac
            this.pureza  = calcPureza

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }


}
