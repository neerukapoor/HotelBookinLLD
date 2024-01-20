package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Objects.Hotel;
import Objects.Room;

public class ReservationManagementSystem {

    HashMap<Hotel, HashMap<Room, List<Map<LocalDate, LocalDate>>>> reservations;

    //HashMap<Room, List<Map<LocalDate, LocalDate>>>reservations;

    static ReservationManagementSystem reservationManagementSystem;
    
    public static ReservationManagementSystem getInstance() {
        if(reservationManagementSystem==null)
            reservationManagementSystem = new ReservationManagementSystem();
        return reservationManagementSystem;
    }

    private ReservationManagementSystem() {
        reservations = new HashMap<>();

    }

    public void reserveRoom(Hotel hotel, Room room, LocalDate startDate, LocalDate endDate) {

        if(reservations.containsKey(hotel)) {
            HashMap<Room, List<Map<LocalDate, LocalDate>>> reservationByRoom = reservations.get(hotel);
            if(reservationByRoom.containsKey(room)) {
                List<Map<LocalDate, LocalDate>> existingReservations = reservationByRoom.get(room);
                for(Map<LocalDate, LocalDate> reservation:existingReservations) {
                    for (Map.Entry<LocalDate, LocalDate> entry : reservation.entrySet()) {
                        LocalDate key = entry.getKey();
                        LocalDate value = entry.getValue();
                        if(key.compareTo(startDate)<=0 && value.compareTo(endDate)>=0) {
                            System.out.println("Room Already booked for this date");
                            return;
                        }
                    }
                }
                Map<LocalDate, LocalDate> mapOfDate = new HashMap<>();
                mapOfDate.put(startDate, endDate);
                List<Map<LocalDate, LocalDate>> mapOfDates = reservations.get(hotel).get(room); 
                mapOfDates.add(mapOfDate);
                HashMap<Room, List<Map<LocalDate, LocalDate>>> bookingByRoom = reservations.get(hotel);
                bookingByRoom.put(room, mapOfDates);
                reservations.put(hotel, bookingByRoom);
            }
        }
        else {
            Map<LocalDate, LocalDate> mapOfDate = new HashMap<>();
            mapOfDate.put(startDate, endDate);
            List<Map<LocalDate, LocalDate>> mapOfDates = new ArrayList<>();
            mapOfDates.add(mapOfDate);
            HashMap<Room, List<Map<LocalDate, LocalDate>>> bookingByRoom = new HashMap<>();
            bookingByRoom.put(room, mapOfDates);
            reservations.put(hotel, bookingByRoom);
        }
        System.out.println("Room booked successfully");
    }

    public void cancelRoom(Hotel hotel, Room room, LocalDate startDate, LocalDate endDate) {
        if(reservations.containsKey(hotel)) {
            HashMap<Room, List<Map<LocalDate, LocalDate>>> reservationByRoom = reservations.get(hotel);
            if(reservationByRoom.containsKey(room)) {
                List<Map<LocalDate, LocalDate>> reservationsByDate = reservationByRoom.get(room);
                for(Map<LocalDate, LocalDate> reservation:reservationsByDate) {
                    for (Map.Entry<LocalDate, LocalDate> entry : reservation.entrySet()) {
                        LocalDate key = entry.getKey();
                        LocalDate value = entry.getValue();
                        if(key.compareTo(startDate)==0 && value.compareTo(endDate)==0) {
                            System.out.println("Booking Cancelled");
                            Map<LocalDate, LocalDate> dateToRemove = new HashMap<>();
                            dateToRemove.put(startDate, endDate);
                            reservations.get(hotel).get(room).remove(dateToRemove);
                            return;
                        }
                    }
                }
                System.out.println("No such booking exist");
            }
            else {
                System.out.println("No Room present with this id");
            }
        }
        else {
            System.out.println("No hotel present with this id");
        }
    }
}
