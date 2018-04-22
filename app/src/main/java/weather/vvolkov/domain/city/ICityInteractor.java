package weather.vvolkov.domain.city;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import weather.vvolkov.models.city.City;

public interface ICityInteractor {
    @NonNull
    Observable<City> searchCityNames(@NonNull String query);
}
