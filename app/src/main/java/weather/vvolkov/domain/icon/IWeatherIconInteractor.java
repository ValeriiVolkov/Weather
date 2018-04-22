package weather.vvolkov.domain.icon;

import android.support.annotation.NonNull;

public interface IWeatherIconInteractor {
    @NonNull
    String getWeatherIconUrl(@NonNull String iconId);
}
