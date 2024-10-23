package ec.carper.javacore.onlinetest.coderpad;

public class Temperature {
    public static void main(String[] args) {
        /*
         * Result is correct with a simple data set: {7 5 9 1 4} -> 1
         * numbers: : {-15 -7 -9 -14 -12} -> -7
         * It works with two negative temperatures that are equal: {-10 -10} -> -10
         * When two temperatures are as close to 0, then the positive wins: {15 -7 9 14 7 12} -> 7
         * 
         */
        System.out.println(closestTo0(new int[] {7,5,9,1,4} ));
        System.out.println(closestTo0(new int[] {-15, -7, -9, -14, -12} ));
        System.out.println(closestTo0(new int[] {-10, -10} ));
        System.out.println(closestTo0(new int[] {15,-7,9,14,7,12} ));
        // System.out.println(computeClosestToZero(new int[] {7,5,9,1,4} ));
        // System.out.println(computeClosestToZero(new int[] {-15, -7, -9, -14, -12} ));
        // System.out.println(computeClosestToZero(new int[] {-10, -10} ));
        // System.out.println(computeClosestToZero(new int[] {15,-7,9,14,7,12} ));
    }
    
    public static int closestTo0 (int [] ts){
        if (ts.length == 0)
            return 0;
        
        int num = ts[0];
        int absNum = Math.abs(num);

        for (int i=0; i<ts.length; i++){
            int itm = Math.abs(ts[i]);
            if (itm < absNum || itm == absNum && ts[i]>0){
                num = ts[i];
                absNum = itm;
            }
        }

        return num;
    }

    public static int computeClosestToZero(int[] ts){
        // Write your code here
        // To debug: System.err.println("Debug messages...");
        if (ts.length == 0)
            return 0;

        int minP = Integer.MAX_VALUE;
        int minN = Integer.MIN_VALUE;

        for (int i=0; i<ts.length; i++){
            if (ts[i]>0){
                if (ts[i] < minP)
                    minP = ts[i];
            }else{
                if (ts[i] > minN)
                    minN = ts[i];
            }
        }
        if (minN!=Integer.MIN_VALUE){
            if (minP == Math.abs(minN))
                return minP;
            else{
                if (minP < Math.abs(minN))
                    return minP;
                else 
                    return minN;
            }
        }else
            return minP;
    }        
}
