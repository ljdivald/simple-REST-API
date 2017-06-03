package iWays.Task.Models;

import java.util.List;

public class BillingAddress {
    private final int billingId;
    private final int userId;
    private final String address;
    private final String streetName;
    private final String city;
    private final String zip;
    private final Country country;
    private final List<Link> links;

    public BillingAddress(int billingId, int userId, String address, String streetName, String city, String zip, Country country, List<Link> links) {
        this.billingId = billingId;
        this.userId = userId;
        this.address = address;
        this.streetName = streetName;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.links = links;
    }

    public int getBillingId() {
        return billingId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Country getCountry() {
        return country;
    }

    public List<Link> getLinks() {
        return links;
    }
}
