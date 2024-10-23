package ec.carper.javacore.onlinetest.codesignal.cj;

public class MakeArrayConsecutive2 {

    public static void main(String[] args) {
        //System.out.println( solution(new int[]{ 6, 2, 3, 8 }) );
        System.out.println( solution(new int[]{ 0, 3}) );
    }

    static int solution(int[] statues) {
        int max = statues[0];
        int min = statues[0];
        int elems = (statues.length - 1);
        for (int i=0; i<statues.length; i++){
            if (statues[i] > max)
                max = statues[i];
            if (statues[i] < min)
                min = statues[i];
        }
        return ( max - min) - elems;
    }
}
