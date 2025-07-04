import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private static Hotel instance;

    Hotel() {
        getRooms();
    }

    public static Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
        }
        return instance;
    }

    private void getRooms() {
        String filePath = System.getProperty("hotel.file.path", "/Users/dmitrinedioglo/ait/git_2/hw_lesson49/src/hotel.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            rooms = reader.lines()
                    .filter(s -> !s.isBlank())
                    .map(Hotel::parsRooms)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла отеля: " + e.getMessage());
            rooms = new ArrayList<>();
        }
    }

    public List<Room> getRoomsByType(RoomType roomType) {
        if (roomType == null) {
            System.out.println("Предупреждение: roomType не может быть null. Возвращен пустой список.");
            return Collections.emptyList();
        }
        return this.rooms.stream()
                .filter(room -> room.getType() == roomType)
                .collect(Collectors.toList());
    }


    private static Room parsRooms(String roomstring) {
        try {
            String[] arr = roomstring.trim().split(",");
            if (arr.length < 3) {
                System.out.println("Некорректная строка в файле отеля (недостаточно данных): " + roomstring);
                return null;
            }
            return new Room(arr[0], Integer.parseInt(arr[1]), RoomType.valueOf(arr[2]));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка парсинга строки номера: " + roomstring + ". Ошибка: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (rooms.isEmpty()) {
            sb.append("в отеле нету комнат");
        } else {
            for (Room room : rooms) {
                sb.append(room).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }


}
