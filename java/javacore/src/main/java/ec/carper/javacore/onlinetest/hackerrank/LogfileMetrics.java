package ec.carper.javacore.onlinetest.hackerrank;

import java.util.HashSet;

public class LogfileMetrics {
    public static void main(String[] args) {
        // Ejemplo de entrada
        String input = "5\n"
                     + "2023-08-26T12:00:00 event1\n"
                     + "2023-08-26T12:15:00 event2\n"
                     + "2023-08-26T12:30:00 event3\n"
                     + "2023-08-26T13:00:00 event1\n"
                     + "2023-08-26T13:30:00 event4\n"
                     + "2\n"
                     + "2023-08-26T12:00:00 2023-08-26T12:30:00\n"
                     + "2023-08-26T13:00:00 2023-08-26T13:30:00";
        
        String[] inputLines = input.split("\n");

        // Leer el número de registros
        int n = Integer.parseInt(inputLines[0].trim());
        
        // Crear un arreglo para almacenar los registros
        String[] logs = new String[n];
        for (int i = 0; i < n; i++) {
            logs[i] = inputLines[i + 1].trim();
        }
        
        // Leer el número de consultas
        int q = Integer.parseInt(inputLines[n + 1].trim());
        
        // Procesar cada consulta
        for (int i = 0; i < q; i++) {
            String[] queryParts = inputLines[n + 2 + i].trim().split(" ");
            String startTime = queryParts[0];
            String endTime = queryParts[1];
            
            // Utilizar un HashSet para contar eventos únicos en el intervalo
            HashSet<String> uniqueEvents = new HashSet<>();
            
            for (String logEntry : logs) {
                String[] logParts = logEntry.split(" ", 2);
                String timestamp = logParts[0];
                String event = logParts[1];
                
                // Verificar si el timestamp está en el intervalo
                if (timestamp.compareTo(startTime) >= 0 && timestamp.compareTo(endTime) <= 0) {
                    uniqueEvents.add(event);
                }
            }
            
            // Imprimir el número de eventos únicos en el intervalo
            System.out.println(uniqueEvents.size());
        }
    }
}
