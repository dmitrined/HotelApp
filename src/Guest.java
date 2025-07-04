import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Импортируем DateTimeFormatter

public class Guest {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Guest(String firstName, String lastName, String email, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Guest{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", birthday=").append(birthday.format(DATE_FORMATTER));
        sb.append('}');
        return sb.toString();
    }
}