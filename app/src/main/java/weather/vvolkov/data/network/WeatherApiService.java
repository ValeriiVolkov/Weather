package weather.vvolkov.data.network;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static weather.vvolkov.data.network.WeatherApi.BASE_URL;

public class WeatherApiService {
    @NonNull
    public static WeatherApi getWeatherApi() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(WeatherApi.class);
    }
}
