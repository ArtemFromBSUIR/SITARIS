package tour;

public class LocationBuilder {
    private String country;
    private String city;

    public LocationBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public LocationBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public Location build() {
        return new Location(country, city);
    }
}