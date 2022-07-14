package service;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import ui.AdminMenu;
import ui.MainMenu;

import java.util.*;

public class ReservationService {
    IRoom room;
    static Map<Customer,List<Reservation>> mapOfReservation = new HashMap<>();
    static List<Reservation> reservationList = new ArrayList<>();
    List<IRoom> rooms = new ArrayList<>();

    public void addRoom(IRoom room){
         rooms.add(room);
    }

    public Room getARoom(String roomId){
        return new Room(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservationList.add(reservation);
        mapOfReservation.put(customer,reservationList);
        return reservation;
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
}
