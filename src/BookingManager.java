import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookingManager {
    private static BookingManager instance;
    private List<Booking> bookings;
    private Hotel hotel;

    private BookingManager(Hotel hotel) {
        this.bookings = new ArrayList<>();
        this.hotel = hotel;
    }
    public static BookingManager getInstance(Hotel hotel) {
        if (instance == null) {
            instance = new BookingManager(hotel);
        }
        return instance;
    }
    public Booking createBookingByType(RoomType roomType, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        if (roomType == null) {
            System.out.println("Ошибка: Тип комнаты не может быть null.");
            return null;
        }
        if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut) || checkIn.isBefore(LocalDate.now())) {
            System.out.println("Ошибка: Некорректные даты бронирования.");
            return null;
        }
        // Находим первую доступную комнату указанного типа
        Room availableRoom = hotel.getAllRooms().stream()
                .filter(room -> room.getType() == roomType)
                .filter(room -> isRoomAvailable(room, checkIn, checkOut))
                .findFirst()
                .orElse(null);

        if (availableRoom == null) {
            System.out.println("Инфо: Нет свободных номеров типа " + roomType + " на указанные даты.");
            return null;
        }
        // Создаем бронирование для найденной комнаты
        Booking newBooking = new Booking(availableRoom, guest, checkIn, checkOut);
        bookings.add(newBooking);
        System.out.println("Бронирование успешно создано: " + newBooking);
        return newBooking;
    }


    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
    public List<Booking> findBookingsByGuestName(String guestName) {
        if (guestName == null || guestName.isBlank()) {
            return Collections.emptyList();
        }
        return bookings.stream()
                .filter(booking -> booking.getGuest().getFirstName().toLowerCase().contains(guestName.toLowerCase()) ||
                        booking.getGuest().getLastName().toLowerCase().contains(guestName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                if (!(checkOut.isBefore(booking.getCheckIn()) || checkIn.isAfter(booking.getCheckOut()))) {
                    return false; // Комната занята
                }
            }
        }
        return true; // Комната свободна
    }
    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        List<Room> allRooms = hotel.getAllRooms();
        return allRooms.stream()
                .filter(room -> isRoomAvailable(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }
    public double calculateBookingCost(Booking booking) {
        long nights = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        if (nights < 0) {
            return 0; // Некорректные даты, стоимость 0
        }
        // Стоимость = количество ночей * базовая цена номера за ночь
        return nights * booking.getRoom().getType().getPrice();
    }


}

