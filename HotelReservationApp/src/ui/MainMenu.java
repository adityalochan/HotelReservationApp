package ui;

import api.HotelResource;
import model.Reservation;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

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
                        findAndReserveRoom();
                        keepRunning=false;
                    }else if (selection==2){ // see current reservations
                        myReservation();
                        keepRunning=false;
                    } else if (selection==3) { //create account
                        createAccount();
                        keepRunning=false;
                    } else if (selection==4) { //admin menu
                        AdminMenu.adminMenu();
                        keepRunning=false;
                    } else if (selection==5) { //exit
                        System.out.println("Exiting System");
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
        while(isNotValidEmail(email)){
            System.out.println("Please enter valid email");
            email = scanner.nextLine();
        }
        System.out.println("First Name");
        String fName = scanner.nextLine();
        System.out.println("Last Name");
        String lName = scanner.nextLine();


        try{
            hotelResource.createCustomerAccount(email,fName,lName);
            System.out.println("Account Created Successfully");
            mainMenu();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void findAndReserveRoom(){
    }

    public static void myReservation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email format: name@domain.com");
        String email = scanner.nextLine();
        while(isNotValidEmail(email)) {
            System.out.println("Please enter a valid email");
            email = scanner.nextLine();
        }
        Collection<Reservation> tmp = hotelResource.getCustomersReservations(email);
        if(tmp==null){
            System.out.println("No records");
            mainMenu();
        }
        else{
            try{
                for (Reservation r:tmp) {
                    System.out.println(r.getCustomer());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static boolean isNotValidEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(email).matches();
    }

}
