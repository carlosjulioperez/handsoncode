package ec.carper.javacore.onlinetest.codesignal.cj;

import java.util.ArrayList;
import java.util.List;

public class Pruebas {
    public static void main(String[] args) {
        String cadena = "Penelope";
        System.out.println();
    }

    String[] solution(String text) {
        List<String> value = new ArrayList();

        for (String aWord : text.split(" ")) {
            System.out.println(aWord);
            int vowels = 0;
            int conson = 0;
            for (int i = 0; i < aWord.length(); i++) {
                char c = aWord.charAt(i);
                int x = (int) c;
                //if (x == 97 || x == 101 || x == 105 || x == 111 || x == 117)
                if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u')
                    vowels++;
                else
                    conson++;
            }
            int diff = Math.abs(conson - vowels);
            value.add(aWord);
        }
        return value.toArray(new String[0]);
    }

}
