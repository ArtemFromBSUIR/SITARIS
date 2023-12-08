package tour;

public class LocationFactoryImpl extends LocationFactory {
    @Override
    public Location createLocation(String country, String city) {
        return new Location(country, city);
    }
}
