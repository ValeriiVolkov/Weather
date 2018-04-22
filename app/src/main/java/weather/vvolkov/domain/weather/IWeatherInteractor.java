package weather.vvolkov.domain.weather;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.models.weather.WeatherInfo;

public interface IWeatherInteractor {
    @NonNull
    Single<WeatherInfo> getWeatherInfo(@NonNull Location location);
}
