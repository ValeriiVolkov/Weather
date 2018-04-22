package weather.vvolkov.domain.location;

import android.support.annotation.NonNull;

import io.reactivex.Maybe;
import weather.vvolkov.models.location.Location;

public interface ILocationInteractor {
    @NonNull
    Maybe<Location> getCurrentLocation();
}
