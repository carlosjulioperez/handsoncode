package ec.carper.javacore.streams;

import ec.carper.javacore.streams.interfaces.Vehicle;
import ec.carper.javacore.streams.interfaces.VehicleImpl;

public class Code {

    public static void main(String[] args) {
        // https://www.baeldung.com/java-8-new-features

        String producer = Vehicle.producer();
        System.out.println(producer);

        Vehicle vehicle = new VehicleImpl();
        String overview = vehicle.getOverview();
        System.out.println(overview);

    }
}
