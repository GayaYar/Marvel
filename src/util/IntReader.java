package util;

import java.util.Scanner;

public class IntReader {
    public static int readInt(Scanner input) {
        boolean valid = false;
        int inputInt = 0;
        while (!valid) {
            try {
                inputInt = Integer.parseInt(input.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number (digits).");
            }
        }
        return inputInt;
    }
}
