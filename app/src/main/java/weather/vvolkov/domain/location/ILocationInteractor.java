package weather.vvolkov.domain.location;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import weather.vvolkov.models.location.Location;

public interface ILocationInteractor {
    @NonNull
    Single<Location> getCurrentLocation();
}
