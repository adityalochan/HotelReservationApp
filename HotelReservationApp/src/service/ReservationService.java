package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import ui.AdminMenu;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService( );
    static Map<Customer,List<Reservation>> mapOfReservation = new HashMap<>();
    static List<Reservation> reservationList = new ArrayList<>();
    Map<String,IRoom> rooms = new HashMap<>();

    private ReservationService(){}
//    Reference : https://www.tutorialspoint.com/java/java_using_singleton.htm
    public static ReservationService getInstance( ) {
        return reservationService;
    }

    public void addRoom(IRoom room){
         rooms.put(room.getRoomNumber(),room);
    }

    public IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservationList.add(reservation);
        mapOfReservation.put(customer,reservationList);
        return reservation;
    }


    public Collection<IRoom> availableRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> unavailableRooms = new ArrayList<>();
        for (Reservation currentReservation:reservationList) {
            if(checkInDate.before(currentReservation.getCheckOutDate()) && checkOutDate.after(currentReservation.getCheckInDate())){
                unavailableRooms.add(currentReservation.getRoom());
            }
        }
        return rooms.values().
                        stream().
                            filter(availableRooms -> unavailableRooms.stream().noneMatch(notAvailableRoom -> notAvailableRoom.equals(availableRooms))).
                                collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return mapOfReservation.get(customer);
    }

    public void printAllReservation(){
        if(reservationList.isEmpty()){
            System.out.println("No Reservations Currently");
            AdminMenu.adminMenu();
        }else {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation + " \n");
            }
        }
    }

    String classInvoked() {
        return "Class ReservationService Invoked";
    }
}
