package weather.vvolkov.repositories.weather;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import weather.vvolkov.models.weather.WeatherInfo;

public interface IWeatherRepository {
    @NonNull
    Single<WeatherInfo> getWeatherInfo(double latitude, double longitude);

    @NonNull
    Observable<WeatherInfo> getWeatherInfo(long cityId);
}
