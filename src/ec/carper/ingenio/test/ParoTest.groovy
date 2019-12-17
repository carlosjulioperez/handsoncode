package ec.carper.ingenio.test
 
import java.util.List
import javax.persistence.Query;

import ec.carper.ingenio.model.Paro
import ec.carper.ingenio.model.ParoDetalle
import ec.carper.ingenio.util.Util
import org.apache.commons.logging.*
import org.openxava.tests.*

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.openxava.jpa.XPersistence.*;

class ParoTest extends ModuleTestBase {

    private static Log log = LogFactory.getLog(ParoTest.class)
 
    ParoTest(String testName) {
        super(testName, "Ingenio", "Paro")
    }
    
    /**
        Imprimir duration en java puro:
        https://stackoverflow.com/questions/38054179/how-do-i-convert-a-jodatime-duration-into-a-string-formatted-as-hhmmss
    */

    // https://stackoverflow.com/questions/29548146/joda-time-sum-various-times
    // https://www.mkyong.com/java/java-time-elapsed-in-days-hours-minutes-seconds/

    /**
    * Sumar: 
    * https://stackoverflow.com/questions/22230487/how-to-sum-times-in-java
    * Modulus:
    * https://www.tecmint.com/arithmetic-in-linux-terminal/
    */
    void test(){
        //sumaTiempos()
        getConsultaClaseIncrustada()
    }

    void sumaTiempos(){
        def timeList = [ "01:40:01", "11:00:05", "10:24:03" ]
        def duration = Util.instance.getDuration(timeList)
        // long duration = 0
        // for (item in timeList){
        //     def arr = item.split(":")
        //     duration += Integer.parseInt(arr[2])
        //     duration += 60 * Integer.parseInt(arr[1])
        //     duration += 3600 * Integer.parseInt(arr[0])
        // }
        log.warn ("============================================================")
        //log.warn ( Util.instance.toTimeString(duration) )
        log.warn ( Util.instance.toTimeString(duration) )
    }

    String getConsultaClaseIncrustada(){
        Query query = getManager().
            createQuery("from Paro o where o.id= :id ")
        query.setParameter("id", "ff8080816f0e8b24016f0e8ba71e0000")
        
        def paro = (Paro) query.getSingleResult()
        // https://stackoverflow.com/questions/55222742/how-to-declare-an-string-array-in-groovy
        def timeList = []
        paro.detalles.each {
            println ">>>>>>>>>>>>>>>>>>>>" + it.descripcion
        }
    }

    // google: groovy interval between times
    // https://stackoverflow.com/questions/48932709/calculate-difference-between-2-date-time-in-soapui
    void elapsedStrings(){
        //Assuming string teased out of response, if not you need to extract the value to a string....
        def startString = '<startTime>2018-02-22T17:10:00-05:00</startTime>';
        def endString     = '<endTime>2018-02-22T18:06:02-05:00</endTime>';

        // If you have the tags, ditch them.
        startString = startString.replace("<startTime>", "");
        startString = startString.replace("</startTime>", "");

        endString = endString.replace('<endTime>', '');
        endString = endString.replace('</endTime>', '');

        println ("Now just strings... ${startString} - ${endString}");

        // Convert strings to dates...
        def convertedStartDate = Date.parse("yyyy-MM-dd'T'HH:mm:ssX",startString);
        def convertedEndDate = Date.parse("yyyy-MM-dd'T'HH:mm:ssX",endString);

        println ("Now dates...  ${convertedStartDate} - ${convertedEndDate}");

        //Use time category to tease out the values of interest...
        use(groovy.time.TimeCategory) {
            def duration = convertedEndDate - convertedStartDate
                println ( "Days: ${duration.days}, Hours: ${duration.hours}, Minutes: ${duration.minutes}, Seconds: ${duration.seconds}, etc.")
        }
    }
}
