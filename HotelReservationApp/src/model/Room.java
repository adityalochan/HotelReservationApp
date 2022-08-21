package model;

import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    protected final Double price;
    private final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber=roomNumber;
        this.price=price;
        this.enumeration=enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "Room Number: " + roomNumber + " Room Price: " + price + " Room Type: " + enumeration;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Room)) return false;
        if(obj==this) return true;

        Room room = (Room)obj;
        return Objects.equals(this.roomNumber,room.roomNumber);
    }
    @Override
    public int hashCode(){
        return Objects.hash(roomNumber);
    }

}
