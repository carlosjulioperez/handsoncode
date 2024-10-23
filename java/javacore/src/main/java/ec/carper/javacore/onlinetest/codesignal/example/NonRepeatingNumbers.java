package ec.carper.javacore.onlinetest.codesignal.example;

import java.util.ArrayList;
import java.util.List;

public class NonRepeatingNumbers {
    public static void main(String[] args) {
        int min = 1879; //870;
        int max = 1902; //893;
        List<Integer> numbers = solution(min, max);
        System.out.println("Non-repeating numbers between " + min + " and " + max + ":");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static List<Integer> solution(int min, int max) {
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (hasUniqueDigits(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean hasUniqueDigits(int num) {
        boolean[] digits = new boolean[10];
        while (num > 0) {
            int digit = num % 10;
            if (digits[digit]) {
                return false;
            }
            digits[digit] = true;
            num /= 10;
        }
        return true;
    }
}