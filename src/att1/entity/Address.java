package att1.entity;

public class Address {
    private int id;
    private String street;
    private int houseNumber;
    private String country;
    private String state;
    private String city;
    private int zipCode;

    public Address() {
    }

    public Address(int id, String street, int houseNumber, String country, String state, String city, int zipCode) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
