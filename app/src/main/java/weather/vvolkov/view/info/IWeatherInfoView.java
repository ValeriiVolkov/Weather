package weather.vvolkov.view.info;

import android.support.annotation.NonNull;

import weather.vvolkov.models.weather.WeatherInfo;

public interface IWeatherInfoView {
    String CITY_ID = "city_id";
    String WEATHER_INFO = "weather_info";

    void showInfo(@NonNull WeatherInfo weatherInfo);

    void openWebPage(@NonNull String url);

    void showWeatherImage(@NonNull String url);

    void showTemperature(@NonNull String temperatureText);
}
