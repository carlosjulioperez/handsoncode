package ec.carper.javacore.onlinetest.codesignal.cj;

import java.util.ArrayList;
import java.util.List;

public class DigitosNoRepetidos {
    public static void main(String[] args) {
        int desde = 879; //870;
        int hasta = 902; //893;

        int arrelo[] = solucion2(desde, hasta);
        for (int i=0; i<arrelo.length; i++){
            System.out.print(arrelo[i]+ " ");
        }

        System.out.println("\n");
        List<Integer> numeros = solucion(desde, hasta);
        for (Integer numero: numeros){
            System.out.print(numero + " ");
        }
    }

    private static int[] solucion2(int desde, int hasta) {
        List<Integer> lista = new ArrayList();
        for (int i = desde; i <= hasta; i++) {
            if (digitoUnico2(i))
                lista.add(i);
        }
        return lista.stream().mapToInt(Integer::intValue).toArray();
    }
    private static boolean digitoUnico2(int numero){
        String num = String.valueOf(numero);
        return num.chars()
                .distinct() //Quitar duplicados
                .count() == num.length();
    }

    private static List<Integer> solucion(int desde, int hasta) {
        List<Integer> lista = new ArrayList();
        for (int i = desde; i <= hasta; i++) {
           if (digitoUnico(i))
               lista.add(i);
        }
        return lista;
    }
    private static boolean digitoUnico(int numero) {
        boolean digitos[] = new boolean[10];
        while (numero > 0) {
            int digito = numero % 10;
            if (digitos[digito])
                return false;

            digitos[digito] = true;
            numero /= 10;
        }
        return true;
    }
}
