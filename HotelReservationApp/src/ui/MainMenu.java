package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    static HotelResource hotelResource = new HotelResource();

    public static void mainMenu() {
        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        try {
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection >= 1 && selection <= 5) {
                switch (selection) {
                    case 1:// find and reserve room
                        findAndReserveRoom();
                        break;
                    case 2:// see current reservations
                        myReservation();
                        break;
                    case 3://create account
                        createAccount();
                        break;
                    case 4://admin menu
                        AdminMenu.adminMenu();
                        break;
                    case 5://exit
                        System.out.println("Exiting System");
                        break;
                }
            } else
                System.out.println("Please enter number between 1 and 5");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Invalid Input");
        }
    }

    public static void printMainMenu() {
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

    public static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email format: name@domain.com");
        String email = scanner.nextLine();
        while (isNotValidEmail(email)) {
            System.out.println("Please enter valid email");
            email = scanner.nextLine();
        }
        System.out.println("First Name");
        String fName = scanner.nextLine();
        System.out.println("Last Name");
        String lName = scanner.nextLine();


        try {
            hotelResource.createCustomerAccount(email, fName, lName);
            System.out.println("Account Created Successfully");
            mainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findAndReserveRoom(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter checkIn Date mm/dd/yyyy example 02/01/2022");
        Date checkInDate = correctDate(scanner);
        while(checkInDate==null)
            checkInDate = correctDate(scanner);
        System.out.println("Enter checkOut Date mm/dd/yyy example 2/21/2022");
        Date checkOutDate = correctDate(scanner);
        while(checkOutDate==null)
            checkOutDate = correctDate(scanner);
        System.out.println("breakpoint");
        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate,checkOutDate);

        if (availableRooms.isEmpty()) {
            Collection<IRoom> substituteRooms = hotelResource.findSubstituteRooms(checkInDate, checkOutDate);

                    Date substituteCheckInDate = hotelResource.defaultDays(checkInDate);
                    Date substituteCheckOutDate = hotelResource.defaultDays(checkOutDate);
                    System.out.println("These are the only substitute rooms found for the \n");
                    System.out.println("Check In Date: " + checkInDate);
                    System.out.println("Check Out Date: " + checkOutDate);

                    //printing substitute rooms
                    substituteRooms.forEach(System.out::println);
                    reserveRoom(substituteCheckInDate, substituteCheckOutDate, substituteRooms);
        }else {
                availableRooms.forEach(System.out::println);
                reserveRoom(checkInDate, checkOutDate, availableRooms);
            }
            mainMenu();

    }

    private static void reserveRoom(Date substituteCheckInDate, Date substituteCheckOutDate, Collection<IRoom> substituteRooms) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to book the room? y/n");
        String roomToBook = scanner.nextLine();

        if(roomToBook.toLowerCase().charAt(0)=='y'){
            System.out.println("Enter email format: name@domain.com");
            String email = scanner.nextLine();
            while (isNotValidEmail(email)) {
                System.out.println("Please enter a valid email \n");
                email = scanner.nextLine();
            }
            if(hotelResource.getCustomer(email)==null)
                System.out.println("No customer found. Please create an account");
            else{
                System.out.println("Please enter room you would like to reserve");
                String roomNum = scanner.nextLine();

                if(substituteRooms.stream().anyMatch(r -> r.getRoomNumber().equals(roomNum))){
                    IRoom room = hotelResource.getRoom(roomNum);

                    Reservation reservation = hotelResource.bookARoom(email,room,substituteCheckInDate,substituteCheckOutDate);
                    System.out.println("Congrats! Reservations completed!");
                    System.out.println("Reservations::"+reservation);
                }else
                    System.out.println("Room not available. Please start again");
            }
            mainMenu();
        }else if (roomToBook.toLowerCase().charAt(0)=='n'){
            System.out.println("Please create an account");
            mainMenu();
        }else
            reserveRoom(substituteCheckInDate,substituteCheckOutDate,substituteRooms);

    }

    public static void myReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email format: name@domain.com");
        String email = scanner.nextLine();
        while (isNotValidEmail(email)) {
            System.out.println("Please enter a valid email \n");
            email = scanner.nextLine();
        }
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        System.out.println("breakpoint");
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No records \n");
            mainMenu();
        } else
            reservations.forEach(r -> System.out.println(" " + r));
    }

    public static boolean isNotValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(email).matches();
    }

    public static Date correctDate(Scanner scanner){
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(scanner.nextLine());
        }catch (Exception e){
            System.out.println("Please enter valid date of format mm/dd/yyyy");
            findAndReserveRoom();
        }
        return null;
    }
}
