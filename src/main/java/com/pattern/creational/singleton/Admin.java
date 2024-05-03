package main.java.com.pattern.singleton;

public class Admin {
    public static void main(String[] args) {
        DocumentPrinter.print();
        ImagePrinter.print();

        DocumentPrinter.printV2();
        ImagePrinter.printV2();
    }
}
