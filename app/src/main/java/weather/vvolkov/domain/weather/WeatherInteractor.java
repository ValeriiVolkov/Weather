package weather.vvolkov.domain.weather;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.weather.IWeatherRepository;

import static weather.vvolkov.data.network.WeatherApi.BASE_URL;

public class WeatherInteractor implements IWeatherInteractor {
    private static final String URL_MASK = BASE_URL + "/img/w/%s.png";
    private static final String PAGE_URL_MASK = "https://openweathermap.org/city/%d";

    @NonNull
    private final IWeatherRepository weatherRepository;

    public WeatherInteractor(@NonNull IWeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @NonNull
    @Override
    public Single<WeatherInfo> getWeatherInfo(@NonNull Location location) {
        return weatherRepository.getWeatherInfo(location.getLattiude(), location.getLongitude());
    }

    @NonNull
    @Override
    public Observable<WeatherInfo> getWeatherInfo(long cityId) {
        return weatherRepository.getWeatherInfo(cityId);
    }

    @NonNull
    @Override
    public String getWeatherIconUrl(@NonNull String iconId) {
        return String.format(URL_MASK, iconId);
    }

    @NonNull
    @Override
    public String getTemperatureText(double temperature) {
        return Math.round(temperature) + " °C";
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String getWeatherPageUrl(long weatherId) {
        return String.format(PAGE_URL_MASK, weatherId);
    }
}
