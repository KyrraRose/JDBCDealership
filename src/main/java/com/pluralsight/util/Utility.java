package com.pluralsight.util;

import java.util.Scanner;

public class Utility {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserInt(String prompt){
        System.out.print(prompt);

        boolean notChosen = true;
        int option = -1;

        do {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }

    public static double getUserDouble(String prompt){
        System.out.print(prompt);

        boolean notChosen = true;
        double option = -1;

        do {
            try {
                option = scanner.nextDouble();
                scanner.nextLine();

                if (option != -1) notChosen = false;

            } catch (Exception e) {
                System.out.print("Invalid Type Entered. " + prompt);
                scanner.nextLine();
            }
        } while (notChosen);

        return option;
    }

    public static String getUserString(String prompt){
        System.out.println("\n-------\n");
        System.out.print(prompt);
        return scanner.nextLine().trim().toUpperCase();
    }

    public static void waitForEnter() {
        System.out.print("\n...Press Enter to Continue");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }
    }

    public static void waitAndContinue() {
        waitForEnter();
        clearScreen();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
