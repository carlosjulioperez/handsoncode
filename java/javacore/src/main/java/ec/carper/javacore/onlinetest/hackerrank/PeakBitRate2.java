package ec.carper.javacore.onlinetest.hackerrank;

public class PeakBitRate2 {
    public static void main(String[] args) {

        String bitSequence = "1011110111011";
        
        // Entrada: "1011110111011"
        // Salida: 4

        int max = 0; // Longitud máxima de '1's consecutivos
        int curr = 0; // Longitud actual de '1's consecutivos
        
        // Recorrer la secuencia de bits
        for (int i = 0; i < bitSequence.length(); i++) {
            char bit = bitSequence.charAt(i);
            
            if (bit == '1') {
                curr++;
                // Actualizar la longitud máxima si es necesario
                if (curr > max)
                    max = curr;
            } else {
                curr = 0;
            }
        }
        
        // Imprimir la longitud máxima de '1's consecutivos
        System.out.println(max);
    }
}
