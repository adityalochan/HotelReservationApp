package model;

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

    @Override
    public String toString(){
        return "Room Number: " + roomNumber + " Room Price: " + price;
    }
}
