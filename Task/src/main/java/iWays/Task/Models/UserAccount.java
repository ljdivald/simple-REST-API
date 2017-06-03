package iWays.Task.Models;

import java.util.List;

public class UserAccount {
    private final int userId;
    private final String title;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneCode;
    private final String phoneNumber;
    private final int role;
    private final Avatar avatar;
    private final Company company;
    private final List<BillingAddress> billingAddress;
    private final List<Link> links;

    public UserAccount(int userId, String title, String firstName, String lastName, String email, String phoneCode, String phoneNumber, int role, Avatar avatar, Company company, List<BillingAddress> billingAddress, List<Link> links) {
        this.userId = userId;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneCode = phoneCode;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.avatar = avatar;
        this.company = company;
        this.billingAddress = billingAddress;
        this.links = links;
    }

    public UserAccount() {
        this.userId = 1;
        this.title = "Mr.";
        this.firstName = "John";
        this.lastName = "Doe";
        this.email = "john.doe@i-ways.hr";
        this.phoneCode = "+385";
        this.phoneNumber = "9126565484";
        this.role = 1;
        this.avatar = null;
        this.company = null;
        this.billingAddress = null;
        this.links = null;
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

    public int getRole() {
        return role;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Company getCompany() {
        return company;
    }

    public List<BillingAddress> getBillingAddress() {
        return billingAddress;
    }

    public List<Link> getLinks() {
        return links;
    }
}
