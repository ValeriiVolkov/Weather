package weather.vvolkov.models.location;

public class Location {
    private final double lattiude;

    private final double longitude;

    public Location(double lattiude, double longitude) {
        this.lattiude = lattiude;
        this.longitude = longitude;
    }

    public double getLattiude() {
        return lattiude;
    }

    public double getLongitude() {
        return longitude;
    }
}
