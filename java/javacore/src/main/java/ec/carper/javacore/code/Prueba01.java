package ec.carper.javacore.code;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Prueba01 {
   
	public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        // String glosaEvento[] = new String [] {
        //     "001019APRLDCEXCCOL   %E04%LINEA DE CREDITO EXCEDIDA                     ",
        //     " EL SUBDETALLE N  003DEL MARGEN GLOBAL: L09000755246                    ",
        //     "  DEL TIPO LINEA BOLETA GARANTIAS                   ( LBG)              ",
        //     "   CON UN MONTO UTILIZADO DE:       360.085.200,00                      ",
        //     "    Y CONDICION XXXXXXXXXXXXXXXXXX ESTA SOBREGIRADA POR FALTA DE GARANTI",
        //     "AS   EXIGIDAS POR EL SUBDETALLE                                         ",
        //     "      LAS CONDICIONES DE GARANTIA DEL  SUBDETALLE SON:                 " };
        
        String glosaEvento[] = new String [] {"MENSAJE DE PRUEBA", null, "DOS"};
        
        List <String> glosaEventoApr = Arrays.asList(glosaEvento);
        int tamanoCadena = 250;

        //String detalleApr = glosaEventoApr.stream().map(String::trim).collect(Collectors.joining(" "));
        String detalleApr = glosaEventoApr.stream().filter(Objects::nonNull).map(String::trim).collect(Collectors.joining(" "));

        // https://www.techiedelight.com/split-string-into-fixed-length-chunks-java/ 
        String[] cadena = detalleApr.split("(?<=\\G.{" + tamanoCadena + "})");
        System.out.println("\n\n"+ Arrays.toString(cadena));
        List <String> detalles = Arrays.asList(cadena);
        for (String detalle: detalles){
            System.out.println(detalle);
        }


        // for (String linea: glosaLista){
        //     String item = linea.trim();
        //     System.out.println(item.length());
            
        //     if (item.length() > tamanoCadena){
        //         System.out.println("Texto excedido");
        //     } else {
        //         System.out.println("Texto normal");
        //     }
        // }
    }
}
