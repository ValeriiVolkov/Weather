package weather.vvolkov.repositories.weather;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import weather.vvolkov.data.network.WeatherApi;
import weather.vvolkov.models.weather.WeatherInfo;

public class WeatherRepository implements IWeatherRepository {
    /**
     * OpenWeather api key
     */
    private final static String APP_ID = "4aeae0f7e46a58183060f22b15e2bf03";

    /**
     * For retrieving temperature in Celsius
     */
    private static final String METRIC = "metric";

    @NonNull
    private final WeatherApi weatherApi;

    public WeatherRepository(@NonNull WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @NonNull
    @Override
    public Single<WeatherInfo> getWeatherInfo(double latitude, double longitude) {
        return weatherApi.getWeather(latitude, longitude, APP_ID, METRIC);
    }
}
