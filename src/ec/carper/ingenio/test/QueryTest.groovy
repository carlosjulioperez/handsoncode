package ec.carper.ingenio.test
 
import javax.persistence.Query;

import ec.carper.ingenio.model.*
import org.apache.commons.logging.*
import org.openxava.tests.*
import static org.openxava.jpa.XPersistence.*;

class QueryTest extends ModuleTestBase {

/*
   Customer pedro = (Customer) XPersistence.getManager()
       .createQuery( "from Customer c where c.name = 'PEDRO')")
       .getSingleResult(); // Para obtener una única entidad (2)
   
   List pedros = XPersistence.getManager()
        .createQuery("from Customer c where c.name like 'PEDRO%')")
        .getResultList(); // Para obtener una colección de entidades (3)
*/
 
    private static Log log = LogFactory.getLog(QueryTest.class)

    QueryTest(String testName) {
        super(testName, "Ingenio", "DiaTrabajo")
    }

    void test() throws Exception {
        getColorMatDetalle()
        //getTrashCanaDiaTrabajoCerrado()
        //getTrashCanaDetalle2()
        //getNativo()
    }

    void getColorMatDetalle(){
        ColorMatDetalle o = (ColorMatDetalle) getManager()
            .createQuery("FROM ColorMatDetalle WHERE colorMat.id = :id ")
            .setParameter("id", "ff808081726e376301726e3947fe0000")
            .getSingleResult()

        println o.dump()
    }

    void getTrashCanaDetalle2(){
        Query query = getManager().createQuery("from TrashCanaDetalle2 where trashCana.id= :id ")
        query.setParameter("id", "ff808081716c39f201716c43535d0000")

        //println "*****" + query.getResultList().size()
        query.resultList.each{
            def o = (TrashCanaDetalle2)it
            println ("**************************************************")
            println (o.id)
            println (o.hora)
            println (o.mlReductores)
            println (o.valTab7SusRed)
            println (o.valPorcAzuRed )
        }
        /*
        // Java's way
        for (Object o: query.getResultList()){
            TrashCanaDetalle2 det = (TrashCanaDetalle2)o
            log.warn (">>>>>>" + det.hora)
//hora, mlReductores, valTab7SusRed, valPorcAzuRed f
        }
        */
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
