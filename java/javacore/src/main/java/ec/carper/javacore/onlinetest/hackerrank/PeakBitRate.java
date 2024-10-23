package ec.carper.javacore.onlinetest.hackerrank;

public class PeakBitRate {

    public static int peakBitRate(int n, int k, int[] data){
        int peak = 0;
        int sum = 0;

        // Calculate the sum of the first 'k' elements
        for (int i=0; i<k; i++)
            sum += data[i];
        peak = sum;

        // Sliding window to calculate the sum of every 'k' 
        // consecutive elements
        for (int i=k; i<n; i++){
            //System.out.println( String.format("sum=%d", sum) );
            sum += data[i] - data[i-k];
            peak = Math.max(peak, sum);
        }

        return peak;
    }


    public static void main(String[] args) {
        int n = 5; // Number of data points
        int k = 3; // Length of the window
        int[] data = {1, 3, 5, 2, 8};

        System.out.println(peakBitRate(n, k, data)); // Output will be the maximum sum of any k consecutive elements
    }
}
