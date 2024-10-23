package ec.carper.javacore.onlinetest.bairesdev;

import java.util.Arrays;

public class Solution1 {

    static long solve(int n, int[] a, int k) {
        // Sort the array to simplify the problem
        Arrays.sort(a);
        
        // Initialize the result to a large number
        long minSpecialValue = Long.MAX_VALUE;
        
        // Iterate over the sorted array using a sliding window of size k
        for (int i = 0; i <= n - k; i++) {
            // Calculate the special value for the current subsequence
            long specialValue = a[i + k - 1] - a[i];
            // Update the minimum special value
            minSpecialValue = Math.min(minSpecialValue, specialValue);
        }
        
        return minSpecialValue;
    }
    
    public static void main(String[] args) {
        // Example usage
        int[] a = {9, 5, 1, 4, 9};
        int n = 5;
        int k = 2;
        
        long result = solve(n, a, k);
        System.out.println(result);  // Output should be 0
    }
}
