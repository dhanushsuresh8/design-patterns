package main.java.com.pattern.singleton;

public class DocumentPrinter {
    public static void print(){
        PrintSpooler printSpooler = PrintSpooler.getInstance();
        printSpooler.print();
    }

    public static void printV2(){
        PrintSpoolerV2.getInstance().print();
    }
}
