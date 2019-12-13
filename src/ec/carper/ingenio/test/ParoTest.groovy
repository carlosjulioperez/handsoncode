package ec.carper.ingenio.test
 
import java.util.List

import ec.carper.ingenio.model.Paro
import ec.carper.ingenio.util.Util
import org.apache.commons.logging.*
import org.openxava.tests.*

import java.text.SimpleDateFormat;
import java.util.Date;

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
        sumaTiempos()
    }

    void sumaTiempos(){
        def timestampsList = [ "01:40:01", "11:00:05", "10:24:03" ]
        long duration = 0
        for (durationp in timestampsList){
            def arr = durationp.split(":")
            duration += Integer.parseInt(arr[2])
            duration += 60 * Integer.parseInt(arr[1])
            duration += 3600 * Integer.parseInt(arr[0])
        }
        log.warn ("============================================================")
        log.warn ( Util.instance.toTimeString(duration) )
    }

    // https://www.programcreek.com/2014/01/how-to-calculate-time-difference-in-java/
    // private void elapsed(){
    //     String time1 = "23:59:01"
    //     String time2 = "00:06:35"
    //
    //     SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss")
    //     Date date1 = format.parse(time1)
    //     Date date2 = format.parse(time2)
    //     long difference = Math.abs(date2.getTime() - date1.getTime())
    //     log.warn ("============================================================")
    //     log.warn(difference)
    //     log.warn ( toTimeString(difference) )
    //     
    //     log.warn ("============================================================")
    //     difference /= 1000
    //     log.warn ( toTimeString(difference) )
    //
    // }


}
