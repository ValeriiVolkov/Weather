package weather.vvolkov.models.city;

import android.support.annotation.NonNull;

public class City {
    private final long id;

    @NonNull
    private final String name;

    @NonNull
    private final String country;

    public City(long id, @NonNull String name, @NonNull String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getCountry() {
        return country;
    }
}
