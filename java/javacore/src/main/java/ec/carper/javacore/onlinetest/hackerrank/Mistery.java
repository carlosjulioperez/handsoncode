 package ec.carper.javacore.onlinetest.hackerrank;

public class Mistery {
    private static Mistery instance = null;
    protected Mistery(){
        // Exists only to defeat instantiation
    }
   
    public static Mistery getInstance() {
        if (instance==null)
            instance = new Mistery();
        return instance;
    }
}