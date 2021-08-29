package ec.carper.javacore.code;

public class Cadena {
 
    final static int TAMANO = 256;
 
    static int maximo(String str, int n) {
 
        int arreglo[] = new int[TAMANO];
        for (int i = 0; i < n; i++) 
            arreglo[str.charAt(i)]++;
 
        int max = 0;
        for (int i = 0; i < TAMANO; i++) {
            if (arreglo[i] != 0)
                max++;
        }
 
        return max;
    }
 
    static int corto(String cadena) {
 
        int n = cadena.length();
 
        int maximosDistintos = maximo(cadena, n);
        int minimo = n;
 
        // Verificar cada subcadena
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                 
                String subCadena = null;
                if(i<j)
                    subCadena = cadena.substring(i, j);
                else
                    subCadena = cadena.substring(j, i);
                int subCadenaLon = subCadena.length();
                int max = maximo(subCadena, subCadenaLon);
 
                if (subCadenaLon < minimo && maximosDistintos == max)
                    minimo = subCadenaLon;
            }
        }
        return minimo;
    }
 
    static public void main(String[] args) {
        //String str = "gamer programming"; //13
        String str = "I just waNt to finiSh These exercises sOon Because I want To wAtch soMe interestinG conFerences";
 
        System.out.println(corto(str));
    }
}