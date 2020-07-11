package ec.carper.ingenio.test
 
import java.util.List

import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.model.TrashCanaDetalle2
import ec.carper.ingenio.model.DiaTrabajo

import ec.carper.ingenio.util.Util
import org.apache.commons.logging.*
import org.openxava.tests.*

class TrashCanaDetalle2Test extends ModuleTestBase {

    private static Log log = LogFactory.getLog(TrashCanaDetalle2Test.class)
 
    TrashCanaDetalle2Test(String testName) {
        super(testName, "Ingenio", "TrashCanaDetalle2")
    }
 
    void testGetPorcAzuRed(){
        // Query query = getManager().createQuery("SELECT o.calPorcAzuRed FROM TrashCanaDetalle2 o WHERE o.trashCana.diaTrabajo.id = :diaTrabajoId AND o.hora <= :hora ORDER BY o.hora DESC")
        // query.setParameter("diaTrabajoId", diaTrabajoId)
        // query.setParameter("hora", hora)
        // List records = query.getResultList()
        // valor = records ? records[0]: 0
        // return valor

        // DiaTrabajo diaTrabajo = XPersistence.getManager().find( DiaTrabajo.class, "ff808081711cd37c01711cd403a70000" )
        // jpql obtain hours difference two timestamp
        // https://stackoverflow.com/questions/10578674/find-hour-or-minute-difference-between-2-java-sql-timestamps
        // https://stackoverflow.com/questions/34954890/jpql-calculate-difference-between-timestamps
        // https://stackoverflow.com/questions/36462861/jpa-query-compare-timestamp-with-offset-to-system-time
        // https://stackoverflow.com/questions/10578674/find-hour-or-minute-difference-between-2-java-sql-timestamps

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        String diaTrabajoId = Aux.instance.diaTrabajoId
        
        def horas = [
            "2019-08-03 06:00:00", 
            "2019-08-03 07:00:00", 
            "2019-08-03 11:00:00", 
            "2019-08-03 17:00:00", 
            "2019-08-03 18:00:00", 
            "2019-08-03 19:00:00", 
            "2019-08-03 20:00:00", 
            "2019-08-03 21:00:00", 
            "2019-08-03 22:00:00", 
            "2019-08-03 23:00:00", 
            "2019-08-04 00:00:00", 
            "2019-08-04 01:00:00", 
            "2019-08-04 02:00:00", 
            "2019-08-04 03:00:00", 
            "2019-08-04 04:00:00", 
            "2019-08-04 05:00:00" 
        ]

        horas.each{
            def valor = (String)new TrashCanaDetalle2().getPorcAzuRed(diaTrabajoId, Util.instance.toTimestamp(it))
            //log.warn(valor)
            println it + ", " + valor //+ "\n"
        }
        //assertEquals(valor, "0.500")
    }

    
}
