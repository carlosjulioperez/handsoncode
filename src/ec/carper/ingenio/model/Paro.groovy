package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*;

@Entity
@Tab(properties="""diaTrabajo.fecha,totalParada""")
@View(members="""
    diaTrabajo;
    titParFab { detalle;total }
""")
class Paro extends Formulario {

    @OneToMany (mappedBy="paro", cascade=CascadeType.ALL) @XOrderBy("fechaInicio") 
    @ListProperties(""" fechaInicio,fechaFin,area.descripcion,descripcion,totalParo[paro.sumaParo] """)
    Collection<ParoDetalle> detalle
    
    @ElementCollection @ReadOnly
    @ListProperties(""" area.descripcion,totalParo """)
    Collection<ParoTotal> total 
    
    String totalParada
    
    void actualizar() throws ValidationException{
        try{
            this.totalParada = sumaParo
            getManager().persist(this)
        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    private void agregarTotal(def areaId, def totalArea){
        def d    = Util.instance.getDuration(totalArea)
        def v    = Util.instance.toTimeString(d)

        def area = new Area()
        area.id  = areaId
        def p    = new ParoTotal(area: area, totalParo: v)
        
        this.total.add(p)
    }

    String getSumaParo(){
        this.total = new ArrayList()
        def totalGeneral = []
        def totalArea = []
        int i=0
        
        Query query = getManager().createQuery("from ParoDetalle o where paro.id= :id order by area.id")
        query.setParameter("id", this.id) 
        
        def lista = query.resultList
        //println ">>> lista: ${lista}"
        lista.each{
            totalArea << it.totalParo 

            if ( i < lista.size()-1 ){
                if ( it.area.id != lista[i+1].area.id ){
                    agregarTotal(it.area.id, totalArea)                    
                    totalArea = []
                }
            }else
                agregarTotal(it.area.id, totalArea) //Ultimo elemento

            i++ 
            
            totalGeneral << it.totalParo 
        }
        def duration = Util.instance.getDuration(totalGeneral)
        def valor = Util.instance.toTimeString(duration)

        // detalle.each { 
        //     //println ">>>area id: ${it.area.id}"
        //     totalGeneral << it.totalParo 
        // }
        // //prinln ">>>>>>>>>>>>>>> " + totalGeneral.size()
        // def duration = Util.instance.getDuration(totalGeneral)
        // def valor = Util.instance.toTimeString(duration)
        // //println ">>>valor: ${valor}"

        return valor
    }

}
