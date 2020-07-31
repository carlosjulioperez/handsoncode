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
    @EditOnly
    Collection<BlcDetalle1>detalle1
    
    @ElementCollection @ReadOnly @Transient
    @ListProperties(""" area.descripcion,totalParo[blc.totalParada] """)
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

    String getTotalParada(){
        return SqlUtil.instance.getCampo(diaTrabajo.id, "Paro" , "totalParada")
    }

}
