
package weather.vvolkov.models.weather;

import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo {
    @NonNull
    @Json(name = "weather")
    private List<Weather> weather = new ArrayList<>();

    private long id;

    @NonNull
    private Main main = new Main();

    @NonNull
    private Wind wind = new Wind();

    @NonNull
    private Clouds clouds = new Clouds();

    private int dt;

    @NonNull
    private String name = "";

    private int cod;

    public long getId() {
        return id;
    }

    @NonNull
    public List<Weather> getWeather() {
        return weather;
    }

    @NonNull
    public Main getMain() {
        return main;
    }

    @NonNull
    public Wind getWind() {
        return wind;
    }

    @NonNull
    public Clouds getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
