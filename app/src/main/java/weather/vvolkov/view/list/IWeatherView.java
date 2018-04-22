package weather.vvolkov.view.list;

import android.support.annotation.NonNull;

import weather.vvolkov.models.weather.WeatherInfo;

public interface IWeatherView {
    void onItemClicked(int position);

    void addListItem(@NonNull WeatherInfo weatherInfo);
}
