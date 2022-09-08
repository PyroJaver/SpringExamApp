package examapp.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @Size(min = 4, max = 50)
    @NotEmpty(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Column(name = "password")
    @Size(min = 4, max = 50)
    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 50)
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Год рождения не может быть пустым")
    @Size(min = 1900)
    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    @NotEmpty
    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password, String name, String surname, int yearOfBirth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }
}
