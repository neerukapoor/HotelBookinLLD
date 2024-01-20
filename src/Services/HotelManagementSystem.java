package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Objects.Hotel;
import Objects.Room;

public class HotelManagementSystem {

    HashMap<Integer, Hotel>hotels;
    HashMap<String,List<Hotel>>hotelByAddress;
    ReservationManagementSystem reservationManagementSystem;

    static HotelManagementSystem hotelManagementSystem;

    public static HotelManagementSystem getInstance() {
        if(hotelManagementSystem==null)
            hotelManagementSystem = new HotelManagementSystem();
        return hotelManagementSystem;
    }

    private HotelManagementSystem() {
        hotels = new HashMap<>();
        hotelByAddress = new HashMap<>();
        reservationManagementSystem = ReservationManagementSystem.getInstance();
    }

    //reserve Hotels
    //find hotels by location
    //Add rooms to hotel
    //reserve room by date
    //cancel booking room

    public void reserveHotel(Hotel hotel) {
        if(hotels.containsKey(hotel.getId()))
        {
            System.out.println("Hotel with this id already present");
            return;
        }
        hotels.put(hotel.getId(), hotel);
        List<Hotel>existingHotelsByAddress = new ArrayList<>();
        if(hotelByAddress.containsKey(hotel.getAddress().getCityName())) {
            existingHotelsByAddress = hotelByAddress.get(hotel.getAddress().getCityName());
        }
        existingHotelsByAddress.add(hotel);
        hotelByAddress.put(hotel.getAddress().getCityName(), existingHotelsByAddress);
        System.out.println("Hotel Reserved Successfully");
    }

    public void findHotelsByLocation(String cityName) {
        if(hotelByAddress.containsKey(cityName)) {
            List<Hotel>existingHotelsByAddress = hotelByAddress.get(cityName);
            for(Hotel hotel:existingHotelsByAddress) {
                System.out.println("Hotel Id: " + hotel.getId() + ", Hotel Name: " + hotel.getHotelName());
            }
        }
        else {
            System.out.println("No Hotel present in this City");
        }
    }

    public void addRoomToHotel(Integer hotelId, Room room) {
        if(!hotels.containsKey(hotelId)) {
            System.out.println("Hotel with this id does not exist");
            return;
        }
        Hotel hotel = hotels.get(hotelId);
        hotel.addRoom(room);
        System.out.println("Room added successfully");
    }

    public void reserveRoomByDate(Integer hotelId, Integer roomId, LocalDate startDate, LocalDate endDate) {
        Hotel hotel = hotels.get(hotelId);
        List<Room> rooms = hotel.getRooms();
        Room room = null;
        for(Room existingRoom:rooms) {
            if(existingRoom.getId() == roomId) {
                room = existingRoom;
            }
        }
        if(room!=null) {
            reservationManagementSystem.reserveRoom(hotel, room, startDate, endDate);
            return;
        }
        System.out.println("Error while reserving");
    }

    public void cancelBooking(Integer hotelId, Integer roomId, LocalDate startDate, LocalDate endDate) {
        Hotel hotel = hotels.get(hotelId);
        List<Room> rooms = hotel.getRooms();
        Room room = null;
        for(Room existingRoom:rooms) {
            if(existingRoom.getId() == roomId) {
                room = existingRoom;
            }
        }
        if(room!=null) {
            reservationManagementSystem.cancelRoom(hotel, room, startDate, endDate);
            return;
        }
        System.out.println("Error while cancelling");
    }
}
