package resources;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResources {
    List<IRoom> rooms = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    Reservation reservation = new Reservation();

    public Customer getCustomer(String email){
        return reservation.getCustomer();
    }

    public void addRoom(List<IRoom> rooms){
        this.rooms=rooms;
        for(IRoom room : rooms){
            reservation.setRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return this.rooms;
    }

    public Collection<Customer> getAllCustomers(){
        customers.add(reservation.getCustomer());
        return customers;
    }

    public void displayAllReservations(){
        System.out.printf("No reservations at the moment");
    }


}
