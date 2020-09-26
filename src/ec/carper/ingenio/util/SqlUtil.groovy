package ec.carper.ingenio.util

import ec.carper.ingenio.model.*

import javax.persistence.*
import java.time.format.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Singleton
class SqlUtil{

    BigDecimal getValorCampo(String diaTrabajoId, String modulo, String campo){
        Query query = getManager().createQuery("SELECT ${campo} FROM ${modulo} WHERE diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }
    
    BigDecimal getValorCampoBlc(String diaTrabajoId, String detalle, String campo){
        Query query = getManager().createQuery("""
            SELECT valor FROM ${detalle}
            WHERE blc.diaTrabajo.id = :diaTrabajoId
            AND material.campo = '${campo}'
        """)
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }

    BigDecimal getValorDetalleCampo(String diaTrabajoId, String maestro, String detalle, String campo){
        Query query = getManager().createQuery("""
            SELECT ${campo} FROM ${detalle}
            WHERE ${maestro}.diaTrabajo.id = :diaTrabajoId
        """)
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }
    
    String getCampo(String diaTrabajoId, String modulo, String campo){
        Query query = getManager().createQuery("SELECT ${campo} FROM ${modulo} WHERE diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: ""
    }

    String getCampoPorId(String id, String modulo, String campo){
        return (String)getManager()
            .createQuery("SELECT ${campo} FROM ${modulo} WHERE id= :id")
            .setParameter("id", id).singleResult
    }

    def getRegistros(String padreId, String modulo, String campoFk){
        return getManager()
            .createQuery("FROM ${modulo} where ${campoFk} = :id")
            .setParameter("id", padreId).resultList
    }
    
    def getDetallePorIndicador(def padreId, def modulo, def campoFk, def indicador){
        return getManager()
                .createQuery("FROM ${modulo} WHERE ${campoFk} = :id AND indicador.campo = :indicador")
                .setParameter("id", padreId)
                .setParameter("indicador", indicador).resultList[0]
    }

    def getDetValorPorDTI(def diaTrabajoId, String objMaestro, String detalle, def indicador){
        return getManager() .createQuery("""
            SELECT valor FROM ${detalle} 
            WHERE ${objMaestro}.diaTrabajo.id = :diaTrabajoId
            AND indicador.campo = :indicador
        """)
        .setParameter("diaTrabajoId", diaTrabajoId)
        .setParameter("indicador", indicador).resultList[0]?:0
    }

    def getDetallePorDTM(def diaTrabajoId, String objMaestro, String detalle, def material){
        return getManager() .createQuery("""
            FROM ${detalle} 
            WHERE ${objMaestro}.diaTrabajo.id = :diaTrabajoId
            AND material.campo = :material
        """)
        .setParameter("diaTrabajoId", diaTrabajoId)
        .setParameter("material", material).resultList[0]
    }

    def getDiaTrabajo(String diaTrabajoId){
        return getManager().find(DiaTrabajo.class, diaTrabajoId)
    }
    
    def getIndicador(String indicadorId){
        return getManager().find(Indicador.class, indicadorId)
    }
    
    def getMaterial(String materialId){
        return getManager().find(Material.class, materialId)
    }
    
    def obtenerFecha(def hora, def diaTrabajoId){
        def h1 = (hora.split(":")[0] + hora.split(":")[1]) as int
        
        def d  = getDiaTrabajo(diaTrabajoId)
        def hora2 = d.turnoTrabajo.horaHasta
        def fecha = d.fecha
        def h2 = (hora2.split(":")[0] + hora2.split(":")[1]) as int

        if (h1 >=0 && h1 <=h2)
            fecha = fecha.plusDays(1);

        def strFecha = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 
        return Util.instance.toTimestamp(strFecha+" "+hora+":00")
    }
    
    BigDecimal getValorDetalleCampoXHora(String diaTrabajoId, java.sql.Timestamp hora, String maestro, String detalle, String campo){
        Query query = getManager().createQuery("""
            SELECT ${campo} FROM ${detalle}
            WHERE ${maestro}.diaTrabajo.id = :diaTrabajoId
            AND hora = :hora ORDER BY hora 
        """)
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        return query.resultList[0]?: 0
    }
                
    boolean isCerrado(def modulo, def id){
        return (boolean) getManager()
            .createQuery("""
                SELECT d.cerrado 
                FROM ${modulo} o, DiaTrabajo d
                WHERE o.id = :id AND o.diaTrabajo.id = d.id
            """).setParameter("id", id).getSingleResult()
    }

}
