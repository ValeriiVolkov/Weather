package weather.vvolkov.repositories.location;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import weather.vvolkov.models.location.Location;

public interface ILocationRepository {
    @NonNull
    Single<Location> getCurrentLocation();
}
