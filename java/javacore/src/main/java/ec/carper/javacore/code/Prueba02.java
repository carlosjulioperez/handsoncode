package ec.carper.javacore.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Prueba02 {
   
	public static void main(String[] args) {
        test2();
    }
    
    private static void test2() {
        List<DetalleHabilitadorDTO> detalleHabilitadores = new ArrayList<DetalleHabilitadorDTO>();
        detalleHabilitadores.add( new DetalleHabilitadorDTO (1, "detalle 1" ) );
        detalleHabilitadores.add( new DetalleHabilitadorDTO (2, "detalle 2" ) );
        detalleHabilitadores.add( new DetalleHabilitadorDTO (3, null ) );
        detalleHabilitadores.add( new DetalleHabilitadorDTO (4, "detalle 4" ) );
        detalleHabilitadores.add( new DetalleHabilitadorDTO (5, null ) );
        detalleHabilitadores.add( new DetalleHabilitadorDTO (6, "detalle 6" ) );

        String detalleApr = detalleHabilitadores.stream()
        .filter(d->d.getDetalle()!=null)
        .map(DetalleHabilitadorDTO::getDetalle)
        .collect(Collectors.joining(" "));
        System.out.println(detalleApr);

    }
}
