package ec.carper.javacore.onlinetest.coderpad;

public class FirstNonRepeatingCharacter {
    
    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c', 'a', 'b', 'c', 'd'};
        System.out.println(findFirstNonRepeatingChar(chars));
    }

    public static char findFirstNonRepeatingChar(char[] chars) {
        int[] charCounts = new int[256]; // Assuming ASCII characters
    
        // Count occurrences of each character
        for (char c : chars) {
            charCounts[c]++;
        }
    
        // Find the first non-repeating character
        for (char c : chars) {
            if (charCounts[c] == 1) {
                return c;
            }
        }
    
        return '\0'; // No non-repeating character found
    }

}
