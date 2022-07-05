package resources;

import model.Reservation;

import java.util.Collection;

public class HotelResources {

    public Customer getCustomer(String email){

    }

    public void createACustomer(String email, String firstName, String lastName){

    }

    public IRoom getRoom(String roomNumber){

    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){

    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){

    }

}
