package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {
    CustomerService customerService = new CustomerService();
    ReservationService reservationService = new ReservationService();
    Reservation reservation = new Reservation();

    public Customer getCustomer(String email){
        return reservation.getCustomer();
    }

    public void addRoom(IRoom rooms){
        reservationService.addRoom(rooms);
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }


}
