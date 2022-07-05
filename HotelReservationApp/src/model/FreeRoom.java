package model;

public class FreeRoom extends Room{
    public FreeRoom(){
        setRoomPrice(0.0);
    }

    @Override
    public String toString(){
        return "Room price: " + price;
    }
}
