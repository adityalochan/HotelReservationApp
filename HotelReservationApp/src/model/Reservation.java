package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate){
        this.customer=customer;
        this.room=room;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        if(checkInDate.equals(new SimpleDateFormat("dd/MM/yy")))
            return "Welcome welcome!!!!";
        return "Customer CheckIn::" + checkInDate + " CheckOut:: " + checkOutDate;
    }

}
