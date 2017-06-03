package iWays.Task.Models;

/**
 * Created by Ljudevit on 2.6.2017..
 */
public class User {
    private final int userId;
    private final String title;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneCode;
    private final String phoneNumber;
    private final String password;
    private final String tos;

    public User(int userId, String title, String firstName, String lastName, String email, String phoneCode, String phoneNumber, String password, String tos) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.tos = tos;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
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

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getTos() {
        return tos;
    }
}
