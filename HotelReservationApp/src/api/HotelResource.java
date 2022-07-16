package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class HotelResource {
    private String firstName;
    private String lastName;

    ReservationService reservationService = ReservationService.getInstance();
    CustomerService customerService = CustomerService.getInstance();
    List<Reservation> reservationList = new ArrayList<>();
    private Map<String, List<Reservation>> mapOfReservation = new HashMap<>();


    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomerAccount(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        new Customer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = new Customer(firstName, lastName, customerEmail);
        Reservation reservation1 = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation1);
        mapOfReservation.put(customerEmail, reservationList);
        return reservation1;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        return mapOfReservation.get(customerEmail);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.availableRooms(checkIn,checkOut);
    }

}
