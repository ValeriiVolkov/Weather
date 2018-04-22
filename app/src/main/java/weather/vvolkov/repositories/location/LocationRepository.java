package weather.vvolkov.repositories.location;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;

import io.reactivex.Single;
import weather.vvolkov.models.location.Location;

public class LocationRepository implements ILocationRepository {
    @NonNull
    private final FusedLocationProviderClient locationClient;

    public LocationRepository(@NonNull FusedLocationProviderClient locationClient) {
        this.locationClient = locationClient;
    }

    @SuppressLint("MissingPermission")
    @NonNull
    @Override
    public Single<Location> getCurrentLocation() {
        return Single.create(emitter -> {
            try {
                locationClient.getLastLocation().addOnSuccessListener(
                        location -> emitter.onSuccess(new Location(location.getLatitude(),
                                location.getLongitude()))
                );
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
