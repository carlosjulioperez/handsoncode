package ec.carper.javacore.onlinetest.codesignal.cj;

public class CheckPalindrome {
    public static void main(String[] args) {
        System.out.println(solution("aabaa"));
        System.out.println(solution("abac"));
        System.out.println(solution("a"));
        System.out.println(solution("ana"));

        System.out.println("\nUsando Java 8:");
        System.out.println(solution2("aabaa"));
        System.out.println(solution2("abac"));
        System.out.println(solution2("a"));
    }
    static boolean solution(String inputString) {
        for (int i=0; i<inputString.length(); i++){
            if (inputString.charAt(i) != inputString.charAt(inputString.length()-1-i) )
                return false;
        }
        return true;
    }
    static boolean solution2(String inputString) {
        return inputString.equals(new StringBuffer(inputString).reverse().toString());
    }
}
