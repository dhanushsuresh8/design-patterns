package main.java.com.pattern.singleton;

//Declaring the class final so that the it cannot be extended.
public final class PrintSpooler {
    
    private static PrintSpooler INSTANCE;

    //To make it thread safe the method can be made syncronized
    // public static PrintSpooler getInstance(){
    //     if (INSTANCE == null) {
    //         INSTANCE = new PrintSpooler();
    //     }
    //     return INSTANCE;
    // }

    public static synchronized PrintSpooler getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new PrintSpooler();
        }
        return INSTANCE;
    }

    public void print(){
        System.out.println("Printing....");
    }
}
