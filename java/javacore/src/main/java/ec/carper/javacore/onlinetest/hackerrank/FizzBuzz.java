package ec.carper.javacore.onlinetest.hackerrank;

public class FizzBuzz {
    public static void main(String[] args) {
        fixxBuzz(15);
    }

    static void fixxBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0)
                System.err.println("FizzBuzz");
            else if (i % 3 == 0 && i % 5 != 0)
                System.err.println("Fizz");
            else if (i % 3 != 0 && i % 5 == 0)
                System.err.println("Buzz");
            else
                System.err.println(i);
        }
    }
}
