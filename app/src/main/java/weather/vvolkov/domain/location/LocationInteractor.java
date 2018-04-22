package weather.vvolkov.domain.location;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;
import weather.vvolkov.models.location.Location;
import weather.vvolkov.repositories.location.ILocationRepository;

public class LocationInteractor implements ILocationInteractor {
    @NonNull
    private final ILocationRepository locationRepository;

    public LocationInteractor(@NonNull ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @NonNull
    @Override
    public Maybe<Location> getCurrentLocation() {
        return locationRepository.getCurrentLocation();
    }
}
