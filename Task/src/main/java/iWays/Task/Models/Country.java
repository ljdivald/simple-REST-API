package iWays.Task.Models;

public class Country {
    private final int countryId;
    private final String name;
    private final String code;
    private final String phoneCode;

    public Country(int countryId, String name, String code, String phoneCode) {
        this.countryId = countryId;
        this.name = name;
        this.code = code;
        this.phoneCode = phoneCode;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getPhoneCode() {
        return phoneCode;
    }
}
