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
}
