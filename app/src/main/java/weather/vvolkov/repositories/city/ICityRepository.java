package weather.vvolkov.repositories.city;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import weather.vvolkov.models.city.City;

public interface ICityRepository {
    @NonNull
    Observable<City> searchCityNames(@NonNull String query);
}
