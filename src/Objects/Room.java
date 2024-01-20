package Objects;

public class Room {
    int id;
    int price;
    RoomType roomType;

    public Room(int id, int price, RoomType roomType) {
        this.id = id;
        this.price = price;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}
