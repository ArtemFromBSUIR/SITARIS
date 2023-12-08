package tour;

public class Location {
    private String country;
    private String city;

    public Location(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }

    public static class LocationBuilder {
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
}