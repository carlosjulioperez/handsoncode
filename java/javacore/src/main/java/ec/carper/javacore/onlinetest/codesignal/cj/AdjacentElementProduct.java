package ec.carper.javacore.onlinetest.codesignal.cj;

import lombok.val;

public class AdjacentElementProduct {
    public static void main(String[] args) {
        //System.out.println( solution(new int[]{ 3, 6, -2, -5, 7, 3 }) );
        System.out.println( solution(new int[]{ -23, 4, -3, 8, -12 }) );
    }

    static int solution(int[] inputArray) {
        int value = 0;
        for (int i=0; i<inputArray.length-1; i++){
            int product = inputArray[i] * inputArray[i+1];
            if (i==0) value = product;
            if (product > value)
                value = product;
        }
        return value;
    }
    
}
