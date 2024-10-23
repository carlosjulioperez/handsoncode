package ec.carper.javacore.onlinetest.coderpad;

import java.util.Arrays;

public class ExistNumber {

    public static void main(String[] args) {
        int[] ints = {-9, 14, 37, 102};
        System.out.println(exists(ints, 102));
        System.out.println(exists(ints, 36));
    }

    static boolean exists1(int[] ints, int k) {
		return Arrays.stream(ints).anyMatch(n -> n == k);
	}
    
    static boolean exists(int[] ints, int k) {
        boolean found = false;
        for (int number : ints) {
            if (number == k) {
                found = true;
                break;
            }
        }
        return found;
    }

}
