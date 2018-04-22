package weather.vvolkov.repositories.location;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;
import weather.vvolkov.models.location.Location;

public interface ILocationRepository {
    @NonNull
    Maybe<Location> getCurrentLocation();
}
