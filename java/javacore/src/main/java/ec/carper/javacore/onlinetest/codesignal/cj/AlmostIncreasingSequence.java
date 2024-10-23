package ec.carper.javacore.onlinetest.codesignal.cj;

public class AlmostIncreasingSequence {
    public static void main(String[] args) {
        System.out.println( solution(new int[]{ 1, 3, 2, 1}) );
        System.out.println( solution(new int[]{ 1, 3, 2}) );
    }    
    
    static boolean solution(int[] sequence) {
        int x = 0;
        int y = 0;
        for (int i=0; i<sequence.length-1; i++){
            if (sequence[i]>=sequence[i+1])
                x++;
        }
        for (int i=0; i<sequence.length-2; i++){
            if (sequence[i]>=sequence[i+2])
                y++;
        }
        return (x<=1 && y<=1);
    }
}
