package task2bones;

public class City {
    private final String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "City: " + cityName;
    }
}
