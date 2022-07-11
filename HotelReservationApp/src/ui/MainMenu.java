package ui;

import api.HotelResource;
import ui.AdminMenu;

import java.util.Scanner;

public class MainMenu {
    static HotelResource hotelResource = new HotelResource();
    public static void mainMenu() {
        printMainMenu();
        boolean keepRunning=true;
        try(Scanner scanner = new Scanner(System.in)){
            while(keepRunning){
                try{
                    int selection = Integer.parseInt(scanner.nextLine());

                    if(selection==1){ // find and reserve room
                        System.out.println("test1");
                        keepRunning=false;
                    }else if (selection==2){ // see all reservations
                        System.out.println("test2");
                        keepRunning=false;
                    } else if (selection==3) { //create account
                        createAccount();
                        keepRunning=false;
                    } else if (selection==4) { //admin menu
                        AdminMenu.adminMenu();
                        keepRunning=false;
                    } else if (selection==5) { //exit
                        System.out.println("test4");
                        keepRunning=false;
                    } else
                        System.out.println("Please enter number between 1 and 5");
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error Invalid Input");
                }
            }
        }
    }

    public static void printMainMenu(){
        System.out.println("Welcome to Hotel Reservation Application");
        System.out.println("----------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------");
        System.out.println("Please select number for menu select?");
    }

    public static void createAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email format: name@domain.com");
        String email = scanner.nextLine();
        System.out.println("First Name");
        String fName = scanner.nextLine();
        System.out.println("Last Name");
        String lName = scanner.nextLine();

        hotelResource.createCustomerAccount(email,fName,lName);
    }
}
