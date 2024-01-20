import java.time.LocalDate;
import java.util.Scanner;

import Objects.Address;
import Objects.Hotel;
import Objects.Room;
import Objects.RoomType;
import Services.HotelManagementSystem;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Address address1 = new Address("Greater Noida");
        Hotel hotel1 = new Hotel("City Palace", 1, address1);

        Address address2 = new Address("Dubai");
        Hotel hotel2 = new Hotel("Maholla", 2, address2);

        Address address3 = new Address("Dubai");
        Hotel hotel3 = new Hotel("Maholla mera", 3, address3);

        HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();
        hotelManagementSystem.reserveHotel(hotel1);
        hotelManagementSystem.reserveHotel(hotel2);
        hotelManagementSystem.reserveHotel(hotel3);

        hotelManagementSystem.findHotelsByLocation(address1.getCityName());
        System.out.println("----------------------");
        hotelManagementSystem.findHotelsByLocation(address2.getCityName());

        Room room1 = new Room(1, 1000, RoomType.DELUX);
        Room room2 = new Room(2, 500, RoomType.SINGLE);
        hotelManagementSystem.addRoomToHotel(1, room1);
        hotelManagementSystem.addRoomToHotel(1, room2);

        System.out.println("Reserving Hotel");
        LocalDate startDate1 = LocalDate.of(2024, 1, 21);
        LocalDate endDate1 = LocalDate.of(2024, 1,25);
        hotelManagementSystem.reserveRoomByDate(hotel1.getId(), 1, startDate1, endDate1);

        System.out.println("--------------------------");

        LocalDate startDate2 = LocalDate.of(2024, 1, 26);
        LocalDate endDate2 = LocalDate.of(2024, 1,27);
        hotelManagementSystem.reserveRoomByDate(hotel1.getId(), 1, startDate2, endDate2);

        System.out.println("--------------------------");

        LocalDate startDate3 = LocalDate.of(2024, 1, 22);
        LocalDate endDate3 = LocalDate.of(2024, 1,25);
        hotelManagementSystem.reserveRoomByDate(hotel1.getId(), 1, startDate3, endDate3);

        System.out.println("Canceling Hotel");
        LocalDate starDate4 = LocalDate.of(2024,1,21);
        LocalDate endDate4 = LocalDate.of(2024, 1,25);
        hotelManagementSystem.cancelBooking(hotel1.getId(), 1, starDate4, endDate4);

        System.out.println("--------------------------");

        LocalDate starDate5 = LocalDate.of(2024,1,21);
        LocalDate endDate5 = LocalDate.of(2024, 1,25);
        hotelManagementSystem.cancelBooking(hotel1.getId(), 1, starDate5, endDate5);
    }
}
