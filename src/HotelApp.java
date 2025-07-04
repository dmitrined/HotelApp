import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class HotelApp {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        Hotel hotel = Hotel.getInstance();
        System.out.println("--- Список всех комнат в отеле при запуске ---");
        printList(hotel.getAllRooms());

        BookingManager bookingManager = BookingManager.getInstance(hotel);

        Guest guest1 = new Guest("Алексей", "Иванов", "alexey.ivanov@example.com", LocalDate.of(1988, 7, 25));
        Guest guest2 = new Guest("Елена", "Смирнова", "elena.smirnova@example.com", LocalDate.of(1992, 3, 10));
        Guest guest3 = new Guest("Дмитрий", "Кузнецов", "dmitry.kuznetsov@example.com", LocalDate.of(1975, 11, 5));

        System.out.println("\n--- Тестовые гости созданы ---");
        System.out.println(guest1);
        System.out.println(guest2);
        System.out.println(guest3);


        System.out.println("\nМетод Hotel.getRoomsByType(RoomType.STANDARD):");
        List<Room> standardRooms = hotel.getRoomsByType(RoomType.STANDARD);
        printList(standardRooms);

        System.out.println("\nМетод Hotel.getRoomsByType(RoomType.SUITE):");
        List<Room> suiteRooms = hotel.getRoomsByType(RoomType.SUITE);
        printList(suiteRooms);


        System.out.println("\n--- Бронирование комнат с помощью createBookingByType ---");

        LocalDate checkInA = parseDate("05.07.2025");
        LocalDate checkOutA = parseDate("09.07.2025");

        if (checkInA != null && checkOutA != null) {
            System.out.println("\nПопытка забронировать STANDARD комнату для " + guest1.getFirstName() +
                    " с " + checkInA.format(DATE_FORMATTER) + " по " + checkOutA.format(DATE_FORMATTER));
            Booking bookingA = bookingManager.createBookingByType(RoomType.STANDARD, guest1, checkInA, checkOutA);
            if (bookingA != null) {
                System.out.println("Стоимость бронирования: " + bookingManager.calculateBookingCost(bookingA));
            }
        }


        LocalDate checkInB = parseDate("05.07.2025");
        LocalDate checkOutB = parseDate("09.07.2025");
        if (checkInB != null && checkOutB != null) {
            System.out.println("\nПопытка забронировать STANDARD комнату для " + guest2.getFirstName() +
                    " с " + checkInB.format(DATE_FORMATTER) + " по " + checkOutB.format(DATE_FORMATTER));
            Booking bookingB = bookingManager.createBookingByType(RoomType.STANDARD, guest2, checkInB, checkOutB);
            if (bookingB != null) {
                System.out.println("Стоимость бронирования: " + bookingManager.calculateBookingCost(bookingB));
            }
        }

        System.out.println("\n--- Поиск бронирований по имени 'Алексей' ---");
        List<Booking> alexeyBookings = bookingManager.findBookingsByGuestName("Алексей");
        printList(alexeyBookings);

        System.out.println("\n--- Поиск бронирований по фамилии 'Смирнова' ---");
        List<Booking> smirnovaBookings = bookingManager.findBookingsByGuestName("Смирнова");
        printList(smirnovaBookings);


        LocalDate checkInAvailable1 = parseDate("16.07.2025");
        LocalDate checkOutAvailable1 = parseDate("18.07.2025");
        if (checkInAvailable1 != null && checkOutAvailable1 != null) {
            System.out.println("\n--- Список свободных комнат на даты (" + checkInAvailable1.format(DATE_FORMATTER) + " по " + checkOutAvailable1.format(DATE_FORMATTER) + ") ---");
            List<Room> availableRoomsForPeriod = bookingManager.getAvailableRooms(checkInAvailable1, checkOutAvailable1);
            printList(availableRoomsForPeriod);
        }

        LocalDate checkInAvailable2 = parseDate("05.07.2025");
        LocalDate checkOutAvailable2 = parseDate("09.07.2025");
        if (checkInAvailable2 != null && checkOutAvailable2 != null) {
            System.out.println("\n--- Список свободных комнат на даты (" + checkInAvailable2.format(DATE_FORMATTER) + " по " + checkOutAvailable2.format(DATE_FORMATTER) + ") ---");
            List<Room> availableRoomsForPeriod2 = bookingManager.getAvailableRooms(checkInAvailable2, checkOutAvailable2);
            printList(availableRoomsForPeriod2);
        }
    }


    private static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка парсинга даты: " + dateString + " " + e.getMessage());
            return null;
        }
    }

    public static <T> void printList(List<T> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Список пуст или равен null. Нет элементов для вывода.");
        } else {
            list.forEach(System.out::println);
        }
    }
}