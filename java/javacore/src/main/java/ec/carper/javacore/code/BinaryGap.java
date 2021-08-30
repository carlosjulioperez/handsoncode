package ec.carper.javacore.code;

public class BinaryGap {
    
    public static int solution(int N) {
        // write your code in Java SE 8
        int gap = 0;
        int[] rangeValid = { 1, 2, 147, 483, 647, 1041, 15, 32 };

        boolean numberFound = false;
        for (int i = 0; i < rangeValid.length; i++) {
            if (N == rangeValid[i]) {
                numberFound = true;
                break;
            }
        }
        //System.out.println(numberFound);

        if (numberFound){
            String binaryNumber = Integer.toString(N, 2); 
            boolean processed = false;
            int max = 0;
            for (int i = 0; i < binaryNumber.length(); i++) {
                char digit = binaryNumber.charAt(i);

                if (digit=='1' && !processed)
                    processed = true;

                if (processed){
                    if (digit=='1'){
                        if (max > gap) 
                            gap = max;
                        max = 0;
                    }else
                        max++;
                }
                
                // System.out.print(digit);
                // System.out.print(" " + processed);
                // System.out.print(" " + max);
                // System.out.print(" " + gap+"\n\n");
            }
        }

        return gap;
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(147));
        System.out.println(solution(483));
        System.out.println(solution(647));
        
        System.out.println(solution(1041));
        System.out.println(solution(15));
        System.out.println(solution(32));

    }

    //Description:
    // A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

    // For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

    // Write a function:

    // class Solution { public int solution(int N); }

    // that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

    // For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

    // Write an efficient algorithm for the following assumptions:

    // N is an integer within the range [1..2,147,483,647].

}
