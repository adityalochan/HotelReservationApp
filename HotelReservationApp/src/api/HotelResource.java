package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;

public class HotelResource {
    Reservation reservation = new Reservation();
    private String firstName;
    private String lastName;
    List<Reservation> reservationList = new ArrayList<>();
    private Map<String,List<Reservation>> mapOfReservation = new HashMap<>();
    public Customer getCustomer(String email){
        return reservation.getCustomer();
    }

    public void createCustomerAccount(String email, String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
        new Customer(firstName,lastName,email);
    }

//    public IRoom getRoom(String roomNumber){
//
//    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = new Customer(firstName,lastName,customerEmail);
        Reservation reservation1 = new Reservation(customer,room,checkInDate,checkOutDate);
        reservationList.add(reservation1);
        mapOfReservation.put(customerEmail,reservationList);
        return reservation1;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return mapOfReservation.get(customerEmail);
    }

//
//    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
//
//    }

}
