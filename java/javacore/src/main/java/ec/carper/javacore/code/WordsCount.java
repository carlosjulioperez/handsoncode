package ec.carper.javacore.code;

public class WordsCount {

    public static int solution(String S) {
        // write your code in Java SE 8
        
        int max = 0;
        String str = S.replaceAll("[.|!|?]", "-");  
        //System.out.println(str);  
        
        String []arraySentence = str.split("-");
        for(String sentence: arraySentence){
            //System.out.println(sentence);
        
            int n=0;
            String []arrayWords = sentence.trim().split(" ");
            for (String words: arrayWords ){
                if(words.trim().length()>0 ) 
                    n++;
            }
            
            if (n>max)
                max = n;
        }
        return max;
    }
    
    public static void main(String[] args) {
        String str1 = "We test coders. Give us a try?";
        String str2 = "Forget  CVs..Save time . x x";

        System.out.println( solution(str1)) ;
        System.out.println( solution(str2)) ;
    }
}
