import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Импортируем DateTimeFormatter
import java.util.Objects;

public class Booking {
    private static long nextBookingId = 1;
    private long bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Booking(Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = nextBookingId++;
        this.room = room;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    public Room getRoom() {
        return room;
    }
    public long getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookingId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Booking{");
        sb.append("bookingId=").append(bookingId);
        sb.append(", room=").append(room);
        sb.append(", guest=").append(guest);
        // Используем форматтер для вывода дат
        sb.append(", checkIn=").append(checkIn.format(DATE_FORMATTER));
        sb.append(", checkOut=").append(checkOut.format(DATE_FORMATTER));
        sb.append('}');
        return sb.toString();
    }
}