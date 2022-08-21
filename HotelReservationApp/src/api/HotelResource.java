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

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomerAccount(String email, String firstName, String lastName) {
        customerService.addCustomer(firstName,lastName,email);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        if(getCustomer(customerEmail)==null) return Collections.emptyList();
        return reservationService.getCustomerReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.availableRooms(checkIn,checkOut);
    }


    public Collection<IRoom> findSubstituteRooms(Date checkInDate, Date checkOutDate) {
        return reservationService.findSubstituteRooms(checkInDate,checkOutDate);
    }

    public Date defaultDays(Date date) {
        return reservationService.defaultDays(date);
    }
}
