package weather.vvolkov.domain.icon;

import android.support.annotation.NonNull;

import static weather.vvolkov.data.network.WeatherApi.BASE_URL;

public class WeatherIconInteractor implements IWeatherIconInteractor {
    private static final String URL_MASK = BASE_URL + "/img/w/%s.png";

    @NonNull
    @Override
    public String getWeatherIconUrl(@NonNull String iconId) {
        return String.format(URL_MASK, iconId);
    }
}
