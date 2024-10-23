package ec.carper.javacore.onlinetest.codesignal.cj;

import java.util.ArrayList;
import java.util.List;

public class AllLongestStrings {
    public static void main(String[] args) {
        String array[] = solution(new String[]{"aba", "aa", "ad", "vcd", "aba"}) ; 
        for (String str: array){
            System.out.println(str);
        }
    }

    static String[] solution(String[] inputArray) {
        List<String> value = new ArrayList<String>();
        int max = 0;
        for (String str: inputArray){
            if (str.length() > max)
                max = str.length();
        }

        for (String str: inputArray){
            if (str.length() == max)
                value.add(str);
        }
        return value.toArray(new String[0]);
    }

}
