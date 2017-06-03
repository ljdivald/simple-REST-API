package iWays.Task.Models;

public class Company {
    private final int companyId;
    private final String name;
    private final String fullCompanyName;
    private final String vatNumber;
    private final Link links;

    public Company(int companyId, String name, String fullCompanyName, String vatNumber, Link links) {
        this.companyId = companyId;
        this.name = name;
        this.fullCompanyName = fullCompanyName;
        this.vatNumber = vatNumber;
        this.links = links;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getFullCompanyName() {
        return fullCompanyName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public Link getLinks() {
        return links;
    }
}
