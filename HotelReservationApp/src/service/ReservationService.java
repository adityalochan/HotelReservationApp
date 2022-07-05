package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    IRoom room;
    static Map<Customer,List<Reservation>> mapOfReservation = new HashMap<>();
    static List<Reservation> reservationList = new ArrayList<>();

    // TO-DO : not sure on functionality
    public void addRoom(IRoom room){
//         new Room();
    }
    public Room getARoom(String roomId){
        return new Room(roomId);
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
        for(Reservation res : reservationList){
            System.out.println(res.getCustomer());
        }
    }
}
