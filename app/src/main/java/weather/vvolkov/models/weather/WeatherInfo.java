
package weather.vvolkov.models.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo implements Parcelable {
    public static final Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
        }
    };

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

    public WeatherInfo(@NonNull String name) {
        this.name = name;
    }

    protected WeatherInfo(Parcel in) {
        id = in.readLong();
        dt = in.readInt();
        name = in.readString();
        cod = in.readInt();
        in.readTypedList(weather, Weather.CREATOR);
        main = (Main) in.readValue(Main.class.getClassLoader());
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeInt(dt);
        parcel.writeString(name);
        parcel.writeInt(cod);
        parcel.writeTypedList(weather);
        parcel.writeValue(main);
    }
}
