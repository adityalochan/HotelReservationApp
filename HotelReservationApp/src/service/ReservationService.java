package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import ui.AdminMenu;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static final ReservationService reservationService = new ReservationService( );
    private static final Map<String,Collection<Reservation>> reservations = new HashMap<>();
    private final Map<String,IRoom> rooms = new HashMap<>();

    private ReservationService(){}
//    Reference : https://www.tutorialspoint.com/java/java_using_singleton.htm
    public static ReservationService getInstance( ) {
        return reservationService;
    }

    public void addRoom(final IRoom room){
         rooms.put(room.getRoomNumber(),room);
    }

    public IRoom getARoom(final String roomId){
        return rooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public Reservation reserveARoom(final Customer customer, final IRoom room, final Date checkInDate, final Date checkOutDate){
        final Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        Collection<Reservation> allReservations = reservations.get(customer.getEmail());

        if(allReservations==null) allReservations=new LinkedList<>();

        allReservations.add(reservation);
        reservations.put(customer.getEmail(), allReservations);
        return reservation;
    }

    public Collection<IRoom> availableRooms(final Date checkInDate, final Date checkOutDate){
        final Collection<IRoom> unavailableRooms = new LinkedList<>();

        for (Reservation reservation:allReservations()) {
            if(checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())){
                unavailableRooms.add(reservation.getRoom());
            }
        }
        return rooms.values().
                        stream().
                            filter(r -> unavailableRooms.stream().noneMatch(notAvailableRoom -> notAvailableRoom.equals(r))).
                                collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomerReservation(final Customer customer){
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation(){
        if(allReservations().isEmpty()){
            System.out.println("No Reservations Currently");
            AdminMenu.adminMenu();
        }else {
            for (Reservation reservation : allReservations()) {
                System.out.println(reservation + " \n");
            }
        }
    }

    public Collection<IRoom> findSubstituteRooms(Date checkInDate, Date checkOutDate) {
        return availableRooms(defaultDays(checkInDate),defaultDays(checkOutDate));
    }

    public Date defaultDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,8);
        return cal.getTime();
    }

    String display(){
        return "Entered Reservation Service class";
    }

    public Collection<Reservation> allReservations(){
        Collection<Reservation> allReservations = new LinkedList<>();

        for(Collection<Reservation> reservations : reservations.values()) allReservations.addAll(reservations);
        return allReservations;
    }

}
