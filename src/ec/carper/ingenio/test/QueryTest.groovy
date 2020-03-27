package ec.carper.ingenio.test
 
import javax.persistence.Query;

import ec.carper.ingenio.model.DiaTrabajo
import org.apache.commons.logging.*
import org.openxava.tests.*
import static org.openxava.jpa.XPersistence.*;

class QueryTest extends ModuleTestBase {

    private static Log log = LogFactory.getLog(QueryTest.class)
 
    QueryTest(String testName) {
        super(testName, "Ingenio", "DiaTrabajo")
    }

    void test(){
        getTrashCanaDiaTrabajoCerrado()
        //getNativo()
    }

    void getTrashCanaDiaTrabajoCerrado(){
        Query query = getManager().createQuery("select diaTrabajo.cerrado from TrashCana o where id= :id ")
        query.setParameter("id", "ff808081711cd37c01711cd45a1f0001")
        def cerrado = (boolean) query.getSingleResult()
        log.warn (cerrado)
    }
    
    /*
    void getNativo(){
        Query q = em.createNativeQuery("SELECT o.diaTrabajo.cerrado FROM TrashCana o WHERE o.id= :id ")
        q.setParameter("id", "ff808081711cd37c01711cd45a1f0001")
        Object[] arreglo = (Object[]) q.getSingleResult();
        // System.out.println("Author "
        //         + arreglo[0]
        //         + " "
        //         + arreglo[1]);
    }
    */
}
