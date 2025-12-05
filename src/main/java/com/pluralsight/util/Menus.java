package com.pluralsight.util;

import com.pluralsight.model.Dealership;

import java.util.List;

public class Menus {

    public static void displayMainMenu(){
        System.out.println("\nMain Menu ---------------------");
        System.out.println("What would you like to do today?");
        System.out.print("\t[1] Display All Vehicles\n");
        System.out.print("\t[2] Filter View\n");
        System.out.print("\t[3] Edit Vehicle Inventory\n");
        System.out.print("\t[4] Record a Contract\n");
        System.out.print("\t[5] Exit Dealership Manager\n");
    }

    public static void displaySearchMenu(){
        System.out.println("\nFilter Search Menu -------------");
        System.out.println("What would you like to search by?");
        System.out.println("\t[1] By Price Range");
        System.out.println("\t[2] By Make/Model");
        System.out.println("\t[3] By Year");
        System.out.println("\t[4] By Color");
        System.out.println("\t[5] By Mileage");
        System.out.println("\t[6] By Vehicle Type");
        System.out.println("\t[7] Exit Search");
    }

    public static void displayDealershipMenu(List<Dealership> dealerships){
        int i = 1;
        System.out.println("Select a Dealership:");
        for (Dealership d : dealerships){
            System.out.printf("\t[%d] %s%n",i,d.getName());
            i++;
        }
    }

    public static void displayEditMenu(){
        System.out.println("\nEdit Inventory");
        System.out.println("\t[1] Add Vehicle");
        System.out.println("\t[2] Remove Vehicle");
        System.out.println("\t[3] Exit Edit Menu");
    }

    public static void displayContractMenu(){
        System.out.println("\nRecord Contract Menu");
        System.out.println("What is the type of contract?");
        System.out.println("\t[1] Sales");
        System.out.println("\t[2] Lease");
        System.out.println("\t[3] Back to Main Menu");
    }
}
