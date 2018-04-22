package weather.vvolkov.data.network;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weather.vvolkov.models.weather.WeatherInfo;

public interface WeatherApi {
    String BASE_URL = "http://api.openweathermap.org";

    @GET("/data/2.5/weather")
    Single<WeatherInfo> getWeather(@Query("lat") double latitude,
                                   @Query("lon") double longitude,
                                   @Query("appid") @NonNull String appId,
                                   @Query("units") @NonNull String units);

    @GET("/data/2.5/weather")
    Observable<WeatherInfo> getWeather(@Query("id") long cityId,
                                       @Query("appid") @NonNull String appId,
                                       @Query("units") @NonNull String units);
}
