package utils.scanner;

import java.util.Scanner;

public class ScannerUtil {

    private static Scanner scanner;

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getString() {
        return getScanner().nextLine();
    }

    public static int getInteger() {
        String string;
        while (true) {
            string = getScanner().nextLine();
            if (string.matches("\\d+")) {
                return Integer.parseInt(string);
            } else {
                System.out.println("Wrong input!");
            }
        }
    }
}