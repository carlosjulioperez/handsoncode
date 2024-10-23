package ec.carper.javacore.onlinetest.codesignal.cj;

public class ZigZag {
     public static void main(String[] args) {
        //long numbers[] = {1, 2, 1, 3, 4};
        test(new int[]{1, 2, 1, 3, 4}); // 1 1 0
        test(new int[]{1, 2, 3, 4}); // 0 0
        test(new int[]{1000000000, 1000000000, 1000000000}); // 0
        test(new int[]{11, 14, 3, 17, 16, 13, 3, 7, 19, 8}); //[1, 1, 1, 0, 0, 1, 0, 1]
     }

     static void test(int[] numbers) {
        int result[] = solution(numbers);
        System.out.println("\n\nSolution:");
        for (int i=0; i<result.length; i++)
            System.out.print(result[i]+" ");
     }

     static int[] solution(int[]numbers) {
        //Arreglo de la solución
        int []value = new int[numbers.length-2];
        int j = 0;
        for (int i=0; i<numbers.length-2; i++){
            Integer x = numbers[i];
            Integer y = numbers[i+1];
            Integer z = numbers[i+2];
            int comparator1 = x.compareTo(y);
            int comparator2 = y.compareTo(z);
            //Do not perform the first time
            if (comparator1 !=0 && comparator2 !=0 && comparator1 != comparator2)
                value [j++] = 1;
            else
                value [j++] = 0;
        }
        return value;
     }
}
