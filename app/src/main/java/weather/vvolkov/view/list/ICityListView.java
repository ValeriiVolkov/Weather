package weather.vvolkov.view.list;

import android.support.annotation.NonNull;

import weather.vvolkov.models.city.City;
import weather.vvolkov.models.weather.WeatherInfo;

public interface ICityListView {
    void addListItem(@NonNull City city);

    void showWeatherInfoScreen(@NonNull City city);

    void showWeatherInfoScreen(@NonNull WeatherInfo weatherInfo);

    void showToast(@NonNull String message);

    void showSearchMenu();

    void hideSearchMenu();

    void clearList();
}
