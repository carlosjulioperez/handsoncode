package ec.carper.javacore.onlinetest.codesignal.example;

public class UniqueDigits {
    public static boolean hasUniqueDigits(int number) {
        String numberString = String.valueOf(number);
        return numberString.chars()
                .distinct() // Remove duplicates
                .count() == numberString.length(); // Check if number of distinct chars equals number of digits
    }

    public static void main(String[] args) {
        System.out.println(hasUniqueDigits(123)); // true (no repeated digits)
        System.out.println(hasUniqueDigits(1122)); // false (repeated digits)
    }
}
