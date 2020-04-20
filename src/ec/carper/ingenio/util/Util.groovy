package ec.carper.ingenio.util

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

}
