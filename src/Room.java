import java.util.Objects;

public class Room  {

    private final String number;
    private final int capacity;
    private final RoomType type;


    public Room(String number, int capacity, RoomType type) {
        this.number = number;
        this.capacity = capacity;
        this.type = type;

    }

    public String getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("number='").append(number).append('\'');
        sb.append(", capacity=").append(capacity);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}

