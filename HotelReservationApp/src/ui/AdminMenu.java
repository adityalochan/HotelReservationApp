package ui;

import api.AdminResource;
import model.*;
import service.ReservationService;

import java.util.*;

public class AdminMenu {
    static AdminResource adminResource = new AdminResource();
    static LinkedHashSet<String> roomSet = new LinkedHashSet<>();
    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        try {
            printAdminMenu();
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection >= 1 && selection <= 5) {
                switch (selection) {
                    case 1: // all customers
                        displayAllCustomers();
                        break;
                    case 2: // all rooms
                        displayAllRooms();
                        break;
                    case 3: // all reservations
                        displayAllReservations();
                        break;
                    case 4: // add room
                        addRoom();
                        break;
                    case 5:
                        MainMenu.mainMenu();
                        break;
                }
            } else
                System.out.println("Please enter number between 1 and 5");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Invalid Input");
        }
    }

    public static void printAdminMenu() {
        System.out.println("Admin Menu");
        System.out.println("----------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");
        System.out.println("----------------------------------------");
        System.out.println("Please select number for menu select?");
    }

    public static void displayAllCustomers() {
        Collection<Customer> customersList = adminResource.getAllCustomers();
        if (customersList.isEmpty()) {
            System.out.println("No Customers Present");
        } else {
            for (Customer customer : customersList) {
                System.out.println(customer.toString());
            }
        }
        adminMenu();
    }

    public static void displayAllRooms() {
        Collection<IRoom> roomsList = adminResource.getAllRooms();
        if (roomsList.isEmpty()) {
            System.out.println("No Rooms Present");
        } else {
            for (IRoom room : roomsList) {
                System.out.println(room.toString());
            }
        }
        adminMenu();
    }

    public static void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void addRoom() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter room number");

            String roomNum = scanner.nextLine();
            while (!roomSet.add(roomNum)) {
                System.out.println("Please enter unique room");
                roomNum = scanner.nextLine();
            }

            System.out.println("Enter price per night");
            final Double price = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            final RoomType roomType = Integer.parseInt(scanner.nextLine()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;

        adminResource.addRoom(Collections.singletonList(new Room(roomNum, price, roomType)));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Room was added successfully");
        System.out.println("Would you like to add another room Y/N");
        addAnotherRoom();
    }

    public static void addAnotherRoom() {
        Scanner scanner = new Scanner(System.in);
        try {
            String c = scanner.nextLine();
            if (c.toLowerCase().charAt(0) == 'n')
                adminMenu();
            else if (c.toLowerCase().charAt(0) == 'y')
                addRoom();
            else {
                System.out.println("Please enter Y(yes) or N(no)");
                addAnotherRoom();
            }
        } catch (Exception e) {
            System.out.println("This is invalid input");
            e.printStackTrace();
        }

    }
}
