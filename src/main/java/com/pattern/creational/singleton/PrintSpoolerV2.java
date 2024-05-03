package main.java.com.pattern.singleton;

//Enum's is Java is always guranteed to be thread safe
//But you can't take this approach if you wan't to extend a super class.
//Increased prefromance when compared to other approach
public enum PrintSpoolerV2 {
    INSTANCE;

    private PrintSpoolerV2() {}

    public static PrintSpoolerV2 getInstance(){
        return INSTANCE;
    }

    public void print(){
        System.out.println("Printing from ENUM....");
    }
}
