package ec.carper.javacore.onlinetest.codesignal.cj;

public class ShapeArea {
    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
    }

    static int solution(int n) {
        return (n*n + ( (n-1) * (n-1 )));
    }
}