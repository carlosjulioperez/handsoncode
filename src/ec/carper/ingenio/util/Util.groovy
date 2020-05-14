package ec.carper.ingenio.util

import ec.carper.ingenio.model.*

import java.sql.Timestamp
import java.text.SimpleDateFormat

@Singleton
class Util{
    private static String format(long s){
        if (s < 10) return "0" + s
        else return "" + s
    }

    String toTimeString(long duration){
        long hh = duration / 3600
        duration %= 3600
        long mm = duration / 60
        duration %= 60
        long ss = duration
        return format(hh) + ":" + format(mm) + ":" + format(ss)
    }

    long getDuration(ArrayList<String> timeList ){
        long duration = 0
        for (item in timeList){
            def arr = item.split(":")
            duration += Integer.parseInt(arr[2])
            duration += 60 * Integer.parseInt(arr[1])
            duration += 3600 * Integer.parseInt(arr[0])
        }
        return duration;
    }

    /**
        https://stackoverflow.com/questions/48932709/calculate-difference-between-2-date-time-in-s
        https://stackoverflow.com/questions/22848000/how-to-create-new-date-in-groovy-at-specific-date-and-time

    */
    String getDurationAsString(String startString, String endString){
        def timeFormat = "yyyy-MM-dd HH:mm:ss"
        def startDate  = Date.parse(timeFormat, startString)
        def endDate    = Date.parse(timeFormat, endString)
        def value      = ""

        use(groovy.time.TimeCategory) {
            def duration = endDate - startDate
                //println ( "Days: ${duration.days}, Hours: ${duration.hours}, Minutes: ${duration.minutes}, Seconds: ${duration.seconds}, etc.")
            value = String.format("%02d", duration.hours) + ":" +
                    String.format("%02d", duration.minutes) + ":" + 
                    String.format("%02d", duration.seconds)
        }
        return value
    }
    
    String getDurationAsString(long startTime, long endTime){
        def startDate  = new Date ( startTime )
        def endDate    = new Date ( endTime )
        def value      = ""

        use(groovy.time.TimeCategory) {
            def duration = endDate - startDate
                //println ( "Days: ${duration.days}, Hours: ${duration.hours}, Minutes: ${duration.minutes}, Seconds: ${duration.seconds}, etc.")
            value = String.format("%02d", duration.hours) + ":" +
                    String.format("%02d", duration.minutes) + ":" + 
                    String.format("%02d", duration.seconds)
        }
        return value
    }
   
    // BigDecimal getSusRed (BigDecimal titulacion){
    //     return new BrixDensidadTitSus().getSusRed(titulacion)
    // }
    
    BigDecimal getPromedio(def detalle, String propiedad, int escala){
        def lista = []
        detalle.each {
            def valor = (BigDecimal)Eval.x(it, "x."+propiedad)
            // println ">>>>>>>>>>> " + valor
            if (valor >= 0) lista << valor
        }
        return lista.size()>0 ? ( lista.sum() / lista.size() ).setScale(escala, BigDecimal.ROUND_HALF_UP) : 0
    }
    
    // https://stackoverflow.com/questions/12102280/groovy-java-sql-timestamp-tostring-to-java-sql-timestamp
    def toTimestamp(String hora) throws Exception{

        // Este código java también funciona, pero el de abajo es más corto
        // def dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        // Date parsedDate = dateFormat.parse(hora);
        // return new Timestamp(parsedDate.getTime());

        //def date = Date.parse('yyyy-MM-dd HH:mm:ss.S', hora)
        def date = Date.parse('yyyy-MM-dd HH:mm:ss', hora)
        return new java.sql.Timestamp(date.time)
    }

}
