package ec.carper.javacore.onlinetest.codesignal.cj;

public class CenturyFromYear {
    public static void main(String[] args) {
        System.out.println(solution(1905));
        System.out.println(solution(1700));
    }

    static int solution(int year) {
        int value = year % 100;
        if (value == 0)
            return year / 100;
        else
            return (year / 100) + 1;
    }
}
