package weather.vvolkov.domain.city;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import weather.vvolkov.models.city.City;
import weather.vvolkov.repositories.city.ICityRepository;

public class CityInteractor implements ICityInteractor {
    @NonNull
    private final ICityRepository cityRepository;

    public CityInteractor(@NonNull ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @NonNull
    @Override
    public Observable<City> searchCityNames(@NonNull String query) {
        return cityRepository.searchCityNames(query);
    }
}
