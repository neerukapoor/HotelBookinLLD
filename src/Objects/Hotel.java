package Objects;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    String hotelName;
    int id;
    List<Room>rooms;
    Address address;

    public Hotel(String hotelName, int id, Address address) {
        this.hotelName = hotelName;
        this.id = id;
        this.address=address;
        this.rooms=new ArrayList<>();
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
