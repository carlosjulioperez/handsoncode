package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Entity
class Parametro extends Identifiable{

    @Column(length=30) @Required
    String nombre

    @Column(length=30) @Required
    String valor
    
    String obtenerValor (String nombre){
        Query query = getManager().createQuery("SELECT valor FROM Parametro WHERE nombre = :nombre")
        query.setParameter("nombre", nombre)
        return query.resultList[0]?: 0
    }

}
