package model;

public class Room implements IRoom{
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(){
    }
    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber=roomNumber;
        this.price=price;
        this.enumeration=enumeration;
    }

    @Override
    public String getRoomNumber() {
        return null;
    }

    @Override
    public double getRoomPrice() {
        return 0;
    }

    @Override
    public RoomType getRoomType() {
        return null;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber=roomNumber;
    }

    public void setRoomPrice(Double price){
        this.price=price;
    }

    public void setRoomType(RoomType enumeration){
        this.enumeration=enumeration;
    }

    @Override
    public String toString(){
        return "Room Number: " + roomNumber + " Room Price: " + price;
    }
}
