package ec.carper.javacore.streams.interfaces;

public interface Vehicle{
    static String producer(){
        return "N&F Vehicles";
    }

    default String getOverview() {
        return "ATV made by " + producer();
    }
}