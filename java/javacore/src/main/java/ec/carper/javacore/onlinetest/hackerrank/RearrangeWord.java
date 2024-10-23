package ec.carper.javacore.onlinetest.hackerrank;

public class RearrangeWord {
    public static String rearrangeWord(String word) {
        char[] chArr = word.toCharArray();
        int n = chArr.length;
        
        // Step 1: Find the rightmost character which is smaller than its next character
        int i = n - 2;
        while (i >= 0 && chArr[i] >= chArr[i + 1]) {
            i--;
        }
        
        // If no such character is found, then the string is the largest permutation
        if (i == -1) {
            return "no answer";
        }
        
        // Step 2: Find the smallest character on the right side of the i-th character that is larger than chars[i]
        int j = n - 1;
        while (chArr[j] <= chArr[i]) {
            j--;
        }
        
        // Step 3: Swap the characters at i and j
        swp(chArr, i, j);
        
        // Step 4: Reverse the sequence from i+1 to end to get the smallest lexicographic order
        rvr(chArr, i + 1, n - 1);
        
        return new String(chArr);
    }

    // Helper method to swap two characters in an array
    private static void swp(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    // Helper method to reverse a portion of the array
    private static void rvr(char[] chars, int start, int end) {
        while (start < end) {
            swp(chars, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(rearrangeWord("baca")); // Output: bcaa
        System.out.println(rearrangeWord("xy")); // Output: yx
        System.out.println(rearrangeWord("pp")); // Output: no answer
        System.out.println(rearrangeWord("hefg")); // Output: hegf
    }
}
