package com.src;

import java.util.Scanner;

public class Main {

    public final static String shopeName = "caffe1";

    // main entry point for app
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                // clear console in start
                HelperFunctions.clearConsole();

                System.out.println("**********\nWelcome to the POS system.\n\nPress: \n 1 for Employee Management \n 2 for Inventory Management \n 3 for Sales\n 4 for Customer Management");

                // ask for user input and move accordingly
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    EmployeeManagement.employeeManagement();
                } else if (input.equals("2")) {
                    ItemsManagement.itemsManagement();
                } else if (input.equals("3")) {
                    SalesManagement.salesManagement();
                } else if (input.equals("4")) {
                    CustomerManagement.customerManagement();
                }
                else {
                    throw new Exception("ERROR: Invalid choice.");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());
                HelperFunctions.waitForUserInput();
            }
        }
    }
}

class HelperFunctions {
    // clear console output
    public final static void clearConsole() throws Exception
    {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }

    public final static void waitForUserInput()
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter any key to continue...");
        scn.nextLine();
    }
}