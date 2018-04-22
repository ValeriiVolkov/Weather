package weather.vvolkov.domain.weather;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.models.weather.WeatherInfo;
import weather.vvolkov.repositories.weather.IWeatherRepository;

public class WeatherInteractor implements IWeatherInteractor {
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
}
